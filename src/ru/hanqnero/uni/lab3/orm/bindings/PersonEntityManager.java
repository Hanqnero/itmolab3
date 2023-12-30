package ru.hanqnero.uni.lab3.orm.bindings;

import ru.hanqnero.uni.lab3.environment.Clothes;
import ru.hanqnero.uni.lab3.environment.Color;
import ru.hanqnero.uni.lab3.environment.Sledge;
import ru.hanqnero.uni.lab3.environment.abstractions.Desire;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.food.Food;
import ru.hanqnero.uni.lab3.people.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PersonEntityManager extends EntityManager<Person>{

    private final FoodEntityManager foodManager = new FoodEntityManager(getSession());
    private final ClothesEntityManager clothesManager = new ClothesEntityManager(getSession());

    protected PersonEntityManager(Session session) {
        super(session);
    }

    private HashMap<String,String> getColumns(Person entity) {
        HashMap<String,String> result = new HashMap<>();
        result.put("uuid", "'%s'".formatted(entity.getID()));
        result.put("name", "'%s'".formatted(entity.getName()));

        result.put("sceneUUID", "NULL");
        result.put("locationUUID", "NULL");
        if (entity.getCurrentScene() != null)
            result.replace("sceneUUID", "NULL", "'%s'".formatted(entity.getCurrentScene().getID()));
        if (entity.getCurrentScene() != null && entity.getCurrentScene().getLocation() != null)
            result.replace("locationUUID", "NULL", "'%s'".formatted(entity.getCurrentScene().getLocation().getID()));

        result.put("locationPosition", "NULL");
        if (entity.getCurrentScene() != null && entity.getCurrentScene().getLocation() != null) {
            var location = entity.getCurrentScene().getLocation();
            for (Location.Position pos: Location.Position.values()) {
                if (location.isPresent(entity, pos)) {
                    result.replace("locationPosition", "NULL", "'%s'".formatted(pos.toString()));
                    break;
                }
            }
        }

        result.put("favoriteFoodUUID", "NULL");
        if (entity instanceof HasFavoriteFood)
            result.replace("favoriteFoodUUID", "NULL", "'%s'".formatted(((HasFavoriteFood) entity).getFavoriteFood().getID()));

        result.put("dead", "NULL");
        if (entity instanceof CanBeDead) result.replace("dead", "NULL",
                switch (((CanBeDead) entity).getLifeStatus()) {
                    case Alive -> "TRUE";
                    case Dead  -> "FALSE";
                });

        result.put("desireType", "NULL");
        if (entity instanceof CanHaveDesires && ((CanHaveDesires) entity).getCurrentDesire() != null)
            result.replace("desireType", "NULL", "'%s'".formatted(((CanHaveDesires) entity).getCurrentDesire().type().toString()));

        result.put("desireObjectUUID", "NULL");
        if (entity instanceof CanHaveDesires && ((CanHaveDesires) entity).getCurrentDesire() != null)
            result.put("desireObjectUUID", "'%s'".formatted(((CanHaveDesires) entity).getCurrentDesire().object().getID()));

        result.put("brainPower ", "NULL");
        if (entity instanceof CanBeAskedQuestions)
            result.replace("brainPower", "NULL", "'%s'".formatted(((CanBeAskedQuestions) entity).getAvailableBrainPower()));

        result.put("medicalCondition", "NULL");
        if (entity instanceof HasMedicalCondition)
            result.replace("medicalCondition", "NULL", "'%s'".formatted(((HasMedicalCondition) entity).getMedicalCondition()));

        result.put("faceExpression", "NULL");
        if (entity instanceof HasFaceExpression && ((HasFaceExpression) entity).getFaceExpression() != null)
            result.replace("faceExpression", "NULL", "'%s'".formatted(((HasFaceExpression) entity).getFaceExpression().toString()));

        result.put("ridingObjectUUID ", "NULL");
        if (entity instanceof CanRide && ((CanRide) entity).isRiding())
            result.replace("ridingObjectUUID", "NULL", "'%s'".formatted(((CanRide) entity).getCurrentlyRides().getID()));

        result.put("ridingObjectClass", "NULL");
        if (entity instanceof CanRide && ((CanRide) entity).isRiding())
            result.replace("ridingObjectClass", "NULL", "'%s'".formatted(((CanRide) entity).getCurrentlyRides().getClass().getName()));

        result.put("hairLength", "NULL");
        if (entity instanceof Louis && ((Louis) entity).getFaceHairLength() != null)
            result.replace("hairLength","NULL","'%s'".formatted(((Louis)entity).getFaceHairLength().toString()));

        result.put("hairStyle", "NULL");
        if (entity instanceof Louis && ((Louis)entity).getHairStyle() != null)
            result.replace("hairStyle","NULL","'%s'".formatted(((Louis)entity).getHairStyle().toString()));

        result.put("saturation", "NULL");
        if (entity instanceof CanConsumeFood)
            result.replace("saturation", "NULL", "'%s'".formatted(((CanConsumeFood) entity).getSaturation()));

        return result;
    }


    @Override
    public void save(Person entity) {
        var connection = getSession().getConnection();

        var columns = getColumns(entity);


        if (!columns.get("favoriteFoodUUID").equals("NULL")) {
            foodManager.save(((HasFavoriteFood) entity).getFavoriteFood());
        }


        String insertCommand =
                "INSERT INTO %s (\"uuid\", \"name\", \"sceneUUID\", \"locationUUID\", \"locationPosition\", \"favoriteFoodUUID\",".formatted(Table.PEOPLE) +
                " \"dead\", \"desireType\", \"desireObjUUID\", \"brainPower\", \"medicalCondition\", \"faceExpression\", \"ridingObjUUID\", \"ridingObjClass\", \"hairLength\", \"hairStyle\", \"saturation\") " +

                "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);".formatted(
                        columns.get("uuid"),
                        columns.get("name"),
                        columns.get("sceneUUID"),
                        columns.get("locationUUID"),
                        columns.get("locationPosition"),
                        columns.get("favoriteFoodUUID"),
                        columns.get("dead"),
                        columns.get("desireType"),
                        columns.get("desireObjUUID"),
                        columns.get("brainPower"),
                        columns.get("medicalCondition"),
                        columns.get("faceExpression"),
                        columns.get("ridingObjUUID"),
                        columns.get("ridingObjClass"),
                        columns.get("hairLength"),
                        columns.get("hairStyle"),
                        columns.get("saturation")
                );

        var moods = new HashMap<String, String>();
        var entityMoods = entity.getCurrentMoods();
        for (var m: ActionMood.values()) moods.put(m.toString(), entityMoods.contains(m) ? "TRUE" : "FALSE");

        String insertMoods =
                "INSERT INTO %s (\"uuid\", \"Default\", \"Silent\", \"Patiently\", \"WithoutComplains\", ".formatted(Table.MOODS) +
                "\"Screaming\", \"Roughly\", \"Shocked\", \"ConcentratedInsanity\", \"Calm\") " +
                "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s);".formatted(
                        columns.get("uuid"),
                        moods.get("Default"),
                        moods.get("Silent"),
                        moods.get("Patiently"),
                        moods.get("WithoutComplains"),
                        moods.get("Screaming"),
                        moods.get("Roughly"),
                        moods.get("Shocked"),
                        moods.get("ConcentratedInsanity"),
                        moods.get("Calm")
                );

        try {
            Statement statement = connection.createStatement();
            statement.execute(insertCommand);
            statement.execute(insertMoods);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (entity instanceof CanWearClothes && !((CanWearClothes) entity).getCurrentClothes().isEmpty()) {
            ((CanWearClothes) entity).getCurrentClothes().forEach((c) -> clothesManager.save(c, entity.getID()));
        }
    }

    private void addClothes(CanWearClothes entity, String id) throws SQLException {
        var statement = connection.createStatement();
        String clothesQuery = "SELECT * FROM %s WHERE \"personUUID\" = '%s';".formatted(Table.CLOTHES, id);
        var clothesResult = statement.executeQuery(clothesQuery);
        while (clothesResult.next()) {
            entity.addClothingItem(new Clothes(
                    Color.valueOf(clothesResult.getString("color")),
                    Clothes.ClothingType.valueOf(clothesResult.getString("type"))
            ));
        }
    }

    private void addMoods(Person entity, String id) throws SQLException {
        var statement = connection.prepareStatement("select * from %s where uuid = '%s';".formatted(Table.MOODS, id));
        var moodsResult = statement.executeQuery();
        if (moodsResult.next()) {
            for (String mood : Arrays.stream(ActionMood.values()).map(ActionMood::toString).toList()) {
               if (moodsResult.getBoolean(mood)) entity.addNewMood(ActionMood.valueOf(mood));
            }
        }
    }

    @Override
    public Person findByID(String id) {
        String query = "SELECT * FROM %s WHERE uuid = '%s';";
        Person resultEntity;
        try {
            var personStatement = connection.prepareStatement(query.formatted(Table.PEOPLE, id));
            var personResult = personStatement.executeQuery();

            if (!personResult.next()) {
                System.err.println("Person with uuid '" + id + "' not found. Returning null instead." );
                return null;
            }

            var name = personResult.getString("name");
            resultEntity =
            switch (name) {
                case "Louis" -> {
                   var louis = new Louis();
                   var hairStyle = HairStyle.valueOf(personResult.getString("hairStyle"));
                   var hairLength = personResult.getString("hairLength").equals("NULL") ?
                           HairLength.Default : HairLength.valueOf(personResult.getString("hairLength"));
                   var saturation = Integer.parseInt(personResult.getString("saturation"));
                   louis.setSaturation(saturation);
                   if (hairLength.equals(HairLength.Short)) louis.shave();
                   louis.styleHair(hairStyle);

                   String savedDesireType = personResult.getString("desireType");
                   if (savedDesireType != null) {
                       var desireType = DesireType.valueOf(savedDesireType);

                       var desireObjUUID = personResult.getString("desireObjUUID");
                       HasUUID desireObj = null;
                       if (isInTable(desireObjUUID, Table.FOOD))    desireObj = foodManager.findByID(desireObjUUID);
                       if (isInTable(desireObjUUID, Table.CLOTHES)) desireObj = clothesManager.findByID(desireObjUUID);
                       louis.desire(new Desire(desireType, desireObj));
                   }

                   addClothes(louis, id);
                   yield louis;
                }
                case "Gage" -> {
                    Food favoriteFood = null;
                    if (isInTable(personResult.getString("favoriteFoodUUID"), Table.FOOD))
                        favoriteFood = foodManager.findByID(id);

                    var gage = new Gage(favoriteFood);
                    gage.setFaceExpression(FaceExpression.valueOf(personResult.getString("faceExpression")));
                    if (Boolean.getBoolean(personResult.getString("dead"))) gage.die();
                    addClothes(gage, id);

                    if (!personResult.getString("ridingObjUUID").equals("NULL"))
                        gage.ride(new Sledge());
                    yield gage;
                }
                case "Rachel" -> {
                    var rachel = new Rachel();
                    rachel.setAvailableBrainPower(personResult.getInt("brainPower"));
                    rachel.setMedicalCondition(personResult.getInt("medicalCondition"));
                    yield rachel;
                }
                case "Ellie" -> {
                    var ellie = new Ellie();
                    addClothes(ellie, id);
                    ellie.setSaturation(personResult.getInt("saturation"));
                    ellie.setMedicalCondition(personResult.getInt("medicalCondition"));
                    if (!personResult.getString("ridingObjUUID").equals("NULL")) ellie.ride(new Sledge());
                    ellie.setFaceExpression(FaceExpression.valueOf(personResult.getString("faceExpression")));
                    yield ellie;
                }
                case "Steve" -> new Steve();
                case "Jud"   -> new Jud();
                default      -> null;
            };
        assert resultEntity != null: "Unknown character " + name;
        addMoods(resultEntity, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultEntity;
    }

    @Override
    public void update(Person entity) {
        try {
            if (!isInTable(entity.getID(), Table.PEOPLE)) {
                System.err.printf("Could not find person with uuid '%s' to update. Ignoring", entity.getID());
                return;
            }
            if (entity instanceof CanWearClothes e) {
                var removeClothesCommand = "UPDATE %s SET \"personUUID\" = NULL WHERE \"personUUID\" = '%s';".formatted(
                        Table.CLOTHES, entity.getID()
                );
                connection.prepareStatement(removeClothesCommand).execute();

                e.getCurrentClothes().forEach((c) -> clothesManager.update(c, entity.getID()));
            }
            var columns = getColumns(entity);
            String updateCommand = ("UPDATE %s SET " +
                    "\"sceneUUID\" = %s, " +
                    "\"locationUUID\" = %s, " +
                    "\"locationPosition\" = %s, " +
                      "dead = %s, " +
                    "\"desireType\" = %s, " +
                    "\"desireObjUUID\" = %s, " +
                    "\"brainPower\" = %s, " +
                    "\"medicalCondition\" = %s, " +
                    "\"faceExpression\" = %s, " +
                    "\"ridingObjUUID\" = %s, " +
                    "\"hairLength\" = %s, " +
                    "\"hairStyle\" = %s, " +
                    "saturation = %s " +
                    "WHERE uuid = %s").formatted(

                    Table.PEOPLE,
                    columns.get("locationUUID"),
                    columns.get("sceneUUID"),
                    columns.get("locationPosition"),
                    columns.get("dead"),
                    columns.get("desireType"),
                    columns.get("desireObjUUID"),
                    columns.get("brainPower"),
                    columns.get("medicalCondition"),
                    columns.get("faceExpression"),
                    columns.get("ridingObjUUID"),
                    columns.get("hairLength"),
                    columns.get("hairStyle"),
                    columns.get("saturation"),
                    columns.get("uuid")
            );

            connection.prepareStatement(updateCommand).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Person entity) {
        String deleteQuery     = "DELETE FROM %s WHERE uuid = '%s';";
        String childUUIDsQuery = "SELECT favoriteFoodUUID, desireObjUUID, ridingObjUUID FROM %s WHERE uuid = '%s';";
        String deleteClothesQuery = "DELETE FROM %s WHERE \"personUUID\" = '%s';";
        try {
            var statement = connection.createStatement();
            statement.execute(deleteQuery.formatted(Table.PEOPLE, entity.getID()));

            var deleteChildStatement = connection.createStatement();
            statement.execute(deleteClothesQuery);

            var UUIDsResult = statement.executeQuery(childUUIDsQuery);
            if (UUIDsResult.next()) {
                deleteChildStatement.execute(deleteQuery.formatted(Table.FOOD, UUIDsResult.getString("favoriteFoodUUID")));
                deleteChildStatement.execute(deleteClothesQuery.formatted(Table.CLOTHES, entity.getID()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
