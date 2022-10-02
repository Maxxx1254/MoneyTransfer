package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    private final SelenideElement loginField = $("[name=\"login\"]");
    private final SelenideElement passwordField = $("[name=\"password\"]");
    private final SelenideElement loginButton = $("[class=\"button__text\"]");

    public VerificationPage validLogin(UserData.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}