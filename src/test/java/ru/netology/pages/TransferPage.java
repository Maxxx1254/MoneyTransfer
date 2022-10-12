package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {

    private final SelenideElement pay = $("[maxlength=\"14\"]");
    private final SelenideElement numberCard = $("[maxlength=\"19\"]");
    private final SelenideElement button = $("[data-test-id=action-transfer]");

    private final SelenideElement error = $x(".//div[@class='money-input__currency']");

    public void getTransferMoneyOnCard(String amount, String cardNumber) {
        pay.setValue(amount);
        numberCard.setValue(cardNumber);
        button.click();
    }

    public String Error() {
        error.should(text("Операция невозможна! На карте не достаточно средств."));
        return String.valueOf(error);
    }
}
