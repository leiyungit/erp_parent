package cn.itcast.erp.biz.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密类
 */
public class DataCrypto {

    /** 散列次数 */
    private static final int hashIterations = 2;

    /**
     * 加密
     * @param source
     * @param salt
     * @return
     */
    public static String encrypt(String source, String salt){
        /**
         * source  原始秘密
         * salt    盐
         * hashIterations  散列次数
         */
        Md5Hash md5 = new Md5Hash(source,salt,hashIterations);
        return md5.toString();
    }

    public static String decrypt(String source){
        return null;
    }
}
