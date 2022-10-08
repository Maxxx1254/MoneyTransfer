package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private final SelenideElement code = $("[name='code']");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public DashboardPage validVerify(UserData.VerificationCode verificationCode) {
        code.shouldBe(visible);
        code.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
