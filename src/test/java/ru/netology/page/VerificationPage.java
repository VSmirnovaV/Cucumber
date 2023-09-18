package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement button = $("[data-test-id='action-verify']");
    private SelenideElement error = $("[data-test-id='error-notification']");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerification (DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        button.click();
        return new DashboardPage();
    }
    public void codeInvalid() {
        error.shouldHave(text("Неверно указан код! Попробуйте ещё раз.")).shouldBe(visible, Duration.ofSeconds(15));
    }
}

