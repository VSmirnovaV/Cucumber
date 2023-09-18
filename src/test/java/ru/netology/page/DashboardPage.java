package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;


import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final ElementsCollection card = $$(".list__item");

    public void verifyDashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int index) {
        var text = card.get(index - 1).text();
        return extractBalance(text);
    }

    public TransferPage selectCard(int index) {
        card.get(index - 1).$("button").click();
        return new TransferPage();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        return Integer.parseInt(text.substring(start + balanceStart.length(), finish));
    }
}
