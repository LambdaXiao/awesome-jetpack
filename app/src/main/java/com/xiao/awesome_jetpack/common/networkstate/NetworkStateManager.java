package com.xiao.awesome_jetpack.common.networkstate;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import static java.util.Objects.requireNonNull;

/**
 * 描述：此观察者 去观察BaseActivity的声明周期方法
 */
public class NetworkStateManager implements DefaultLifecycleObserver {

    /*
     * 静态内部类方式实现单例模式
     *
     *当第一次加载Singleton类时并不会初始化SINGLRTON,只有第一次调用getInstance方法的时候才会初始化SINGLETON
     *第一次调用getInstance 方法的时候虚拟机才会加载SingletonHoder类,这种方式不仅能够保证线程安全,也能够保证对象的唯一,
     *还延迟了单例的实例化,所有推荐使用这种方式
     * */
    private static class NetworkStateManagerInstance{
        private static final NetworkStateManager INSTANCE = new NetworkStateManager();
    }

    public static NetworkStateManager getInstance(){
        return NetworkStateManagerInstance.INSTANCE;
    }

    private NetworkStateReceive mNetworkStateReceive;

    /**
     * 观察 BaseActivity 的 生命周期方法后
     * 就是注册一个 广播，此广播可以接收到信息（然后 输出 “网络不给力”）
     */
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        mNetworkStateReceive = new NetworkStateReceive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).registerReceiver(mNetworkStateReceive, filter);
        } else if (owner instanceof Fragment) {
            requireNonNull(((Fragment) owner).getActivity())
                    .registerReceiver(mNetworkStateReceive, filter);
        }
    }

    /**
     * 那么观察到 观察 BaseActivity的生命周期方法后
     * 就是移除一个 广播
     * @param owner
     */
    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).unregisterReceiver(mNetworkStateReceive);
        } else if (owner instanceof Fragment) {
            requireNonNull(((Fragment) owner).getActivity())
                    .unregisterReceiver(mNetworkStateReceive);
        }
    }
}
