package com.xiao.awesome_jetpack.common;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * 描述：Fragment的生命周期监听
 */
public class FragmentLifecycle implements DefaultLifecycleObserver {
    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d("test","onCreate"+owner.toString());
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.d("test","onStart"+owner.toString());
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.d("test","onResume"+owner.toString());
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.d("test","onPause"+owner.toString());
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.d("test","onStop"+owner.toString());
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d("test","onDestroy"+owner.toString());
    }
}
