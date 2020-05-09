package com.xiao.awesome_jetpack.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //建立绑定
        mActivityMainBinding.setVm(mMainViewModel);
        // 让感应生效
        mActivityMainBinding.setLifecycleOwner(this);

        setSupportActionBar(new Toolbar(this));
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 处理三个  Fragment
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.navigation_home,
                        R.id.navigation_collect,
                        R.id.navigation_personal)
                        .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


}
