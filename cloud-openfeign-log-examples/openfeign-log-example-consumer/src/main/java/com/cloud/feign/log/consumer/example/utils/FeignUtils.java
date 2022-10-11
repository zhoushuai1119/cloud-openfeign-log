package com.cloud.feign.log.consumer.example.utils;


import com.cloud.feign.log.consumer.example.enums.ErrorCodeEnum;
import com.cloud.feign.log.consumer.example.exception.BusinessException;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/1/20 15:00
 * @version: v1
 */
public class FeignUtils {

    public static BusinessException decodeFeignException(String interfaceName,Throwable throwable){
        String message = throwable.getLocalizedMessage();
        return new BusinessException(ErrorCodeEnum.FEIGN_CLIENT_ERROR,interfaceName + "接口:" + message);
    }

}
