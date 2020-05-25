package com.xiao.awesome_jetpack.ui.collect.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CollectViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CollectViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("收藏中心");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
