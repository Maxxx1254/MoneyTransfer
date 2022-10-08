package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement pay = $("[maxlength=\"14\"]");
    private final SelenideElement numberCard = $("[maxlength=\"19\"]");
    private final SelenideElement button = $("[data-test-id=action-transfer]");

    public void getTransferMoneyOnCard(String amount, String cardNumber) {
        pay.setValue(amount);
        numberCard.setValue(cardNumber);
        button.click();
    }
}
