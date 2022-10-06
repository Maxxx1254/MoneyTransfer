package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement pay = $("[maxlength=\"14\"]");
    private final SelenideElement numberCard = $("[maxlength=\"19\"]");
    private final SelenideElement button = $("[data-test-id=action-transfer]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void getTransferMoneyOnFirstCard(UserData.Amount amount) {
        pay.setValue(amount.getAmount());
        numberCard.setValue(UserData.getPayInfo().getCardLast());
        button.click();
    }

    public void getTransferMoneyOnLastCard(UserData.Amount amount) {
        pay.setValue(amount.getAmount());
        numberCard.setValue(UserData.getPayInfo().getCardFirst());
        button.click();
        new DashboardPage();
    }

    public void getTransferMoneyOnLastCardUpLimit(UserData.Amount amount) {
        pay.setValue(amount.getOverAmount());
        numberCard.setValue(UserData.getPayInfo().getCardLast());
        button.click();
        error();
    }

    public void error() {
        errorNotification.should(Condition.visible).should(text("Операция невозможна! На карте не достаточно средств."));
    }
}
