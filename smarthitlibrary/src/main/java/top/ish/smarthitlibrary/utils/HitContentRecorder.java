package top.ish.smarthitlibrary.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import top.ish.smarthitlibrary.constants.ConstantsValue;
import top.ish.smarthitlibrary.constants.VariableValue;

/**
 * Created by yanjie on 2017-07-25.
 * Describe: 记录打点日志记录
 */

public class HitContentRecorder {

    private static MappedByteBuffer buffer;

    /**
     * 将打点记录到文件中
     *
     * @param content
     */
    public static void write2File(String content) {
        File path = new File(VariableValue.recordPath);
        if (TextUtils.isEmpty(VariableValue.recordPath) || !path.exists()) {
            throw new RuntimeException("日志记录位置不能为空或路径不存在，请检查");
        }
        try {
            //1.判断buffer是否为空，如果为空重新创建
            if (null == buffer) {
                RandomAccessFile outputStream = new RandomAccessFile(path, "rw");
                FileChannel fc = outputStream.getChannel();
                buffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, 100 * 1024);
            }
            //2.判断记录打点日志的文件是否存在，如果不存在就创建
            File recordFilePath = new File(path, ConstantsValue.PACKAGE_NAME);
            if (!recordFilePath.exists()) {
                recordFilePath.createNewFile();
            }
            //3.判断文件大小，当文件大于2M的时上传
            if (recordFilePath.length() > ConstantsValue.PACKAGE_SIZE){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        buffer.put(content.getBytes());
    }


}
