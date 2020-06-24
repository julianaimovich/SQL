package Pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.*;

public class AuthPage {

    private static SelenideElement codeField = $("[data-test-id=code] input");
    private static SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public static void successAuth() throws SQLException {

        codeField.waitUntil(Condition.visible, 5000);

        val codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/app", "app", "pass"
                );
        ) {
            val authCode = runner.query(conn, codeSQL, new ScalarHandler<>());
            codeField.setValue((String) authCode);
            verifyButton.click();
        }
    }
}