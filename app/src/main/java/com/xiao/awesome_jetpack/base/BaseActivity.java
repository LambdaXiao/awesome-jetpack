package com.xiao.awesome_jetpack.base;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.xiao.awesome_jetpack.MyApplication;
import com.xiao.awesome_jetpack.global.SharedViewModel;
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
     * Toast 长时间
     * @param text
     */
    public void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * Toast 短时间
     * @param text
     */
    public void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
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

    /**
     * 暴露给自己的子类 得到ViewModelProvider
     * @param activity
     * 子类的 activity
     * @return 唯一  ViewModelProvider  ViewModel
     */
    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
    }

}
