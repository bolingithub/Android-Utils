package pers.bolin.androidutils.bcd;


import pers.bolin.androidutils.hex.HexUtil;

/**
 * bcd工具
 *
 * @author LanHe-Android
 */
public class BcdUtil {

    /**
     * 将int 转化为bcd字节
     *
     * @param data
     * @param len  过长的话，前面的字节补0
     * @return
     */
    public static byte[] intToBcd(int data, int len) {
        byte[] result = new byte[len];
        byte cBR;
        for (int i = (len - 1); i >= 0; i--) {
            cBR = (byte) (data % 10);
            data = data / 10;
            result[i] = (byte) (data % 10);
            result[i] = (byte) (result[i] * 0x10 + cBR);
            data = data / 10;
        }
        return result;
    }

    /**
     * bcd字节转化为int
     *
     * @param data
     * @return
     */
    public static int bcdToInt(byte[] data) {
        if (data == null || data.length <= 0) {
            throw new IllegalArgumentException("参数为null或者length <= 0");
        }
        int result = 0;
        for (byte aData : data) {
            result = result * 100 + ((aData >> 4) & 0x0f) * 10 + (aData & 0x0f);
        }
        return result;
    }

    /**
     * 转化为10进制的String
     *
     * @param src
     * @return
     */
    public static String bytesToBcdString(byte[] src) {
        // 不会出现ABCD
        return HexUtil.bytesToHexString(src);
    }

    /**
     * String 转 byte字节
     *
     * @param asc
     * @return
     */
    public static byte[] stringToBcd(String asc) {
        if (asc == null || asc.isEmpty()) {
            throw new IllegalArgumentException("参数为null或者isEmpty");
        }
        // 去掉空格
        asc = asc.replace(" ", "");
        if (asc.length() % 2 != 0) {
            asc = "0" + asc;
        }
        if (!isDecimalString(asc)) {
            throw new IllegalArgumentException("不是有效的10进制数据");
        }

        byte abt[] = asc.getBytes();
        byte bbt[] = new byte[abt.length / 2];
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }


    /**
     * 是否是10进制数
     *
     * @param decimalStr
     * @return
     */
    public static boolean isDecimalString(String decimalStr) {
        String regex = "^[0-9]+$";
        return decimalStr.matches(regex);
    }
}
