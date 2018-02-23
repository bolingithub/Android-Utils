package pers.bolin.androidutils.log;

import android.util.Log;

/**
 * 日志显示类
 *
 * @author LanHe-Android
 */
public class MyLog {

    public static boolean isOpenLog = true;

    private static final String TAG = "AppLog";

    public static void d(String content) {
        if (isOpenLog) {
            Log.d(TAG, content);
        }
    }

    public static void d(String TAG, String content) {
        if (isOpenLog) {
            Log.d(TAG, content);
        }
    }

    public static void e(String content) {
        if (isOpenLog) {
            Log.e(TAG, content);
        }
    }

    public static void e(String TAG, String content) {
        if (isOpenLog) {
            Log.e(TAG, content);
        }
    }
}
