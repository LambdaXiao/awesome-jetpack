package com.xiao.awesome_jetpack.base;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.xiao.awesome_jetpack.application.MyApplication;
import com.xiao.awesome_jetpack.common.SharedViewModel;
import com.xiao.awesome_jetpack.common.networkstate.NetworkStateManager;
import com.xiao.awesome_jetpack.utils.AdaptScreenUtils;
import com.xiao.awesome_jetpack.utils.ScreenUtils;

/**
 * 所有Activity的父类
 */
public class BaseActivity extends AppCompatActivity {

    // 贯穿整个项目的,初始化必须共享（单例的方式 MyApplication传入的ViewModelStore是同一个）
    protected SharedViewModel mSharedViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);

        getLifecycle().addObserver(NetworkStateManager.getInstance());
    }

    /**
     * 方便调试Debug
     * @return
     */
    public boolean isDebug() {
        return getApplication().getApplicationInfo() != null &&
                (getApplication().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    /**
     * 使用 appolication 共享
     * 给此BaseActivity 得到ViewModelProvider
     * application
     * @return  唯一 ViewModelProvider  ViewModel
     */
    private ViewModelProvider getAppViewModelProvider() {
        return ((MyApplication) getApplication()).getAppViewModelProvider(this);
    }

}
