package com.cloud.feign.log.consumer.example.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Feign配置注册（全局）
 * @author: zhou shuai
 * @date: 2022/1/20 16:56
 * @version: v1
 */
@Configuration
@Slf4j
public class FeignSupportConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //可以设置请求头数据
        requestTemplate.header("FEIGN_LOG_HEADER", "hshshshhshan111");
    }

}
