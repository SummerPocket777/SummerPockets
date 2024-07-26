package com.sp.config;

import java.util.Random;

public class VoucherCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 10;

    public static String generateVoucherCode() {
        Random random = new Random();
        StringBuilder voucherCode = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            voucherCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return voucherCode.toString();
    }
}
