package com.biginsect.signinmanagement.utils;

import java.util.List;

/**
 * @author biginsect
 * @date 2020/4/4
 */
public final class ListUtils {

    private ListUtils(){
        throw new UnsupportedOperationException("无法初始化工具类");
    }

    /**
     * 检查list是否为null 或 未包含任何元素
     * @param list 目标list
     * @param <T>
     * @return true 如何为null或empty,否则 false
     */
    public static <T> boolean isEmpty(List<T> list){
        return null == list || list.isEmpty();
    }

    /**
     * 查询list的元素个数，为null 返回0
     * @param list 目标
     * @param <T>
     * @return 元素个数
     */
    public static <T> int getSize(List<T> list){
        return null == list ? 0 : list.size();
    }
}
