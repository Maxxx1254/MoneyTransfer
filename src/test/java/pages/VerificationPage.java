package pages;

import com.codeborne.selenide.SelenideElement;
import data.UserData;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class VerificationPage {

    private final SelenideElement code = $("[name='code']");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    public VerificationPage() {   code.shouldBe(visible);  }

    public DashboardPage validVerify(UserData.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
