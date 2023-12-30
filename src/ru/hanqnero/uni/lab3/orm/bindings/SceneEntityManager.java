package ru.hanqnero.uni.lab3.orm.bindings;

import ru.hanqnero.uni.lab3.environment.abstractions.Scene;
import ru.hanqnero.uni.lab3.environment.abstractions.TimeStamp;

import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;

public class SceneEntityManager extends EntityManager<Scene> {

    private final LocationEntityManager locationManager = new LocationEntityManager(getSession());

    protected SceneEntityManager(Session session) {
        super(session);
    }

    private HashMap<String,String> getColumns(Scene entity) {
        var result = new HashMap<String,String>();

        result.put("uuid", "'%s'".formatted(entity.getID()));
        result.put("locationUUID", entity.getLocation() == null ? "NULL" : "'%s'".formatted(entity.getLocation().getID()));

        boolean noTimestamp = entity.getTimestamp() == null;
        result.put("timestampType", noTimestamp ? "NULL" : "'%s'".formatted(entity.getTimestamp().type().toString()));
        result.put("timestampDate", noTimestamp ? "NULL" : "'%s'".formatted(entity.getTimestamp().date().toString()));
        result.put("timestampSpecialType", noTimestamp ? "NULL" : "'%s'".formatted(entity.getTimestamp().specialType().toString()));

        return result;
    }

    @Override
    public void save(Scene entity) {
        var columns = getColumns(entity);
        String command =
                "INSERT INTO %s (uuid, \"locationUUID\", \"timestampType\", \"timestampDate\", \"timestampSpecialType\") VALUES (%s, %s, %s, %s, %s)".formatted(
                        Table.SCENES,
                        columns.get("uuid"),
                        columns.get("locationUUID"),
                        columns.get("timestampType"),
                        columns.get("timestampDate"),
                        columns.get("timestampSpecialType")
        );
        try {
            if (entity.getLocation() != null) locationManager.save(entity.getLocation());
            connection.prepareStatement(command).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Scene findByID(String id) {
        String query = "SELECT * FROM %s WHERE uuid = '%s'".formatted(Table.SCENES, id);
        Scene result;
        try {
            var resultSet = connection.prepareStatement(query).executeQuery();

            if(!resultSet.next()) return null;

            result = new Scene();

            if (!resultSet.getString("locationUUID").equals("NULL")) {
                result.setLocation(locationManager.findByID(resultSet.getString("locationUUID")));
            }

            if (!resultSet.getString("timestampType").equals("NULL")) {
                TimeStamp.Type type = TimeStamp.Type.valueOf(resultSet.getString("timestampType"));
                Instant date = Instant.parse(resultSet.getString("timestampDate").replace(" ", "T")+"00Z");
                TimeStamp.SpecialType specialType = TimeStamp.SpecialType.valueOf(resultSet.getString("timestampSpecialType"));

                assert type != null && date != null && specialType != null: "Could not get timestamp data from db";
                result.setTimestamp(new TimeStamp(type, date, specialType));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(Scene entity) {
        var columns = getColumns(entity);
        String command =
                "UPDATE %s SET \"locationUUID\" = %s, \"timestampType\" = %s, \"timestampDate\" = %s, \"timestampSpecialType\" = %s WHERE uuid = '%s';".formatted(
                Table.SCENES,
                        columns.get("locationUUID"),
                        columns.get("timestampType"),
                        columns.get("timestampDate"),
                        columns.get("timestampSpecialType"),
                        entity.getID()
        );
        try {
            connection.prepareStatement(command).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(Scene entity) {
        String deleteCommand = "DELETE FROM %s WHERE uuid = '%s';".formatted(
                Table.SCENES, entity.getID()
        );
        if (entity.getLocation() != null)
            locationManager.delete(entity.getLocation());

        try {
            connection.prepareStatement(deleteCommand).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
