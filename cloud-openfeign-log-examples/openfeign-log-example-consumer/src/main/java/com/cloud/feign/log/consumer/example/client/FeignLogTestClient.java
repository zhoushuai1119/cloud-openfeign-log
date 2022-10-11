package com.cloud.feign.log.consumer.example.client;

import com.cloud.feign.log.consumer.example.client.hystrix.FeignLogTestFallbackFactory;
import com.cloud.feign.log.consumer.example.dto.FeignLogDto;
import com.cloud.platform.common.domain.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/1/20 14:32
 * @version: v1
 */
@FeignClient(name = FeignLogTestClient.SERVER_NAME,
        fallbackFactory = FeignLogTestFallbackFactory.class)
public interface FeignLogTestClient {

    String SERVER_NAME = "openfeign-log-example-producer";

    /**
     * feign log test
     *
     * @param testParms
     * @return
     */
    @PostMapping(value = "/feign/log/test")
    BaseResponse feignLogTest(@RequestParam("testParms") String testParms, @RequestBody FeignLogDto feignLogDto);

}
