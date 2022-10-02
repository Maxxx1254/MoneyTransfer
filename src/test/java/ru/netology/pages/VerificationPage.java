package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private final SelenideElement code = $("[name='code']");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    public VerificationPage() {   code.shouldBe(visible);  }

    public void validVerify(UserData.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        verifyButton.click();
        new DashboardPage();
    }
}
