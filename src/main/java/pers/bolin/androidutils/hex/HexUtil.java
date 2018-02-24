package pers.bolin.androidutils.hex;

/**
 * 16进制工具
 */
public class HexUtil {

    /**
     * 16进制字符串转换为byte[]
     *
     * @param hexString 16进制字符串
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.isEmpty()) {
            throw new IllegalArgumentException("需要转化的数据为null");
        }
        hexString = hexString.toUpperCase().replace(" ", ""); // 转换为大写，去掉空格
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("需要转化的长度不为偶数");
        }
        if (!isHexString(hexString)) {
            throw new IllegalArgumentException("需要转化的长度不为有效的16进制数据");
        }

        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 将字节数组转换为16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        if (src == null || src.length <= 0) {
            throw new IllegalArgumentException("需要转化的数据为null");
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 将字节数组转换为16进制字符串之间添加空格
     * 一般为调试使用
     *
     * @param src
     * @return
     */
    public static String bytesToHexStringWithSpace(byte[] src) {
        if (src == null || src.length <= 0) {
            throw new IllegalArgumentException("需要转化的数据为null");
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv + " ");
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 是否是16进制数据
     *
     * @param src
     * @return
     */
    public static boolean isHexString(String src) {
        String regex = "^[A-Fa-f0-9]+$";
        return src.matches(regex);
    }

}
