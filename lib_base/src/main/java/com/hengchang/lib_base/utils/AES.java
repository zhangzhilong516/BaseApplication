package com.hengchang.lib_base.utils;

import android.util.Base64;

import com.hengchang.lib_base.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zhangzhilong
 * @date 2019/4/30.
 * description：AES加密算法
 */
public final class AES {

    private static final String TAG = "AESCryptUtils";

    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String CHARSET = "UTF-8";

    private static final String HASH_ALGORITHM = "SHA-256";

    private static final byte[] ivBytes;

    private static final String aesKey;

    public static boolean DEBUG_LOG_ENABLED = BuildConfig.DEBUG;


    static {
        aesKey = DEBUG_LOG_ENABLED ? "testKey123456789" : "ProSx38cAi%^2^*b";
        ivBytes = "cashloan-api-par".getBytes();
    }


    /**
     * 生成Key
     */
    private static SecretKeySpec generateKey(final String aesKey) {

//        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
//        byte[] bytes = aesKey.getBytes("UTF-8");
//        digest.update(bytes, 0, bytes.length);
//        byte[] key = digest.digest();
//
//        log("SHA-256 key ", password);

        return new SecretKeySpec(aesKey.getBytes(), "AES");
    }

    /**
     * AES加密
     */
    public static String encrypt(String message) {
        try {
            return BuildConfig.DEBUG ? message : encrypt(aesKey, message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * AES加密
     */
    public static String encrypt(final String key, String message)
            throws GeneralSecurityException {
        try {
            final SecretKeySpec secretKeySpec = generateKey(key);

            byte[] cipherText = encrypt(secretKeySpec, ivBytes, message.getBytes(CHARSET));

            return Base64.encodeToString(cipherText, Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }


    /**
     * AES加密
     */
    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = cipher.doFinal(message);
        return cipherText;
    }


    /**
     * AES解密
     */
    public static String decrypt(String base64EncodedString) {
        try {
            return BuildConfig.DEBUG ? base64EncodedString : decrypt(aesKey, base64EncodedString);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * AES解密
     */
    public static String decrypt(final String aesKey, String base64EncodedString)
            throws GeneralSecurityException {

        try {
            final SecretKeySpec key = generateKey(aesKey);

            byte[] decodedCipherText = Base64.decode(base64EncodedString, Base64.NO_WRAP);

            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);

            return new String(decryptedBytes, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }

    /**
     * AES解密
     */
    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
            throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
        return decryptedBytes;
    }
}
