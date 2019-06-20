package com.bw.weidushop.util;

import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * com.baway.rxretrofitmvpdemo.util
 *
 * @author 李宁康
 * @date 2019 2019/05/24 11:39
 */
public class AESUtil  {
    public static String decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
          /*  SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
           // byte[] result = cipher.doFinal(content);*/
            return Base64.encodeToString(enCodeFormat,1) ;// 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
