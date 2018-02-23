package pers.bolin.androidutils.spf;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * SharedPreferences工具
 * Created by H-Bolin on 2018/2/23.
 */
public class MySharedPreferencesUtil {

    /**
     * 存储的SharedPreferences文件名
     */
    private static final String FILE_NAME = "app_data_spf";

    /**
     * 保存数据到文件
     *
     * @param context 上下文
     * @param key     key
     * @param data    数据
     */
    public static void putData(Context context, String key, Object data) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            throw new IllegalArgumentException("未获取到参数类型，请确认data的类型");
        }

        editor.apply();
    }

    /**
     * 从文件中读取数据
     *
     * @param context  上下文
     * @param key      key
     * @param defValue 默认值，如果当前获取不到数据就返回它
     * @return 数据
     */
    public static Object getData(Context context, String key, Object defValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        //defValue为默认值，如果当前获取不到数据就返回它
        if (defValue instanceof String) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defValue);
        } else {
            throw new IllegalArgumentException("未获取到参数类型，请确认defValue的类型");
        }

    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context 上下文
     * @param key     key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getAll();
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
