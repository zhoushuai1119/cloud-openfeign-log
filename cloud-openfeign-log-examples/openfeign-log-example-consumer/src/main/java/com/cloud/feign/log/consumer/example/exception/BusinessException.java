package com.cloud.feign.log.consumer.example.exception;


import com.cloud.platform.common.exception.BaseException;
import com.cloud.platform.common.exception.BaseExceptionCode;

/**
 * 业务Exception
 * @author zs111
 */
public class BusinessException extends BaseException {


    /**
     * @param errorCode 错误码
     * @param errorDetailMessage  错误详细信息
     */
    public BusinessException(BaseExceptionCode errorCode, String errorDetailMessage) {
        super(errorCode, errorDetailMessage);
    }


    public BusinessException(BaseExceptionCode errorCode) {
        super(errorCode.getErrorCode(), errorCode.getErrorMessage(), null);
    }

    public BusinessException(String errorCode, String errorMessage, String errorTips) {
        super(errorCode, errorMessage, errorTips);
    }

}
