package com.wind.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类
 * @author follow
 *
 */
public class Md5Util {
	
	
	private Md5Util() {
    }

    public static final String MD5 = "MD5";


    /**
     * 获得字符串的md5值
     *
     * @param str 待加密的字符串
     * @return md5加密后的字符串
     */
    public static String getMD5String(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(MD5);
            byte[] bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            return HexUtil.bytes2Hex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
