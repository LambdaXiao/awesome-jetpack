package com.xiao.awesome_jetpack.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xiao.awesome_jetpack.application.MyApplication;
import com.xiao.awesome_jetpack.common.FragmentLifecycle;
import com.xiao.awesome_jetpack.common.SharedViewModel;


/**
 * 所有Fragment的父类
 */
public class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;
    public SharedViewModel sharedViewModel;  // 使用共享

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);

        getLifecycle().addObserver(new FragmentLifecycle());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }


    public boolean isDebug() {
        return mActivity.getApplicationContext().getApplicationInfo() != null &&
                (mActivity.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    // 给当前BaseFragment用的
    private ViewModelProvider getAppViewModelProvider() {
        return ((MyApplication) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }


}
