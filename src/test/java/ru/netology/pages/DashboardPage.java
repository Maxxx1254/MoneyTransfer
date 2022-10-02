package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;
import ru.netology.data.UserData;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private final ElementsCollection popolnit = $$(withText("Пополнить"));
    private final ElementsCollection cards = $$("[class='list__item']");
    private final String balanceStart = ", баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard]");

    static TransferPage transferPage = new TransferPage();


    public DashboardPage() {heading.should(visible);}

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalanceFirstCard(text);
    }

    private int extractBalanceFirstCard(String text) {

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


    public void transferMoneyOnFirstCard(UserData.Amount amount) {
        popolnit.first().click();
        transferPage.getTransferMoneyOnFirstCard(DataHelper.getAmount());
        heading.should(visible);
        getFirstCardBalance();
    }
    public void transferMoneyOnLastCard(UserData.Amount amount) {
        popolnit.last().click();
        transferPage.getTransferMoneyOnLastCard(DataHelper.getAmount());
        heading.should(visible);
        getLastCardBalance();
    }

    public void transferMoneyOnLastCardUpLimit(UserData.Amount overAmount) {
        popolnit.last().click();
        transferPage.getTransferMoneyOnLastCardUpLimit(DataHelper.getAmount());
    }


    public int finishBalanceFirstCard() {
        int startBalance = getFirstCardBalance();
        String k = DataHelper.getAmount().getAmount();
        int pay = Integer.parseInt(k);
        int finishBalance = startBalance + pay;
        return finishBalance;
    }
    public int finishBalanceLastCard() {
        int startBalance = getLastCardBalance();
        String k = DataHelper.getAmount().getAmount();
        int pay = Integer.parseInt(k);
        int finishBalance = startBalance + pay;
        return finishBalance;
    }
}
