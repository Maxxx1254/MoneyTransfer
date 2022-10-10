package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection popolnit = $$(withText("Пополнить"));
    private final ElementsCollection cards = $$("[class='list__item']");
    private final String balanceStart = ", баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard]");

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalanceCard(text);
    }

    public int getLastCardBalance() {
        val text = cards.last().text();
        return extractBalanceCard(text);
    }

    private int extractBalanceCard(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage transferMoneyOnFirstCard(String amount) {
        heading.should(visible);
        popolnit.first().click();
        return new TransferPage();
    }

    public TransferPage transferMoneyOnLastCard(String amount) {
        heading.should(visible);
        popolnit.last().click();
        return new TransferPage();
    }
}
