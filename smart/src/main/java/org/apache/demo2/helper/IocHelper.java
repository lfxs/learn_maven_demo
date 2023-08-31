package org.apache.demo2.helper;

import org.apache.demo2.annotation.Inject;
import org.apache.demo2.util.ArrayUtil;
import org.apache.demo2.util.CollectionUtil;
import org.apache.demo2.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * 依赖注入助手类
 * Ioc框架中所管理的对象都是单例的,由于Ioc框架底层是从BeanHelper中获取Bean Map的，
 * 而Bean Map 中的对象都是事先创建好放入到这个Bean容器的,所有的对象都是单例的
 */
public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty((Collection<?>) beanMap)){
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    for (Field beanField : beanFields) {
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
