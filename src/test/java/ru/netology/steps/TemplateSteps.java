package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {
    private static DashboardPage dashboardPage;

    @Пусть("пользователь залогинился с логином {string} и паролем {string}")
    public void userAuthorization(String login, String password) {
        var LoginPage = open("http://localhost:9999", LoginPage.class);
        var verificationPage = LoginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerification(DataHelper.getVerificationCode());
    }
    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void validTransfer(String amount, String cardNumber, int cardSecond) {
        var transferPage = dashboardPage.selectCard(cardSecond);
        dashboardPage = transferPage.validTransfer(amount, cardNumber);
    }
    @Тогда("баланс его {} карты из списка на главной странице должен стать {} рублей")
    public void getBalance(int index, int expectedBalanceCard) {
       var actualBalanceCard =  dashboardPage.getCardBalance(index);
        assertEquals(expectedBalanceCard,actualBalanceCard);
    }

}
