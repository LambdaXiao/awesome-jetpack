package com.xiao.awesome_jetpack.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xiao.awesome_jetpack.MyApplication;
import com.xiao.awesome_jetpack.global.SharedViewModel;


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


    public void showLongToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


    public void showShortToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    // 给当前BaseFragment用的
    private ViewModelProvider getAppViewModelProvider() {
        return ((MyApplication) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }

    // 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
//    protected ViewModelProvider getFragmentViewModelProvider(Fragment fragment) {
//        return new ViewModelProvider(fragment, fragment.getDefaultViewModelProviderFactory());
//    }

    // 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
//    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
//        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
//    }

}
