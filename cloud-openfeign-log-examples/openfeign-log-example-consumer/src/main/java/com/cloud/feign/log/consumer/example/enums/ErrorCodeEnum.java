package com.cloud.feign.log.consumer.example.enums;

import com.cloud.platform.common.exception.BaseExceptionCode;
import lombok.Getter;

/**
 * 错误信息enum
 *
 * @Author 施文
 * @Date 2019/06/23
 * @Time 13:46:19
 */
@Getter
public enum ErrorCodeEnum implements BaseExceptionCode {

    /**
     * feign 调用失败
     */
    FEIGN_CLIENT_ERROR("200001", "feign调用失败");


    private String code;
    private String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }

}
