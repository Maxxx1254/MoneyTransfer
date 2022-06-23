import data.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.VerificationPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {

    @BeforeEach
    public void setup() {open("http://localhost:9999/");}

    @Test
    void shouldTransferMoneyBetweenFirstCards() {
        var loginPage =new LoginPage();
        var authInfo = UserData.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = UserData.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage.transferMoneyOnFirstCard(UserData.getPayInfo());
        $(withText("15000")).should(appear);
    }

    @Test
    void shouldTransferMoneyBetweenLastCards() {
        var loginPage =new LoginPage();
        var authInfo = UserData.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = UserData.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        DashboardPage.transferMoneyOnFirstCard(UserData.getPayInfo());
        $(withText("15000")).should(appear);
    }
}
