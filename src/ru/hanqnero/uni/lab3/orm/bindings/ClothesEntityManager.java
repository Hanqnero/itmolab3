package ru.hanqnero.uni.lab3.orm.bindings;

import ru.hanqnero.uni.lab3.environment.Clothes;
import ru.hanqnero.uni.lab3.environment.Color;
import ru.hanqnero.uni.lab3.people.CanWearClothes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ClothesEntityManager extends EntityManager<Clothes> {

    protected ClothesEntityManager(Session session) {
        super(session);
    }

    private HashMap<String, String> getColumns(Clothes entity) {
        HashMap<String, String> result = new HashMap<>();
        result.put("uuid", "'%s'".formatted(entity.getID()));
        result.put("color", "'%s'".formatted(entity.getColor().toString()));
        result.put("type", "'%s'".formatted(entity.getType().toString()));
        result.put("personUUID", "NULL");
        return result;
    }

    @Override
    public void save(Clothes entity) {
        save(entity, "NULL");
    }

    protected void save(Clothes entity, String personUUID) {
        var columns = getColumns(entity);
        String insertCommand =
                "INSERT INTO %s (\"uuid\", \"color\", \"type\", \"personUUID\")".formatted(Table.CLOTHES) +
                "VALUES (%s, %s, %s, %s)".formatted(
                        columns.get("uuid"),
                        columns.get("color"),
                        columns.get("type"),
                        "'%s'".formatted(personUUID)
                );
        try {
            var statement = connection.createStatement();
            statement.execute(insertCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Clothes findByID(String id) {

        String query = "SELECT * FROM %s WHERE \"uuid\" = '%s'".formatted(Table.CLOTHES, id);

        try {
            var statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            var type = Clothes.ClothingType.valueOf(result.getString("type"));
            var color = Color.valueOf(result.getString("color"));

            return new Clothes(color, type);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void update(Clothes entity) {
        System.err.println("Update operation called for clothes type. Clothes type is by itself immutable.");
    }

    protected void update(Clothes entity, String personUUID) {
        String command = "UPDATE %s SET \"personUUID\" = '%s' WHERE uuid = '%s';".formatted(
                Table.CLOTHES,
                personUUID,
                entity.getID()
        );
        try {
            connection.prepareStatement(command).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void update(Clothes entity, CanWearClothes person, boolean forceSet) {
        if (!(person instanceof HasUUID)) {
            throw new IllegalArgumentException(person.toString() + "doesn't have UUID");
        }
        String personUUID = "NULL";
        boolean personHasEntity =
                person.getCurrentClothes().stream()
                        .map(Clothes::getID)
                        .anyMatch((id) -> id.equals(entity.getID()));
        if (personHasEntity) personUUID = "'%s'".formatted(((HasUUID) person).getID());
        if (!personHasEntity && !forceSet) return;

        String updateStatement = "UPDATE %s SET \"personUUID\" = %s WHERE \"uuid\" = '%s';".formatted(
                Table.CLOTHES, personUUID, entity.getID()
        );
        try {
            var statement = connection.prepareStatement(updateStatement);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Clothes entity) {
        try {
            String deleteCommand = "DELETE FROM %s WHERE \"uuid\" = '%s';".formatted(Table.CLOTHES, entity.getID());
            var statement = connection.prepareStatement(deleteCommand);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
