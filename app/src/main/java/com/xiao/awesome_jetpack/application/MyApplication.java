package com.xiao.awesome_jetpack.application;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * 描述：整个项目的 Application
 * <p>
 * 为什么要 “implements ViewModelStoreOwner” 就是为了 让整个项目持有一份 被观察者接口，为了完成共享
 */
public class MyApplication extends BaseApp implements ViewModelStoreOwner {

    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.Factory mFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppViewModelStore = new ViewModelStore();
        // todo，这里可以完成一系列的初始化工作

    }

    public static MyApplication getApplication() {
        return (MyApplication) getApp();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    /**
     * 暴露出去，对外提供唯一一份ViewModelProvider
     *
     * @param activity
     * @return
     */
    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((MyApplication) activity.getApplication(), getAppFactory(activity));
    }

    /**
     * 共享
     * 内部享有 就是为了拿到 ViewModelProvider.Factory
     *
     * @param activity
     * @return
     */
    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    /**
     * 内部享有 检查Application
     *
     * @param activity
     * @return
     */
    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

}
