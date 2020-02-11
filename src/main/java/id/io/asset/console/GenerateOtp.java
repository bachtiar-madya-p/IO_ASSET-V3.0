/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.io.asset.console;

import java.util.Random;

/**
 *
 * @author permadi
 */
public class GenerateOtp {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(generateOTP(6));
        }

    }

    private static String generateOTP(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[length];

        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        String result = String.valueOf(otp);
        return result;
    }

}
