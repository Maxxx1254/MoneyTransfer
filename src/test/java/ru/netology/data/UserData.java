package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@AllArgsConstructor
@Data
public class UserData {

    @Value
    public static class CardNumber {
        String cardNumberFirst;
        String cardNumberLast;
    }

    @Value
    public static class Amount {
        String amount;
        String overAmount;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardNumber getCardNumber() {
        return new CardNumber("5559 0000 0000 0001", "5559 0000 0000 0002");
    }
}
