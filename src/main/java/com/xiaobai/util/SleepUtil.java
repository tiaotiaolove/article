package com.xiaobai.util;

/**
 * 模拟处理时间
 *
 * @author bail
 * @date 2018/12/29
 */
public class SleepUtil {

    /**
     * 模拟处理 n 毫秒的复杂业务
     *
     * @param n 单位为毫秒
     */
    public static void sleepSomeTime(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
