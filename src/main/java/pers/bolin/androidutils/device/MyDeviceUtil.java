package pers.bolin.androidutils.device;

import android.content.Context;

/**
 * 设备工具类
 * Created by H-Bolin on 2018/2/23.
 */

public class MyDeviceUtil {

    // ----------------------------------- 屏幕相关 ----------------------------

    /**
     * 得到屏幕的高 （像素）
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到屏幕的宽 （像素）
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备的密度
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 得到设备的屏幕密度dpi（120 / 160 / 240）
     */
    public static int getScreenDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 得到设备屏幕的高度 (dp)
     */
    public static int getScreenHeightDp(Context context) {
        return (int) (context.getResources().getDisplayMetrics().heightPixels / context.getResources().getDisplayMetrics().density);
    }

    /**
     * 得到设备屏幕的宽度 (dp)
     */
    public static int getScreenWidthDp(Context context) {
        return (int) (context.getResources().getDisplayMetrics().widthPixels / context.getResources().getDisplayMetrics().density);
    }

}
