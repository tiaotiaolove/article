package com.xiaobai.goods.service;

import com.xiaobai.util.SleepUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步执行的模块
 *
 * @author bail
 * @date 2018/4/17.15:39
 */
@Component
@Slf4j
public class AsyncService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public void testAsyncFunc() {
        log.debug("3---异步方法开始执行");
        SleepUtil.sleepSomeTime(3000);
        log.debug(restTemplate.getForObject("https://bossbff.s2b.wanmi.com/baseConfig", Map.class).toString());
        log.debug("4---异步方法结束执行");
    }

    /**
     * 自定义异步线程池
     *
     * @return 任务执行器
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Bail-Thread");
        //线程池大小
        executor.setCorePoolSize(5);
        //线程池最大线程数
        executor.setMaxPoolSize(10);
        //最大等待任务数
        executor.setQueueCapacity(25);
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
