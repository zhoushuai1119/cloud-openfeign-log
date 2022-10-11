package com.cloud.feign.log.consumer.example.client.hystrix;

import com.cloud.feign.log.consumer.example.dto.FeignLogDto;
import com.cloud.feign.log.consumer.example.client.FeignLogTestClient;
import com.cloud.feign.log.consumer.example.exception.BusinessException;
import com.cloud.feign.log.consumer.example.utils.FeignUtils;
import com.cloud.platform.common.domain.response.BaseResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/1/20 14:35
 * @version: v1
 */
@Component
@Slf4j
public class FeignLogTestFallbackFactory implements FallbackFactory<FeignLogTestClient> {

    @Override
    public FeignLogTestClient create(Throwable throwable) {
        log.error("fallback, cause:{}", throwable.getMessage());
        return new FeignLogTestClient() {

            @Override
            public BaseResponse feignLogTest(String testParms, FeignLogDto feignLogDto) {
                BusinessException businessException = FeignUtils.decodeFeignException("feignLogTest", throwable);
                BaseResponse response = new BaseResponse();
                response.setSuccess(false);
                response.setErrorCode(businessException.getErrorCode());
                response.setErrorTips(businessException.getErrorTips());
                response.setErrorMessage(businessException.getMessage());
                return response;
            }
        };
    }

}
