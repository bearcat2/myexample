package com.headerits.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * <p>Description:  AES 加密、解密算法 </p>
 * <p>Title: AesEncodeUtils </p>
 * <p>Create Time: 2018/6/26 17:06 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class AesEncodeUtils {

    /**
     * 编码方式
     */
    public static final String CODE_TYPE = "UTF-8";

    /**
     * 填充类型
     */
    public static final String AES_TYPE = "AES/ECB/PKCS5Padding";

    /**
     * 使用 aes 算法加密文本内容
     *
     * @param content 要加密的文本
     * @param aesKey  aes秘钥key
     * @return
     */
    public static String encrypt(String content, String aesKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(content.getBytes(CODE_TYPE));
            return new BASE64Encoder().encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 使用 aes 算法解密文本内容
     *
     * @param content 待解密的文本内容
     * @param aesKey  aes秘钥key
     * @return
     */
    public static String decrypt(String content, String aesKey) {
        try {
            byte[] byteMi = new BASE64Decoder().decodeBuffer(content);
            SecretKeySpec key = new SecretKeySpec(
                    aesKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, CODE_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 根据appId、appSecret、时间戳生成aes秘钥
     *
     * @param timestamp 时间戳
     * @return
     */
    public static String generateAesKey(long timestamp) {
        String appId = PropertiesUtils.getInstance().get("hd.lock-cloud.app-id");
        String appSecret = PropertiesUtils.getInstance().get("hd.lock-cloud.app-secret");
        StringBuffer sb = new StringBuffer(16);
        String[] timestamps = String.valueOf(timestamp).split("");
        for (int i = 2; i < timestamps.length; i++) {
            sb.append(appId.charAt(Integer.parseInt(timestamps[i]) % 8))
                    .append(appSecret.charAt(Integer.parseInt(timestamps[i]) % 8));
        }
        return sb.toString();
    }


    public static String generateAesKey(long timestamp, String secret) {
        String appId = PropertiesUtils.getInstance().get("hd.lock-cloud.app-id");
        StringBuffer sb = new StringBuffer(16);
        String[] timestamps = String.valueOf(timestamp).split("");
        for (int i = 2; i < timestamps.length; i++) {
            sb.append(appId.charAt(Integer.parseInt(timestamps[i]) % 8))
                    .append(secret.charAt(Integer.parseInt(timestamps[i]) % 8));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String demo = "bniIOBiyYAKdyzsjS0PVVQ==";
        long time = 1530761341153L;
        System.out.println(
                decrypt(
                        demo
                        , generateAesKey(time / 1000)
                )
        );
    }
}