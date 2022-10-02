package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    public void setup() {open("http://localhost:9999/");}

    @Test
    void shouldTransferMoneyBetweenFirstCards() {
        var loginPage =new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int expected = dashboardPage.finishBalanceFirstCard();
        dashboardPage.transferMoneyOnFirstCard(DataHelper.getAmount());
        assertEquals( expected , dashboardPage.getFirstCardBalance());
    }

    @Test
    void shouldTransferMoneyBetweenLastCards() {
        var loginPage =new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int expected = dashboardPage.finishBalanceLastCard();
        dashboardPage.transferMoneyOnLastCard(DataHelper.getAmount());
        assertEquals(expected, dashboardPage.getLastCardBalance());
    }

    @Test
    void shouldTransferMoneyIfOverLimit() {
        var loginPage =new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        dashboardPage.transferMoneyOnLastCardUpLimit(DataHelper.getAmount());
        var transferPage = new TransferPage();
        assertEquals("Операция невозможна! На карте не достаточно средств.", transferPage.error());
    }
}
