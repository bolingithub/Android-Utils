package pers.bolin.androidutils.bytes;

import java.util.Arrays;

/**
 * 字节工具
 * Created by LanHe-Android on 2017/6/5.
 */
public class BytesUtil {

    /**
     * 从一个byte[]数组中截取一部分,注意不能截取超过src数组的长度
     *
     * @param src   目的数组
     * @param begin 开始位置 从0开始
     * @param count 截取的长度，包含begin本身的长度
     * @return byte[] 截取的数组
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] temp = new byte[count];
        if (!((begin + count) > src.length)) {
            System.arraycopy(src, begin, temp, 0, temp.length);
        } else {
            throw new IllegalArgumentException("subBytes截取的长度过长");
        }
        return temp;
    }

    /**
     * 拼接字节
     *
     * @param first
     * @param rest
     * @return
     */
    public static byte[] concatArrays(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }


    /**
     * 小端数据的2个byte数组转换int
     *
     * @return
     */
    public static int twoBytesOfLittleEndianToInt(byte[] src) {
        if (src.length != 2) {
            throw new IllegalArgumentException("twoBytesOfLittleEndianToInt需要转换的数据长度不为2");
        }
        return ((src[1] << 8) & 0xff00) | (src[0] & 0xff);
    }

    /**
     * 小端数据的4个byte数组转换int
     *
     * @return
     */
    public static int fourBytesOfLittleEndianToInt(byte[] src) {
        if (src.length != 4) {
            throw new IllegalArgumentException("fourBytesOfLittleEndianToInt需要转换的数据长度不为4");
        }
        return ((src[3] << 24) & 0xff000000) | ((src[2] << 16) & 0xff0000) | ((src[1] << 8) & 0xff00) | (src[0] & 0xff);
    }

    /**
     * 大端数据的2个byte数组转换int
     *
     * @param data
     * @return
     */
    public static int twoBytesOfBigEndianToInt(byte[] data) {
        if (data.length != 2) {
            throw new IllegalArgumentException("twoBytesOfBigEndianToInt需要转换的数据长度不为2");
        }
        return ((data[0] << 8) & 0xff00) | (data[1] & 0xff);
    }

    /**
     * 大端数据的4个byte数组转换int
     *
     * @return
     */
    public static int fourBytesOfBigEndianToInt(byte[] src) {
        if (src.length != 4) {
            throw new IllegalArgumentException("fourBytesOfLittleEndianToInt需要转换的数据长度不为4");
        }
        return ((src[0] << 24) & 0xff000000) | ((src[1] << 16) & 0xff0000) | ((src[2] << 8) & 0xff00) | (src[3] & 0xff);
    }

    /**
     * 将int 转化为2个小端的字节
     *
     * @param src
     * @return
     */
    public static byte[] intToTwoBytesOfLittleEndian(int src) {
        byte[] res = new byte[2];
        res[1] = (byte) ((src >> 8) & 0xff);
        res[0] = (byte) (src & 0xff);
        return res;
    }

    /**
     * 将int 转化为4个小端的字节
     *
     * @param src
     * @return
     */
    public static byte[] intToFourBytesOfLittleEndian(int src) {
        byte[] res = new byte[4];
        res[3] = (byte) ((src >> 24) & 0xff);
        res[2] = (byte) ((src >> 16) & 0xff);
        res[1] = (byte) ((src >> 8) & 0xff);
        res[0] = (byte) (src & 0xff);
        return res;
    }

    /**
     * 将int 转化为2个大端的字节
     *
     * @param src
     * @return
     */
    public static byte[] intToTwoBytesOfBigEndian(int src) {
        byte[] res = new byte[2];
        res[0] = (byte) ((src >> 8) & 0xff);
        res[1] = (byte) (src & 0xff);
        return res;
    }

    /**
     * 将int 转化为4个大端的字节
     *
     * @param src
     * @return
     */
    public static byte[] intToFourBytesOfBigEndian(int src) {
        byte[] res = new byte[4];
        res[0] = (byte) ((src >> 24) & 0xff);
        res[1] = (byte) ((src >> 16) & 0xff);
        res[2] = (byte) ((src >> 8) & 0xff);
        res[3] = (byte) (src & 0xff);
        return res;
    }

}
