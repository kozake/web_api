package hoge.web.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection conn;

    public static void init() throws Exception {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:mem:play;MODE=MYSQL");

        conn.createStatement().execute(
                "CREATE TABLE employees (" +
                        "      id  NUMBER(11) DEFAULT NULL auto_increment PRIMARY KEY," +
                        "      firstName VARCHAR(56)," +
                        "      lastName VARCHAR(56)" +
                        "  );");

        new Employee("Shinichi", "Kozake").save();
        new Employee("Taro", "Yamada").save();
        new Employee("Jiro", "Suzuki").save();
        new Employee("Saburo", "Tanaka").save();
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void close() throws SQLException {
        conn.close();
    }
}
