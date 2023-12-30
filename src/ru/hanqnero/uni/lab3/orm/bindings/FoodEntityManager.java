package ru.hanqnero.uni.lab3.orm.bindings;

import ru.hanqnero.uni.lab3.environment.Temperature;
import ru.hanqnero.uni.lab3.environment.food.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class FoodEntityManager extends EntityManager<Food> {

    public FoodEntityManager(Session session) {
        super(session);
    }

    private HashMap<String,String> getColumns(Food entity) {
        var result = new HashMap<String,String>();

        result.put("uuid", "'%s'".formatted(entity.getID()));
        result.put("class", "'%s'".formatted(entity.getClass().toString()));
        result.put ("saturation", "%d".formatted(entity.getSaturationChange()));
        result.put("taste", "'%s'".formatted(entity.getTaste().toString()));
        result.put("isConsumed", entity.isConsumed() ? "TRUE" : "FALSE");

        result.put("temperature", "NULL");
        if (entity instanceof HasTemperature e && e.getTemp()!=null)
            result.replace("temperature", "NULL",
                    "'%s'".formatted(((HasTemperature) entity).getTemp().toString()));

        result.put("cookedDegree", "NULL");
        if (entity instanceof FoodToCook e)
            result.replace("cookedDegree", "NULL",
                    "'%s'".formatted(e.getCookedDegree().toString())
            );


        return result;
    }

    @Override
    public void save(Food entity) {

        var columns = getColumns(entity);
        String insertCommand =
                "INSERT INTO %s (uuid, class, saturation, \"isConsumed\", temperature, \"cookedDegree\", taste)".formatted(Table.FOOD) +
                "VALUES (%s, %s, %s, %s, %s, %s, %s);".formatted(
                        columns.get("uuid"),
                        columns.get("class"),
                        columns.get("saturation"),
                        columns.get("isConsumed"),
                        columns.get("temperature"),
                        columns.get("cookedDegree"),
                        columns.get("taste")
                );

        try {
            connection.prepareStatement(insertCommand).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Food findByID(String id) {
        try {
            String query = "SELECT FROM %s WHERE uuid = '%s';".formatted(Table.FOOD.toString(), id);
            var statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.err.printf("Could not find entity in table %s with matching uuid '%s'%n", Table.FOOD, id);
                return null;
            }

            Food resultFood = null;
            switch (resultSet.getString("class")) {
                case "ru.hanqnero.uni.lab3.food.Breakfast" -> resultFood = new Breakfast(
                  Integer.parseInt(resultSet.getString("saturation")),
                  Taste.valueOf(resultSet.getString("taste"))
                );
                case "ru.hanqnero.uni.lab3.food.HotChocolate" -> resultFood = new HotChocolate(
                    Integer.parseInt(resultSet.getString("saturation")),
                    Taste.valueOf(resultSet.getString("taste")),
                    Temperature.valueOf(resultSet.getString("temperature"))
                );
            }
            return resultFood;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Food entity) {
        var columns = getColumns(entity);
        String command = "UPDATE %s SET consumed = %s, temperature = %s, \"cookedDegree\" = %s WHERE uuid = %s;".formatted(
                Table.FOOD,
                columns.get("consumed"),
                columns.get("temperature"),
                columns.get("cookedDegree"),
                columns.get("uuid")
        );
        try {
            connection.prepareStatement(command).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(Food entity) {
        String command = "DELETE FROM %s WHERE uuid = '%s';".formatted(Table.FOOD, entity.getID());
        try {
            connection.prepareStatement(command).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
