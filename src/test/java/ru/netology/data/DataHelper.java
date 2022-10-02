package ru.netology.data;

import com.github.javafaker.Faker;

public class DataHelper {
    private static final Faker faker = new Faker();
    public static UserData.AuthInfo getAuthInfo() {return new UserData.AuthInfo("vasya", "qwerty123");}
    public static UserData.VerificationCode getVerificationCode() { return new UserData.VerificationCode("12345"); }
    public static UserData.Amount getAmount() { return new UserData.Amount( "5000", "15000"); }
    public static UserData.PayInfo getPayInfo() { return new UserData.PayInfo();}
//    public static String randomPay() {
//        int randomPay = faker.number().numberBetween(1, 10_000);
//        return String.valueOf(randomPay);
//    }
//    public static String randomOverPay() {
//        int randomOverPay = faker.number().numberBetween(20_000, 999_999);
//        return String.valueOf(randomOverPay);
//    }
}
