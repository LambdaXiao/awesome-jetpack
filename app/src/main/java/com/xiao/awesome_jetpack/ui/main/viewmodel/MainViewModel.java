package com.xiao.awesome_jetpack.ui.main.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 描述：
 */
public class MainViewModel extends ViewModel {
    private MutableLiveData<String> livedata;
    public MutableLiveData<String> getLiveData(){
        if(livedata == null){
            livedata = new MutableLiveData<String>();
        }
        return livedata;
    }

    public void onClick(){
        getLiveData().setValue("345");
    }
}
