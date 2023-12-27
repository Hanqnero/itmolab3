package ru.hanqnero.uni.lab3.orm.bindings;

import ru.hanqnero.uni.ORM.Session;
import ru.hanqnero.uni.ORM.EntityManager;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.people.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

public class PersonEntityManager extends EntityManager<Person>{

    public final String TABLE = "people_table";
    public final String MOODS_TABLE = "people_moods";

    protected PersonEntityManager(Session session, String table) {
        super(session, table);
    }
    @Override
    public void save(Person entity) {
        try {
            var connection = getSession().getConnection();

            var id = "'%s'".formatted(entity.getId());
            var name = "'%s'".formatted(entity.getName());

            String sceneId = "NULL";
            String locationId = "NULL";
            if (entity.getCurrentScene() != null) sceneId = entity.getCurrentScene().getID();
            if (sceneId != null && entity.getCurrentScene().getLocation() != null)
                locationId = entity.getCurrentScene().getLocation().getID();

            String locationPosition = "NULL";
            if (entity.getCurrentScene() != null && entity.getCurrentScene().getLocation() != null) {
                var location = entity.getCurrentScene().getLocation();
                for (Location.Position pos: Location.Position.values()) {
                    if (location.isPresent(entity, pos)) {
                        locationPosition = pos.toString();
                        break;
                    }
            }
            }

            String favoriteFoodUUID = "NULL";
            if (entity instanceof HasFavoriteFood)
                favoriteFoodUUID = "'%s'".formatted(((HasFavoriteFood) entity).getFavoriteFood().getID());

            String dead = "NULL";
            if (entity instanceof CanBeDead) dead =
                    switch (((CanBeDead) entity).getLifeStatus()) {
                        case Alive -> "TRUE";
                        case Dead  -> "FALSE";
                    };

            String desireType = "NULL";
            if (entity instanceof CanHaveDesires && ((CanHaveDesires) entity).getCurrentDesire() != null)
                desireType = "'%s'".formatted(((CanHaveDesires) entity).getCurrentDesire().type().toString());

            String desireObjectID = "NULL";
            if (entity instanceof CanHaveDesires && ((CanHaveDesires) entity).getCurrentDesire() != null)
                desireObjectID = "'%s'".formatted(((CanHaveDesires) entity).getCurrentDesire().object().getID());

            String brainPower = "NULL";
            if (entity instanceof CanBeAskedQuestions)
                brainPower = "'%s'".formatted(((CanBeAskedQuestions) entity).getAvailableBrainPower());

            String medicalCondition = "NULL";
            if (entity instanceof HasMedicalCondition)
                medicalCondition = "'%s'".formatted(((HasMedicalCondition) entity).getMedicalCondition());

            String faceExpression = "NULL";
            if (entity instanceof HasFaceExpression && ((HasFaceExpression) entity).getFaceExpression() != null)
                faceExpression = "'%s'".formatted(((HasFaceExpression) entity).getFaceExpression().toString());

            String ridingObjectID = "NULL";
            if (entity instanceof CanRide && ((CanRide) entity).isRiding())
                ridingObjectID = "'%s'".formatted(((CanRide) entity).getCurrentlyRides().getID());

            String ridingObjectType = "NULL";
            if (entity instanceof CanRide && ((CanRide) entity).isRiding())
                ridingObjectType = "'%s'".formatted(((CanRide) entity).getCurrentlyRides().getClass().toString());


            String insertCommand =
                    "INSERT INTO %s (uuid, name, sceneUUID, locationUUID, locationPos, favoriteFoodUUID,".formatted(TABLE) +
                    " dead, desireType, desireObjUUID, brainPower, medicalCondition, faceExpression, ridingObjUUID, ridingObjType)" +

                    "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)".formatted(
                        id, name, sceneId, locationId, locationPosition, favoriteFoodUUID, dead, desireType,
                        desireObjectID, brainPower, medicalCondition, faceExpression, ridingObjectID, ridingObjectType
                    );

            var moods = new LinkedList<String>();
            for (var m: ActionMood.values()) {
                if (entity.getCurrentMoods().contains(m))
                    moods.add("TRUE");
                else
                    moods.add("FALSE");
            }

            String insertMoods =
                    "INSERT INTO %s (uuid, Default, Silent, Patiently, WithoutComplains, ".formatted(MOODS_TABLE) +
                    "Screaming, Roughly, Shocked, ConcentratedInsanity, Calm)" +

                    "VALUES (%s, %s %s %s %s %s %s %s %s %s)".formatted(
                            id, moods.get(0), moods.get(1), moods.get(2), moods.get(3),
                            moods.get(4), moods.get(5), moods.get(6), moods.get(7), moods.get(8)
                    );

            Statement statement = connection.createStatement();
            statement.execute(insertCommand);
            statement.execute(insertMoods);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(long id) { //TODO: change signature to (String id)

    }

    @Override
    public void update(Person entity) { //TODO: change signature to (String id)

    }

    @Override
    public void delete(long id) { //TODO: change signature to (String id)

    }
}
