package com.xiaobai.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 模拟处理时间
 *
 * @author bail
 * @date 2018/12/29
 */
@Slf4j
public class SleepUtil {

    /**
     * 模拟处理 n 毫秒的复杂业务
     *
     * @param n 单位为毫秒
     */
    public static void sleepSomeTime(long n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            log.error("Thread.sleep执行出错", e);
        }
    }
}
