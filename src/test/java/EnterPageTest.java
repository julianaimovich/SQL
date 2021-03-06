import Model.DatabaseHelper;
import Pages.LoginPage;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class EnterPageTest {

    @AfterEach
    public void cleaningDB () throws SQLException {
        DatabaseHelper.cleanDB();
    }

    @Test
    @DisplayName("Authorization should be successful")
    void authorizationShouldBeSuccessful () throws SQLException {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authPage = loginPage.dataFilling();
        authPage.successAuth();
        $(withText("Личный кабинет")).shouldBe(visible);
    }

    @Test
    @DisplayName("Error message should appear in case of 3 invalid codes")
    void errorMessageShouldAppearInCaseOf3InvalidCodes () throws SQLException {
        val faker = new Faker();

        int i = 1;
        while (i <= 3) {
            open("http://localhost:9999");
            SelenideElement codeField = $("[data-test-id=code] input");
            SelenideElement verifyButton = $("[data-test-id=action-verify]");
            val loginPage = new LoginPage();
            loginPage.dataFilling();
            codeField.setValue(faker.internet().password());
            verifyButton.click();
            i++;
        }
        $(byText("Ошибка!")).shouldBe(visible);
    }
}