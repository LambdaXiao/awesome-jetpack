package com.xiao.awesome_jetpack.data.repository.remote.network;

import android.net.ParseException;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.xiao.awesome_jetpack.application.MyApplication;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述：观察者（数据回调处理基类）
 */
public abstract class BaseObserver<T extends BaseResponse> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T baseResponse) {
        try {
            if (baseResponse.isSuccess()) {
                //请求成功
                onSuccess(baseResponse);
            } else {
                //请求失败
                onFailure(baseResponse.getErrorCode(), baseResponse.getErrorMsg(), baseResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //数据解析失败
            Toast.makeText(MyApplication.getApplication(), "数据解析异常，请重试！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof retrofit2.HttpException) {
            //HTTP错误
            Toast.makeText(MyApplication.getApplication(), "网络(协议)异常！", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            //连接错误
            Toast.makeText(MyApplication.getApplication(), "连接失败！", Toast.LENGTH_SHORT).show();
        } else if (e instanceof InterruptedIOException) {
            //连接超时
            Toast.makeText(MyApplication.getApplication(), "连接超时！", Toast.LENGTH_SHORT).show();
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            //解析错误
            Toast.makeText(MyApplication.getApplication(), "数据解析异常！", Toast.LENGTH_SHORT).show();
        } else {
            //其他错误
            Toast.makeText(MyApplication.getApplication(), "其他未知错误！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T response);

    public void onFailure(double errorCode, String errorMsg, T response) {
        //子类重写该方法时注意不要super()方法，否则此处也会被调用
        if (TextUtils.isEmpty(errorMsg)) {
            Toast.makeText(MyApplication.getApplication(), "请求异常，请重试！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getApplication(), errorMsg, Toast.LENGTH_SHORT).show();
        }
    }
}
