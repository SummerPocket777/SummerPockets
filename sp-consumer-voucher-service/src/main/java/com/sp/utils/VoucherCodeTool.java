package com.sp.utils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class VoucherCodeTool {

    // 生成优惠券码
    public static String generateVoucherCode(int userId, int voucherId) {
        String a = String.format("%04d", userId) + String.format("%03d", voucherId) + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 进行加密
        SecretKey secretKey = generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        StringBuilder hexString = new StringBuilder();
        for (byte b : keyBytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return encrypt(a, secretKey) + hexString;
    }

    // 解析优惠券码
    public static String parseVoucherCode(String voucherCode) {
        String hexString = voucherCode.split("=")[1];
        // 转为对象
        byte[] keyBytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            String hexPair = hexString.substring(i, i + 2);
            keyBytes[i / 2] = (byte) Integer.parseInt(hexPair, 16);
        }
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        // 先解密
        return decrypt(voucherCode.split("=")[0], secretKey);
    }

    // 加密
    public static String encrypt(String plainText, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 解密
    public static String decrypt(String encryptedText, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 生成密钥
    public static SecretKey generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom());
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}