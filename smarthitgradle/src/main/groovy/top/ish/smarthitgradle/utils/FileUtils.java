package top.ish.smarthitgradle.utils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import top.ish.smarthitgradle.inject.SmartClassVisitor;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by yanjie on 2017-07-31.
 * Describe:
 */

public class FileUtils {




    /**
     * 遍历目录找出所有class文件
     * @param path
     */
    public static void traverseFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        String filepath = file2.getAbsolutePath();
                        if (filepath.endsWith(".class")) {
                            System.out.println(filepath);
                            injectClassFile(file2.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }

    /**
     * 侵入class文件
     * @param filepath
     */
    private static void injectClassFile(String filepath) {
        try {
            InputStream inputStream = new FileInputStream(filepath);

            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }

            ClassReader classReader;
            classReader = new ClassReader(swapStream.toByteArray());

            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            ClassVisitor classVisitor = new SmartClassVisitor(getClassName(filepath),classWriter);
            classReader.accept(classVisitor, ASM5);

            FileOutputStream fos = new FileOutputStream(filepath);
            fos.write(classWriter.toByteArray());
            fos.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPackagename(String classPath ,String flag) {
        String[] SPs = classPath.split(flag);
        String tmpString = SPs[SPs.length-1].replaceAll("/", ".").replace(".class", "").substring(1);
        return tmpString;
    }


    private static String getClassName(String path) {
        String[] tmp = path.split("/");
        String name = tmp[tmp.length-1];
        return name.replace(".class", "");
    }

}
