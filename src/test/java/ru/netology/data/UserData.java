package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@AllArgsConstructor
@Data
public class UserData {
    @Value
    public static class PayInfo {
        String cardFirst;
        String cardLast;
        String idFirstCard;
        String idLastCard;
    }

    @Value
    public  static class Amount {
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

    public static UserData.Amount getAmount() {
        return new UserData.Amount("5000", "15000");
    }

    public static UserData.PayInfo getPayInfo() {
        return new UserData.PayInfo("5559 0000 0000 0001", "5559 0000 0000 0002", "92df3f1c-a033-48e6-8390-206f6b1f56c0", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");}
}
