package org.apache.demo2;

import org.apache.demo2.helper.BeanHelper;
import org.apache.demo2.helper.ClassHelper;
import org.apache.demo2.helper.ConfigHelper;
import org.apache.demo2.helper.ControllerHelper;
import org.apache.demo2.util.ClassUtil;

/**
 * 加载对应的Helper 类
 */
public class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                ConfigHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            //不需要进行实例化,只需要加载Helper的静态代码块即可
            ClassUtil.loadClass(cls.getName(),false );
        }
    }
}
