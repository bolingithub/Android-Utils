package pers.bolin.androidutils.qrcode;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具
 */
public class MyQRCodeUtil {

    /**
     * 生成二维码，转成Bitmap格式
     */
    public static Bitmap createQRCode(String text, int qrcodeWidth, int qrcodeHight) throws WriterException {
        return createQRCode(text, qrcodeWidth, qrcodeHight, false);
    }

    /**
     * 生成二维码，转成Bitmap格式
     */
    public static Bitmap createQRCode(String text, int qrcodeWidth, int qrcodeHight, boolean isHex) throws WriterException {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 容错率：容错率越高,二维码的有效像素点就越多.
        // 使用小写的编码，大写会出现]Q2\000026开头内容
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 16进制，会是得编码格式失效，使用时，需要检查数据是16进制数
        hints.put(EncodeHintType.HEX_DATA, isHex);
        // 去白色边框
        hints.put(EncodeHintType.MARGIN, 0);
        // 这边创建可能会抛出异常！
        BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHight, hints);

        // 转换成Bitmap格式
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < width; ++y) {
            for (int x = 0; x < height; ++x) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000; // black pixel
                } else {
                    pixels[y * width + x] = 0xffffffff; // white pixel
                }
            }
        }
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);
        return bmp;
    }
}
