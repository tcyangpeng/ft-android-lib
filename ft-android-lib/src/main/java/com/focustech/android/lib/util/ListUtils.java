package com.focustech.android.lib.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <列表工具类>
 * 提供列表操作的常用方法
 *
 * @author focustech
 * @version [版本号, 2024-11-20]
 * @see [相关类/方法]
 * @since [V1]
 */
public final class ListUtils {

    private ListUtils() {
        throw new UnsupportedOperationException("ListUtils cannot be instantiated");
    }

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return true表示为空，false表示不为空
     */
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断列表是否不为空
     *
     * @param list 列表
     * @return true表示不为空，false表示为空
     */
    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

    /**
     * 获取列表的大小
     *
     * @param list 列表
     * @return 列表大小，如果为null则返回0
     */
    public static int size(List<?> list) {
        return list == null ? 0 : list.size();
    }

    /**
     * 安全地获取列表中指定位置的元素
     *
     * @param list  列表
     * @param index 索引位置
     * @param <T>   元素类型
     * @return 指定位置的元素，如果索引越界则返回null
     */
    public static <T> T getItem(List<T> list, int index) {
        if (isEmpty(list) || index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    /**
     * 安全地获取列表的第一个元素
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 第一个元素，如果列表为空则返回null
     */
    public static <T> T getFirst(List<T> list) {
        return getItem(list, 0);
    }

    /**
     * 安全地获取列表的最后一个元素
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 最后一个元素，如果列表为空则返回null
     */
    public static <T> T getLast(List<T> list) {
        return isEmpty(list) ? null : list.get(list.size() - 1);
    }

    /**
     * 创建一个新的ArrayList
     *
     * @param <T> 元素类型
     * @return 新的ArrayList实例
     */
    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    /**
     * 创建一个包含指定元素的ArrayList
     *
     * @param elements 元素数组
     * @param <T>      元素类型
     * @return 包含指定元素的ArrayList
     */
    @SafeVarargs
    public static <T> List<T> newArrayList(T... elements) {
        if (elements == null || elements.length == 0) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(Arrays.asList(elements));
    }

    /**
     * 将集合转换为ArrayList
     *
     * @param collection 集合
     * @param <T>        元素类型
     * @return ArrayList
     */
    public static <T> List<T> toArrayList(Collection<T> collection) {
        if (collection == null) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(collection);
    }

    /**
     * 反转列表
     *
     * @param list 列表
     * @param <T>  元素类型
     */
    public static <T> void reverse(List<T> list) {
        if (isNotEmpty(list)) {
            Collections.reverse(list);
        }
    }

    /**
     * 获取反转后的列表（不修改原列表）
     *
     * @param list 原列表
     * @param <T>  元素类型
     * @return 反转后的新列表
     */
    public static <T> List<T> reversed(List<T> list) {
        if (isEmpty(list)) {
            return new ArrayList<T>();
        }
        List<T> reversed = new ArrayList<T>(list);
        Collections.reverse(reversed);
        return reversed;
    }

    /**
     * 对列表进行排序
     *
     * @param list 列表
     * @param <T>  元素类型，必须实现Comparable接口
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        if (isNotEmpty(list)) {
            Collections.sort(list);
        }
    }

    /**
     * 使用比较器对列表进行排序
     *
     * @param list       列表
     * @param comparator 比较器
     * @param <T>        元素类型
     */
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        if (isNotEmpty(list) && comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    /**
     * 去除列表中的重复元素
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 去重后的新列表
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        if (isEmpty(list)) {
            return new ArrayList<T>();
        }
        Set<T> set = new HashSet<T>(list);
        return new ArrayList<T>(set);
    }

    /**
     * 合并多个列表
     *
     * @param lists 列表数组
     * @param <T>   元素类型
     * @return 合并后的新列表
     */
    @SafeVarargs
    public static <T> List<T> merge(List<T>... lists) {
        List<T> result = new ArrayList<T>();
        if (lists == null || lists.length == 0) {
            return result;
        }
        for (List<T> list : lists) {
            if (isNotEmpty(list)) {
                result.addAll(list);
            }
        }
        return result;
    }

    /**
     * 分割列表
     *
     * @param list 原列表
     * @param size 每个子列表的大小
     * @param <T>  元素类型
     * @return 分割后的列表集合
     */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        List<List<T>> result = new ArrayList<List<T>>();
        if (isEmpty(list) || size <= 0) {
            return result;
        }
        int listSize = list.size();
        for (int i = 0; i < listSize; i += size) {
            result.add(new ArrayList<T>(list.subList(i, Math.min(listSize, i + size))));
        }
        return result;
    }

    /**
     * 过滤列表中的null元素
     *
     * @param list 列表
     * @param <T>  元素类型
     * @return 过滤后的新列表
     */
    public static <T> List<T> filterNull(List<T> list) {
        if (isEmpty(list)) {
            return new ArrayList<T>();
        }
        List<T> result = new ArrayList<T>();
        for (T item : list) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 交换列表中两个位置的元素
     *
     * @param list 列表
     * @param i    第一个位置
     * @param j    第二个位置
     * @param <T>  元素类型
     * @return true表示交换成功，false表示交换失败
     */
    public static <T> boolean swap(List<T> list, int i, int j) {
        if (isEmpty(list) || i < 0 || i >= list.size() || j < 0 || j >= list.size()) {
            return false;
        }
        if (i == j) {
            return true;
        }
        Collections.swap(list, i, j);
        return true;
    }

    /**
     * 判断列表是否包含指定元素
     *
     * @param list    列表
     * @param element 要查找的元素
     * @param <T>     元素类型
     * @return true表示包含，false表示不包含
     */
    public static <T> boolean contains(List<T> list, T element) {
        return isNotEmpty(list) && list.contains(element);
    }

    /**
     * 查找元素在列表中的位置
     *
     * @param list    列表
     * @param element 要查找的元素
     * @param <T>     元素类型
     * @return 元素的索引位置，如果不存在则返回-1
     */
    public static <T> int indexOf(List<T> list, T element) {
        return isEmpty(list) ? -1 : list.indexOf(element);
    }

    /**
     * 获取列表的子列表
     *
     * @param list      原列表
     * @param fromIndex 起始位置（包含）
     * @param toIndex   结束位置（不包含）
     * @param <T>       元素类型
     * @return 子列表
     */
    public static <T> List<T> subList(List<T> list, int fromIndex, int toIndex) {
        if (isEmpty(list) || fromIndex < 0 || toIndex > list.size() || fromIndex >= toIndex) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(list.subList(fromIndex, toIndex));
    }

    /**
     * 将数组转换为列表
     *
     * @param array 数组
     * @param <T>   元素类型
     * @return 列表
     */
    @SafeVarargs
    public static <T> List<T> asList(T... array) {
        if (array == null || array.length == 0) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(Arrays.asList(array));
    }

    /**
     * 将列表转换为数组
     *
     * @param list  列表
     * @param array 目标数组
     * @param <T>   元素类型
     * @return 数组
     */
    public static <T> T[] toArray(List<T> list, T[] array) {
        if (isEmpty(list)) {
            return array;
        }
        return list.toArray(array);
    }

    /**
     * 清空列表
     *
     * @param list 列表
     */
    public static void clear(List<?> list) {
        if (isNotEmpty(list)) {
            list.clear();
        }
    }

    /**
     * 比较两个列表是否相等
     *
     * @param list1 列表1
     * @param list2 列表2
     * @param <T>   元素类型
     * @return true表示相等，false表示不相等
     */
    public static <T> boolean equals(List<T> list1, List<T> list2) {
        if (list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        return list1.equals(list2);
    }
}
