package com.cloud.openfeign.log.config;

import com.cloud.openfeign.log.MyFeignLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/10/9 16:31
 * @version: v1
 */
@Configuration(proxyBeanMethods = false)
public class FeignLogAutoConfiguration {

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public MyFeignLogger myFeignLogger() {
        return new MyFeignLogger();
    }

}
