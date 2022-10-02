package ru.netology.data;

import lombok.Value;

public class UserData {
    @Value
    public static class PayInfo {
        String cardFirst = "5559 0000 0000 0001";
        String cardLast = "5559 0000 0000 0002";
        String idFirstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idLastCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
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
}
