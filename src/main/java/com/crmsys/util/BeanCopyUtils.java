package com.crmsys.util;


import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Paosin Von Scarlet on 2017/10/2.
 */
public class BeanCopyUtils {

    /**
     * 利用Spring 的 BeanUtils 来进行Po-》Vo的转换
     * 由于Spring 的BeanUtils 不会进行类型检查，所以使用的时候一定要注意
     *
     * @param destClazz 目标参数模板
     * @param orig 源数据
     * @return the dest bean
     */
    public static <T, V> T copyProperties(Class<T> destClazz, V orig) {
        T dest = null;
        try {
            dest = destClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (dest == null || orig == null) {
            return dest;
        }

        BeanUtils.copyProperties(orig, dest);

        return dest;
    }


    /**
     * 将一个 po的集合转化为 vo的集合
     * @param destClazz 目标参数模板
     * @param orig 源数据列表
     * @return
     */
    public static <T, V> List<T> copyProperties(Class<T> destClazz, List<V> orig) {
        List<T> rs = new ArrayList<T>();
        for (V v : orig) {
            rs.add(copyProperties(destClazz, v));
        }
        return rs;
    }
}
