package com.cloud.feign.log.producer.example.controller;

import com.cloud.feign.log.producer.example.dto.FeignLogDto;
import com.cloud.platform.common.domain.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/10/11 11:08
 * @version: v1
 */
@RestController
@RequestMapping("/feign/log")
@Slf4j
public class FeignLogTestController {

    @PostMapping("test")
    public BaseResponse<FeignLogDto> feignLogTest(@RequestParam("testParms") String testParms, @RequestBody FeignLogDto feignLogDto) {
        log.info("***testParms:{}****", testParms);
        return BaseResponse.createSuccessResult(feignLogDto);
    }

}
