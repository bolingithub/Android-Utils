package pers.bolin.androidutils.struct;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javolution.io.Struct;

/**
 * C语言的结构体(小端数据)
 * Created by H-Bolin on 2018/2/26.
 */

public class BaseStructOfC extends Struct {

    /**
     * 放入数据
     * 解决以下问题：
     * 1.当前的info和字节数据data是关联在一起的了。data的数据改变，其对应info的属性也会跟着改变。
     * 2.当前的data数据小于结构体info的大小时，info.setByteBuffer(b, 0);不会出错，但是引用到没有不够长度的数据时，就会出错了。
     *
     * @param data
     */
    public void putData(byte[] data) {
        int structLen = this.size();
        byte[] temp = new byte[structLen];
        if (data.length >= structLen) {
            System.arraycopy(data, 0, temp, 0, structLen);
        } else {
            // 少于当前的结构的大小，以0来补充
            System.arraycopy(data, 0, temp, 0, data.length);
        }
        ByteBuffer buff = ByteBuffer.wrap(temp);
        buff.order(this.byteOrder());
        this.setByteBuffer(buff, 0);
    }

    // 一定要加上这个，不然会出现对齐的问题
    @Override
    public boolean isPacked() {
        return true;
    }

    // 设置为小端数据
    @Override
    public ByteOrder byteOrder() {
        return ByteOrder.LITTLE_ENDIAN;
    }

}
