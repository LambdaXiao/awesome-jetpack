package com.xiao.awesome_jetpack.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.xiao.awesome_jetpack.R;
import com.xiao.awesome_jetpack.base.BaseActivity;
import com.xiao.awesome_jetpack.databinding.ActivityMainBinding;
import com.xiao.awesome_jetpack.ui.main.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {

    private MainViewModel mMainViewModel;
    private ActivityMainBinding mActivityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);

        //建立绑定
        mActivityMainBinding.setVm(mMainViewModel);
        // 让感应生效
        mActivityMainBinding.setLifecycleOwner(this);
    }
}
