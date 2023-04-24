package top.goku.dailycost.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import top.goku.dailycost.exception.DailyCostException;
import top.goku.dailycost.exception.ErrorEnum;

import java.util.Collection;
import java.util.Objects;

/**
 * @author ZXP
 * @link <a href="https://github.com/sgr997">sgr997</a>
 * @date 2022/8/13 10:20
 */
public class AssertUtils {

    public static void notNull(Object obj) {
        notNull(obj, null, null);
    }

    public static void notNull(Object obj, String message) {
        notNull(obj, null, message);
    }

    public static void notNull(Object obj, ErrorEnum errorEnum, String message) {
        isTrue(Objects.nonNull(obj), errorEnum, message);
    }

    public static void isNull(Object obj) {
        isNull(obj, null, null);
    }

    public static void isNull(Object obj, String message) {
        isNull(obj, null, message);
    }

    public static void isNull(Object obj, ErrorEnum errorEnum, String message) {
        isTrue(Objects.isNull(obj), errorEnum, message);
    }

    public static <T> void notEmpty(Collection<T> collection) {
        notEmpty(collection, null, null);
    }

    public static <T> void notEmpty(Collection<T> collection, String message) {
        notEmpty(collection, null, message);
    }

    public static <T> void notEmpty(Collection<T> collection, ErrorEnum errorEnum, String message) {
        isTrue(CollectionUtil.isNotEmpty(collection), errorEnum, message);
    }

    public static <T> void isEmpty(Collection<T> collection) {
        isEmpty(collection, null, null);
    }

    public static <T> void isEmpty(Collection<T> collection, String message) {
        isEmpty(collection, null, message);
    }

    public static <T> void isEmpty(Collection<T> collection, ErrorEnum errorEnum, String message) {
        isTrue(CollectionUtil.isEmpty(collection), errorEnum, message);
    }

    public static void isBlank(String str) {
        isBlank(str, null, null);
    }

    public static void isBlank(String str, String message) {
        isBlank(str, null, message);
    }

    public static void isBlank(String str, ErrorEnum errorEnum, String message) {
        isTrue(StrUtil.isBlank(str), errorEnum, message);
    }

    public static void notBlank(String str) {
        notBlank(str, null, null);
    }

    public static void notBlank(String str, String message) {
        notBlank(str, null, message);
    }

    public static void notBlank(String str, ErrorEnum errorEnum, String message) {
        isTrue(StrUtil.isNotBlank(str), errorEnum, message);
    }

    public static void isFalse(boolean condition) {
        isFalse(condition, null, null);
    }

    public static void isFalse(boolean condition, String message) {
        isFalse(condition, null, message);
    }

    public static void isFalse(boolean condition, ErrorEnum errorEnum, String message) {
        isTrue(!condition, errorEnum, message);
    }

    public static void isTrue(boolean condition) {
        isTrue(condition, null, null);
    }

    public static void isTrue(boolean condition, String message) {
        isTrue(condition, null, message);
    }

    public static void isTrue(boolean condition, ErrorEnum errorEnum, String message) {
        if (!condition) {
            throw new DailyCostException(errorEnum == null ? ErrorEnum.PARAMS_ERROR : errorEnum, message);
        }
    }
}
