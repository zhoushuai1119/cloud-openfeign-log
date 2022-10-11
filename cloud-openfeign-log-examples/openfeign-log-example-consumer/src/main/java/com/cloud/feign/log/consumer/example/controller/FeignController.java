package com.cloud.feign.log.consumer.example.controller;

import com.cloud.feign.log.consumer.example.dto.FeignLogDto;
import com.cloud.feign.log.consumer.example.client.FeignLogTestClient;
import com.cloud.platform.common.domain.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/10/11 11:19
 * @version: v1
 */
@RestController
@RequestMapping("feign")
public class FeignController {

    @Autowired
    private FeignLogTestClient feignLogTestClient;

    @GetMapping("test")
    public BaseResponse<FeignLogDto> feignLogTest(@RequestParam("testParms") String testParms) {
        FeignLogDto logDto = new FeignLogDto("zhoushuai", 29);
        return feignLogTestClient.feignLogTest(testParms, logDto);
    }

}
