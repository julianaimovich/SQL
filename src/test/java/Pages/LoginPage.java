package Pages;
import Model.User;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private static SelenideElement loginField = $("[data-test-id=login] input");
    private static SelenideElement passwordField = $("[data-test-id=password] input");
    private static SelenideElement loginButton = $("[data-test-id=action-login]");

    public static AuthPage dataFilling() throws SQLException {

        val userSQL = "SELECT * FROM users WHERE login='vasya';";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
        ) {
            val firstUser = runner.query(conn, userSQL, new BeanHandler<>(User.class));
            loginField.setValue(firstUser.getLogin());
            passwordField.setValue("qwerty123");
            loginButton.click();
            return new AuthPage ();
        }
    }
}