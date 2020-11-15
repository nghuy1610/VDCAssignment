package com.vdc.shop.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setConcurrencyLimit(20);
        return simpleAsyncTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncUncaughtExceptionHandler();
    }

    public static class CustomAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler{
        private static final Logger LOGGER = LogManager.getLogger(CustomAsyncUncaughtExceptionHandler.class);
        @Override
        public void handleUncaughtException(Throwable e, Method method, Object... args) {
            LOGGER.error("message = methodName - {}, methodArgs - {}, stacktrace = {}", method.getName(),
                    Arrays.toString(args), ExceptionUtils.getStackTrace(e));
        }
    }
}
