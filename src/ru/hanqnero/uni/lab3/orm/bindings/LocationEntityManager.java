package ru.hanqnero.uni.lab3.orm.bindings;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Preparations;
import ru.hanqnero.uni.lab3.people.Person;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class LocationEntityManager extends EntityManager<Location> {

    record Pair<K, V>(K key, V value) {}

    private final PersonEntityManager personManager = new PersonEntityManager(getSession());

    protected LocationEntityManager(Session session) {
        super(session);
    }

    private HashMap<String,String> getColumns(Location entity) {
        var result = new HashMap<String,String >();
        result.put("uuid","'%s'".formatted(entity.getID()));
        result.put("class","'%s'".formatted(entity.getClass().getName()));
        result.put("type","'%s'".formatted(entity.getType().toString()));

        if (entity instanceof Preparations pr)
            result.put("completeness", "%f".formatted(pr.getCompleteness()));

        return result;
    }

    @Override
    public void save(Location entity) {
        var columns = getColumns(entity);
        String command = "INSERT INTO %s (uuid, class, type, completeness)".formatted(Table.LOCATION) +
                "VALUES (%s, %s, %s, %s);".formatted(
                        columns.get("uuid"),
                        columns.get("class"),
                        columns.get("type"),
                        columns.get("completeness")
                );
        try {

            for (Person p : entity.getCharacters()) personManager.save(p);

            var statement = connection.prepareStatement(command);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private LinkedList<Pair<Person, Location.Position>> findAllInLocation(String locationUUID) throws SQLException {
        var result = new LinkedList<Pair<Person, Location.Position>>();

        String query = "SELECT uuid, \"locationPosition\" FROM %s WHERE \"locationUUID\" = '%s';".formatted(
                Table.PEOPLE, locationUUID
        );

        var resultSet = connection.prepareStatement(query).executeQuery();

        while (resultSet.next()) {
            var position = Location.Position.valueOf(resultSet.getString("locationPosition"));
            var person   = personManager.findByID(resultSet.getString("uuid"));
            result.add(new Pair<>(person, position));
        }
        return result;
    }

    @Override
    public Location findByID(String id) {
        Location result;

        String query = "SELECT * FROM %s WHERE uuid = '%s';".formatted(Table.LOCATION, id);
        try {
            var resultSet = connection.prepareStatement(query).executeQuery();
            if (!resultSet.next()) return null;

            Location.Type type = Location.Type.valueOf(resultSet.getString("type"));

            result = switch (resultSet.getString("class")) {
                case "ru.hanqnero.uni.lab3.abstractions.environment.location" -> new Location(type);
                case "ru.hanqnero.uni.lab3.environment.abstractions.Preparations" -> {
                    var p = new Preparations(type);
                    p.workOnCompletion(resultSet.getFloat("completeness"));
                    yield p;
                }
                default -> throw new IllegalArgumentException(
                        "Type '%s' is not supported by LocationEntityManager".formatted(resultSet.getString("class")));
            };

            for (var pair : findAllInLocation(id)) {
                result.addCharacter(pair.key(), pair.value());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(Location entity) {
        try {
            if (!isInTable(entity.getID(), Table.LOCATION)) {
                System.err.printf("Could not find location with uuid '%s' to update. Ignoring", entity.getID());
                return;
            }
            if (!(entity instanceof Preparations p)) return;
            String updateCommand = "UPDATE %s SET completeness = %s;".formatted(
                    Table.LOCATION, p.getCompleteness()
            );

            connection.prepareStatement(updateCommand).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Location entity) {
        String deleteCommand = "DELETE FROM %s WHERE uuid = '%s';".formatted(
                Table.LOCATION, entity.getID()
        );
        entity.getCharacters().forEach(personManager::delete);
        try {
            connection.prepareStatement(deleteCommand).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
