package Pages;

import Model.DatabaseHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public void successAuth() throws SQLException {

        codeField.waitUntil(Condition.visible, 5000);
        codeField.setValue(DatabaseHelper.getVerificationCode());
        verifyButton.click();
    }
}