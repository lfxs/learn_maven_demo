package org.apache.demo2.helper;

import org.apache.demo2.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean 助手类
 */
public class BeanHelper {
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<>();
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /**
     *
     * 获取Bean映射
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     *
     * 获取Bean实例
     */
    public static <T> T getBean(Class<?> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("cat not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }








}
