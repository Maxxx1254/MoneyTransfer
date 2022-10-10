package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
        int expected = dashboardPage.getFirstCardBalance() + Integer.parseInt("5000");
        int expected2 = dashboardPage.getLastCardBalance() - Integer.parseInt("5000");
        dashboardPage.transferMoneyOnFirstCard("5000");
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnCard("5000", UserData.getCardNumber().getCardNumberLast());
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
        int expected = dashboardPage.getLastCardBalance() + Integer.parseInt("5000");
        int expected2 = dashboardPage.getFirstCardBalance() - Integer.parseInt("5000");
        dashboardPage.transferMoneyOnLastCard("5000");
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnCard("5000", UserData.getCardNumber().getCardNumberFirst());
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
        dashboardPage.transferMoneyOnLastCard("15000");
        var transferPage = new TransferPage();
        transferPage.getTransferMoneyOnCard("15000", UserData.getCardNumber().getCardNumberLast());
        $x(".//div[@class='money-input__currency']").should(text("Операция невозможна! На карте не достаточно средств."));
    }
}
