package com.focustech.android.lib.page;

import android.content.Context;


import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;


/**
 * 获取主module中的BuildConfig
 *
 * @author liuzaibing
 * @version [版本号, 2017/6/14]
 * @see [相关类/方法]
 * @since [V1]
 */
public class BuildConfigUtil {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(BuildConfigUtil.class.getSimpleName());
    public static final String APP_PACKAGE = "com.focustech.android.mt.parent";
    public static final String APP_WORK_DIR = "APP_WORK_DIR";

    public static Object getBuildConfigValue(Context context, String fieldName) {
        try {
            Class<?> clazz = Class.forName(APP_PACKAGE + ".BuildConfig");
            log.info(clazz.getName());
            Field field = clazz.getDeclaredField(fieldName);
            log.info((String) field.get(null));
            return field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getBuildConfigValue,filedName:" + fieldName + "\n error" + e.toString());
        }
        return null;
    }
}
