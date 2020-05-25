package com.xiao.awesome_jetpack.ui.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    // TODO: Implement the ViewModel
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("首页");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
