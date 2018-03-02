package pers.bolin.androidutils.bytes;

import java.util.Arrays;

/**
 * Created by H-Bolin on 2018/2/27.
 */

public class MyBytesOfCUtils {

    /**
     * 将src的数组拷贝到dest中
     *
     * @param dest
     * @param src
     * @param len
     */
    public static void memcpy(byte[] dest, byte[] src, int len) {
        System.arraycopy(src, 0, dest, 0, len);
    }

    public static void memcpy(byte[] dest, int destPos, byte[] src, int len) {
        System.arraycopy(src, 0, dest, destPos, len);
    }

    public static void memcpy(byte[] dest, byte[] src, int srcPos, int len) {
        System.arraycopy(src, srcPos, dest, 0, len);
    }

    public static void memcpy(byte[] dest, int destPos, byte[] src, int srcPos, int len) {
        System.arraycopy(src, srcPos, dest, destPos, len);
    }

    /**
     * 将src的数组填充值
     *
     * @param src
     * @param data
     * @param len
     */
    public static void memset(byte[] src, byte data, int len) {
        Arrays.fill(src, 0, len, data);
    }

    public static void memset(byte[] src, int offset, byte data, int len) {
        Arrays.fill(src, offset, len, data);
    }

}
