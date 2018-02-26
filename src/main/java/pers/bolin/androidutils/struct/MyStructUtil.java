package pers.bolin.androidutils.struct;

import javolution.io.Struct;


public class MyStructUtil {

    /**
     * 将Unsigned8[]转成byte数组
     *
     * @param data
     * @return
     */
    public static byte[] Unsigned8ToBytes(Struct.Unsigned8[] data) {
        byte[] results = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            results[i] = (byte) data[i].get();
        }
        return results;
    }
}
