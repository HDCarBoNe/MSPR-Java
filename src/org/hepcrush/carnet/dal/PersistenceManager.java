package org.hepcrush.carnet.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class PersistenceManager {

    private static final String DRIVER_CLASS = "org.hepcrush.carnet.Driver";
    private static final String DB_URL= "jdbc:mariadb://localhost:3306/java";
    private static final String DB_LOGIN = "javauser";
    private static final String DB_PWD = "javapwd";
    private static Connection connection;
    private PersistenceManager() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (null == null || connection.isClosed()){
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(DB_URL,DB_LOGIN,DB_PWD);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if(null != connection && !connection.isClosed()){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("quelque chose");
                throw e;
            }
        }
    }
}
