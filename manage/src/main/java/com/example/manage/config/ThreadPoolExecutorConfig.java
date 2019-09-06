package com.example.manage.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @program: demo
 * @author: dwx
 * @create: 2019-09-03 10:29
 **/
@Configuration
@Slf4j
public class ThreadPoolExecutorConfig implements AsyncConfigurer {

    /**
     *
     * 线程池设置
     */
    @Override
    public Executor getAsyncExecutor() {
        //使用spring的包
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1); //最小线程数量
        threadPoolTaskExecutor.setMaxPoolSize(4); //最大线程数
        threadPoolTaskExecutor.setQueueCapacity(200);//缓存队列
        threadPoolTaskExecutor.setKeepAliveSeconds(60); //线程池维护线程所允许的空闲时间
        threadPoolTaskExecutor.setAwaitTerminationSeconds(12);//等待时间
        threadPoolTaskExecutor.setThreadNamePrefix("example");//线程名前缀
        threadPoolTaskExecutor.initialize();//初始化
        return threadPoolTaskExecutor;
    }

    public static void main(String[] args) {
        //创建线程局部变量，不是线程变量共享，只能是当前线程使用
        ThreadLocal threadLocal = new ThreadLocal();
    }

}
