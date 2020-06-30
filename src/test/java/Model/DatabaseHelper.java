package Model;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private DatabaseHelper() {
    }

    public static String getUserLogin() throws SQLException {

        val userSQL = "SELECT * FROM users WHERE login='vasya';";
        val runner = new QueryRunner();

        User firstUser;
        String firstUserLogin;
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
        ) {
            firstUser = runner.query(conn, userSQL, new BeanHandler<>(User.class));
            firstUserLogin = firstUser.getLogin();
        }
        return firstUserLogin;
    }

    public static String getVerificationCode() throws SQLException {

        val codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        String authCode;
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
        ) {
            authCode = runner.query(conn, codeSQL, new ScalarHandler<>());
        }
        return authCode;
    }

    public static void cleanDB () throws SQLException {

        val cleanSQL = "DELETE FROM auth_codes;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
        ) {
            val cleaning = runner.update(conn, cleanSQL);
        }
    }
}