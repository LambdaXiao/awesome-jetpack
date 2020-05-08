package com.xiao.awesome_jetpack.data.repository.remote.network;

import java.io.Serializable;

/**
 * 描述：网络请求返回的实体类Bean
 */
public class BaseResponse<T> implements Serializable {
    private static int SUCCESS_CODE = 0;//成功的code
    T data;
    double errorCode;
    String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public double getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(double errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return getErrorCode() == SUCCESS_CODE;
    }
}
