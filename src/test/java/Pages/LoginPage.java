package Pages;

import Model.DatabaseHelper;
import com.codeborne.selenide.SelenideElement;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public AuthPage dataFilling() throws SQLException {

        loginField.setValue(DatabaseHelper.getUserLogin());
        passwordField.setValue("qwerty123");
        loginButton.click();
        return new AuthPage ();
    }
}