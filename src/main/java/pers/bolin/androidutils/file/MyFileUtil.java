package pers.bolin.androidutils.file;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import pers.bolin.androidutils.log.MyLog;

/**
 * 仅适用于Android 的文件操作类
 * Android 的存储有：内部存储/外部存储
 * 可分配给app的目录：
 * 1./storage/emulated/0/Android/data/<application package>/cache -- 外部缓存
 * 2./data/user/0/<application package>/cache  -- 内部缓存
 * 3./storage/emulated/0/<application package>/files  -- 外部缓存
 * 4./data/user/0/<application package>/files -- 内部缓存
 * 5.SD卡存储
 * 我们可以在cache目录里面放置我们的图片缓存，而且cache与files的差别在于，
 * 如果手机的内部存储控件不够了，会自行选择cache目录进行删除，
 * 因此，不要把重要的文件放在cache文件里面，可以放置在files里面，因为这个文件只有在APP被卸载的时候才会被删除。
 *
 * @author LanHe-Android
 * @date 2017/11/10
 */

public class MyFileUtil {

    /**
     * 获取SD卡的根目录
     *
     * @return 根目录路径
     * @throws Exception
     */
    public static String getSDCardRootDirectory() throws Exception {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && !Environment.isExternalStorageRemovable()) {
            return Environment.getExternalStorageDirectory().toString();
        } else {
            throw new Exception("获取SD卡根目录失败");
        }
    }


    /**
     * 清空目录，包括目录本身
     *
     * @param path 目录路径
     * @throws Exception
     */
    public static void cleanDirectory(String path) throws Exception {
        File dir = new File(path);
        if (!dir.exists()) {
            MyLog.d("该目录不存在：" + path);
            return;
        }
        if (!dir.isDirectory()) {
            throw new Exception("该文件不是目录：" + path);
        }
        // 获取目标文件夹下所有的文件和文件夹数组
        File[] list = dir.listFiles();
        for (File subFiles : list) {
            if (subFiles.isFile()) {
                boolean isSuccess = subFiles.delete();
                if (!isSuccess) {
                    throw new Exception("该文件删除失败：" + subFiles.getName());
                }
            } else {
                cleanDirectory(subFiles.getPath());
            }
        }
        boolean isSuccess = dir.delete();
        if (!isSuccess) {
            throw new Exception("该文件删除失败：" + dir.getName());
        }
    }


    /**
     * 将/data/user/0/<application package>/files 中 -- 内存缓存中 取出
     * PS.这种方式适合读取少量数据的文件
     *
     * @param context  上下文对象
     * @param fileName 文件名称
     * @return byte[]  数据
     * @throws Exception
     */
    public static byte[] readDataInInternalStorage(Context context, String fileName) throws Exception {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
            byte[] data = new byte[fis.available()];
            int result = fis.read(data);
            if (result < 0) {
                throw new Exception("读取文件失败");
            }
            return data;
        } catch (FileNotFoundException e) {
            throw new Exception("未找到文件");
        } catch (IOException e) {
            throw new Exception("读取文件失败");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 保存数据到/data/user/0/<application package>/files 中 -- 内存缓存中
     * PS.这种方式适合写少量数据的文件
     *
     * @param context  上下文对象
     * @param fileName 文件名称
     * @param data     数据
     * @param fileType Context.MODE_PRIVATE / Context.MODE_APPEND
     * @throws Exception
     */
    public static void saveDataInInternalStorage(Context context, String fileName, byte[] data, int fileType) throws Exception {
        FileOutputStream outStream = null;
        try {
            outStream = context.openFileOutput(fileName, fileType);
            outStream.write(data);
            outStream.close();
        } catch (FileNotFoundException e) {
            throw new Exception("未找到文件");
        } catch (IOException e) {
            throw new Exception("保存文件失败");
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                }
            }
        }
    }


    /**
     * 获取缓存的Cache目录（Cache）
     * <p>
     * 优先获取外部的缓存目录：
     * /storage/emulated/0/Android/data/<application package>/cache
     * 获取不到，则使用内部缓存目录：
     * /data/user/0/<application package>/cache
     *
     * @param context 上下文对象
     * @return 缓存目录
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath;
        // 判断SD是否挂载
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && !Environment.isExternalStorageRemovable()) {
            File file = context.getExternalCacheDir();
            if (file != null) {
                cachePath = file.getPath();
            } else {
                cachePath = context.getCacheDir().getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 获取缓存的Files目录（Files）
     * <p>
     * 优先获取外部的缓存目录：
     * /storage/emulated/0/<application package>/files
     * 获取不到，则使用内部缓存目录：
     * /data/user/0/<application package>/files
     *
     * @param context 上下文对象
     * @param nextDir 一般填写null，如果非null，则是下一级目录的名称
     * @return 缓存目录
     */
    public static String getDiskFilesDir(Context context, String nextDir) {
        String filePath;
        // 判断SD是否挂载
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && !Environment.isExternalStorageRemovable()) {
            File file = context.getExternalFilesDir(nextDir);
            if (file != null) {
                filePath = file.getPath();
            } else {
                if (nextDir != null) {
                    filePath = context.getFilesDir().getPath() + "/" + nextDir;
                } else {
                    filePath = context.getFilesDir().getPath();
                }
            }
        } else {
            if (nextDir != null) {
                filePath = context.getFilesDir().getPath() + "/" + nextDir;
            } else {
                filePath = context.getFilesDir().getPath();
            }
        }
        return filePath;
    }

}
