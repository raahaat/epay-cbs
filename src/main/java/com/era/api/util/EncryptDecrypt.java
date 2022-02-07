package com.era.api.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class EncryptDecrypt {

    public static String decryptWithIV(String encrypted,String key)
            throws GeneralSecurityException, UnsupportedEncodingException {
        try {
            IvParameterSpec iv = new IvParameterSpec(key.substring(0, 16).getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.substring(0, 16).getBytes("UTF-8"), "AES");
            byte[] ciphertextBytes = Base64.getDecoder().decode(encrypted.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(ciphertextBytes);
            return new String(original);
        }catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
            return "1";
        }
    }


 public static String encrypt(String key, String value) {

    try {
        IvParameterSpec iv = new IvParameterSpec(key.substring(0, 16).getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.substring(0, 16).getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return null;
}
}
