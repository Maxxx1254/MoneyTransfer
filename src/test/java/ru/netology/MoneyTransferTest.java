package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MoneyTransferTest {

    @BeforeEach
    public void setup() {
        open("http://localhost:9999/");
    }

    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

    @Test
    void shouldTransferMoneyBetweenFirstCards() {
        var loginPage = new LoginPage();
        var authInfo = UserData.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = UserData.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int expected = dashboardPage.getFirstCardBalance() + Integer.parseInt(UserData.getAmount().getAmount());
        int expected2 = dashboardPage.getLastCardBalance() - Integer.parseInt(UserData.getAmount().getAmount());
        dashboardPage.transferMoneyOnFirstCard(UserData.getAmount());
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnCard(UserData.getAmount().getAmount(), "5559 0000 0000 0002");
        assertArrayEquals(new int[]{expected, expected2}, new int[]{dashboardPage.getFirstCardBalance(), dashboardPage.getLastCardBalance()});
    }

    @Test
    void shouldTransferMoneyBetweenLastCards() {
        var loginPage = new LoginPage();
        var authInfo = UserData.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = UserData.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int expected = dashboardPage.getLastCardBalance() + Integer.parseInt(UserData.getAmount().getAmount());
        int expected2 = dashboardPage.getFirstCardBalance() - Integer.parseInt(UserData.getAmount().getAmount());
        dashboardPage.transferMoneyOnLastCard(UserData.getAmount());
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnCard(UserData.getAmount().getAmount(), "5559 0000 0000 0001");
        assertArrayEquals(new int[]{expected, expected2}, new int[]{dashboardPage.getFirstCardBalance(), dashboardPage.getLastCardBalance()});
    }

    @Test
    void shouldTransferMoneyIfOverLimit() {
        var loginPage = new LoginPage();
        var authInfo = UserData.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = UserData.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        dashboardPage.transferMoneyOnLastCard(UserData.getAmount());
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnCard(UserData.getAmount().getOverAmount(), "5559 0000 0000 0001");
        errorNotification.should(Condition.visible).should(text("Операция невозможна! На карте не достаточно средств."));
    }
}
