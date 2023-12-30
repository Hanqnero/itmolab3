package ru.hanqnero.uni.lab3.orm.bindings;

import java.sql.Connection;
import java.sql.SQLException;

abstract public class EntityManager<T> {
    private final Session session;
    final Connection connection;

    boolean isInTable(String id, Table table) throws SQLException {
        var query = "SELECT uuid FROM %s WHERE uuid = '%s';".formatted(table, id);
        var statement = getSession().getConnection().prepareStatement(query);
        var resultSet = statement.executeQuery();
        return resultSet.next();
    }

    protected EntityManager(Session session) {
        this.session = session;
        connection = session.getConnection();
    }
    abstract public void save(T entity);
    abstract public T findByID(String id);
    abstract public void update(T entity);
    abstract public void delete(T entity);

    public Session getSession() {
        return session;
    }
}
