package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    public void setup() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTransferMoneyBetweenFirstCards() {
        var loginPage = new LoginPage();
        var authInfo = UserData.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = UserData.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int expected = dashboardPage.getFirstCardBalance() + Integer.parseInt(UserData.getAmount().getAmount());
        dashboardPage.transferMoneyOnFirstCard(UserData.getAmount());
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnFirstCard(UserData.getAmount());
        assertEquals(expected, dashboardPage.getFirstCardBalance());
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
        dashboardPage.transferMoneyOnLastCard(UserData.getAmount());
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnLastCard(UserData.getAmount());
        assertEquals(expected, dashboardPage.getLastCardBalance());
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
        transferPage.getTransferMoneyOnLastCardUpLimit(UserData.getAmount());
    }
}
