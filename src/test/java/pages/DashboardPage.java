package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.UserData;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private static final ElementsCollection popolnit = $$(withText("Пополнить"));
    private static final ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = ", баланс: ";
    private static final String balanceFinish = " р.";
//    private final SelenideElement idFirstCard = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0]");
//    private final SelenideElement idLastCard = $("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d]");

    private static final SelenideElement amount = $("[maxlength=\"14\"]");
    private static final SelenideElement numberCard = $("[maxlength=\"19\"]");
    private static final SelenideElement heading = $("[data-test-id=dashboard]");
    private static final SelenideElement pay = $("[data-test-id=action-transfer]");

    public DashboardPage() {heading.should(visible);}

//    UserData user = new UserData("5000");

    public static int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalanceFirstCard(text);
    }

    private static int extractBalanceFirstCard(String text) {

        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getLastCardBalance() {
        val text = cards.last().text();
        return extractBalanceLastCard(text);
    }

    private int extractBalanceLastCard(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


    public static int transferMoneyOnFirstCard(UserData.PayInfo payInfo) {
        popolnit.first().click();
        amount.setValue(payInfo.getAmount());
        numberCard.setValue(payInfo.getCardFirst());
        pay.click();
        heading.should(visible);
        return getFirstCardBalance();
    }
    public int TransferMoneyOnLastCard(UserData.PayInfo payInfo) {
        cards.last().click();
        amount.setValue(payInfo.getAmount());
        numberCard.setValue(payInfo.getCardFirst());
        pay.click();
        heading.should(visible);
        return getLastCardBalance();
    }
}
