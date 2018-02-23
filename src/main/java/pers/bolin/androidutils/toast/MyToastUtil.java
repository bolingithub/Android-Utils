package pers.bolin.androidutils.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * toast工具
 */
public class MyToastUtil {

    private static Toast mToast;
    private static String mShowText;  // 上一次显示的文本内容

    /**
     * 默认保留上一次的文本不同的toast
     */
    private static boolean mIsKeep = true;

    /**
     * 是否保存上一次的文本内容
     *
     * @param isKeep
     */
    public static void IsKeepTheLastText(boolean isKeep) {
        MyToastUtil.mIsKeep = isKeep;
    }

    /**
     * 显示toast
     *
     * @param context 上下文对象
     * @param text    显示的文本
     */
    public static void showToast(Context context, String text) {
        if (mToast != null && mShowText != null && !mShowText.equals(text) && mIsKeep) {
            // 和上次的文本内容不一致，等待上次的显示完成之后再显示
            mToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            // 不保留就直接显示本次的文本
            if (mToast == null) {
                mToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
        }
        mToast.show();
        mShowText = text;
    }

    /**
     * 取消toast
     */
    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
