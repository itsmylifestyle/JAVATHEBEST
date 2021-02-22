package data;

import data.inter.IDBManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDBManager {
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ilovejavabd", "postgres", "Cvut2020.");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
