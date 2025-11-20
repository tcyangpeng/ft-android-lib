package com.focustech.android.lib.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * <数学工具类>
 * 提供数学计算、随机数生成等功能
 *
 * @author focustech
 * @version [版本号, 2024-11-20]
 * @see [相关类/方法]
 * @since [V1]
 */
public final class MathUtils {

    private static final Random RANDOM = new Random();

    private MathUtils() {
        throw new UnsupportedOperationException("MathUtils cannot be instantiated");
    }

    /**
     * 生成指定范围内的随机整数（包含min，不包含max）
     *
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 随机整数
     */
    public static int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return RANDOM.nextInt(max - min) + min;
    }

    /**
     * 生成0到指定值之间的随机整数（不包含bound）
     *
     * @param bound 上界（不包含）
     * @return 随机整数
     */
    public static int randomInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }
        return RANDOM.nextInt(bound);
    }

    /**
     * 生成指定范围内的随机长整数（包含min，不包含max）
     *
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 随机长整数
     */
    public static long randomLong(long min, long max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return (long) (RANDOM.nextDouble() * (max - min)) + min;
    }

    /**
     * 生成指定范围内的随机浮点数（包含min，不包含max）
     *
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 随机浮点数
     */
    public static float randomFloat(float min, float max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return RANDOM.nextFloat() * (max - min) + min;
    }

    /**
     * 生成指定范围内的随机双精度浮点数（包含min，不包含max）
     *
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 随机双精度浮点数
     */
    public static double randomDouble(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return RANDOM.nextDouble() * (max - min) + min;
    }

    /**
     * 生成随机布尔值
     *
     * @return 随机布尔值
     */
    public static boolean randomBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * 生成指定长度的随机字符串（包含数字和大小写字母）
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        if (length <= 0) {
            return "";
        }
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机数字字符串
     *
     * @param length 字符串长度
     * @return 随机数字字符串
     */
    public static String randomNumericString(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 获取两个数中的最大值
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 最大值
     */
    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * 获取多个数中的最大值
     *
     * @param numbers 数字数组
     * @return 最大值
     */
    public static int max(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("numbers cannot be null or empty");
        }
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    /**
     * 获取两个数中的最小值
     *
     * @param a 第一个数
     * @param b 第二个数
     * @return 最小值
     */
    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    /**
     * 获取多个数中的最小值
     *
     * @param numbers 数字数组
     * @return 最小值
     */
    public static int min(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("numbers cannot be null or empty");
        }
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    /**
     * 计算绝对值
     *
     * @param value 数值
     * @return 绝对值
     */
    public static int abs(int value) {
        return Math.abs(value);
    }

    /**
     * 计算绝对值
     *
     * @param value 数值
     * @return 绝对值
     */
    public static long abs(long value) {
        return Math.abs(value);
    }

    /**
     * 计算绝对值
     *
     * @param value 数值
     * @return 绝对值
     */
    public static float abs(float value) {
        return Math.abs(value);
    }

    /**
     * 计算绝对值
     *
     * @param value 数值
     * @return 绝对值
     */
    public static double abs(double value) {
        return Math.abs(value);
    }

    /**
     * 四舍五入
     *
     * @param value 数值
     * @return 四舍五入后的整数
     */
    public static int round(float value) {
        return Math.round(value);
    }

    /**
     * 四舍五入
     *
     * @param value 数值
     * @return 四舍五入后的长整数
     */
    public static long round(double value) {
        return Math.round(value);
    }

    /**
     * 向上取整
     *
     * @param value 数值
     * @return 向上取整后的值
     */
    public static double ceil(double value) {
        return Math.ceil(value);
    }

    /**
     * 向下取整
     *
     * @param value 数值
     * @return 向下取整后的值
     */
    public static double floor(double value) {
        return Math.floor(value);
    }

    /**
     * 保留指定小数位数（四舍五入）
     *
     * @param value  数值
     * @param scale  小数位数
     * @return 保留指定小数位数后的值
     */
    public static double round(double value, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("scale must be positive");
        }
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * 精确加法运算
     *
     * @param v1 加数1
     * @param v2 加数2
     * @return 和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 精确减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 差
     */
    public static double subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 精确乘法运算
     *
     * @param v1 乘数1
     * @param v2 乘数2
     * @return 积
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 精确除法运算（默认保留10位小数）
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 商
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, 10);
    }

    /**
     * 精确除法运算
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 保留小数位数
     * @return 商
     */
    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("scale must be positive");
        }
        if (v2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 计算平方
     *
     * @param value 数值
     * @return 平方值
     */
    public static double square(double value) {
        return value * value;
    }

    /**
     * 计算平方根
     *
     * @param value 数值
     * @return 平方根
     */
    public static double sqrt(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("value cannot be negative");
        }
        return Math.sqrt(value);
    }

    /**
     * 计算幂次方
     *
     * @param base     底数
     * @param exponent 指数
     * @return 幂次方值
     */
    public static double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * 限制数值在指定范围内
     *
     * @param value 数值
     * @param min   最小值
     * @param max   最大值
     * @return 限制后的值
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * 限制数值在指定范围内
     *
     * @param value 数值
     * @param min   最小值
     * @param max   最大值
     * @return 限制后的值
     */
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * 限制数值在指定范围内
     *
     * @param value 数值
     * @param min   最小值
     * @param max   最大值
     * @return 限制后的值
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * 判断数值是否在指定范围内（包含边界）
     *
     * @param value 数值
     * @param min   最小值
     * @param max   最大值
     * @return true表示在范围内，false表示不在
     */
    public static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * 判断数值是否在指定范围内（包含边界）
     *
     * @param value 数值
     * @param min   最小值
     * @param max   最大值
     * @return true表示在范围内，false表示不在
     */
    public static boolean inRange(double value, double min, double max) {
        return value >= min && value <= max;
    }
}
