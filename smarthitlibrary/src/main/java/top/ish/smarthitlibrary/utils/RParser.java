package top.ish.smarthitlibrary.utils;

import android.util.SparseArray;

import java.lang.reflect.Field;

import top.ish.smarthitlibrary.context.App;

/**
 * Created by yanjie on 17/7/5.
 * R文件解析
 */

public class RParser {

    /**
     * R文件解析器
     *
     * @return 解析结果
     */
    public SparseArray<String> parseR() {
        SparseArray<String> idRecordMap = new SparseArray<>();
        try {
            Class<?> clz = Class.forName(App.INSTANCE.getPackageName() + ".R");
            Class<?> innerClazz[] = clz.getDeclaredClasses();
            for (Class<?> cls : innerClazz) {
                String[] name = cls.getName().split("\\$");
                if ("id".equals(name[1])) {
                    Object instance = cls.newInstance();
                    Field[] fields = cls.getFields();
                    for (int i = 1; i < fields.length; i++) {
                        try {
                            Object key = fields[i].get(instance);
                            String value = fields[i].getName();
                            idRecordMap.put(Integer.parseInt(key.toString()), value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
            return idRecordMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
