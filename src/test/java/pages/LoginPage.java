package pages;

import com.codeborne.selenide.SelenideElement;
import data.UserData;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    private SelenideElement loginField = $("[name=\"login\"]");;
    private SelenideElement passwordField = $("[name=\"password\"]");
    private SelenideElement loginButton = $("[class=\"button__text\"]");

    public VerificationPage validLogin(UserData.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}