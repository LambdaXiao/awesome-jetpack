package com.xiao.awesome_jetpack.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xiao.awesome_jetpack.data.repository.remote.RemoteRepository;
import com.xiao.awesome_jetpack.data.repository.remote.network.BaseObserver;
import com.xiao.awesome_jetpack.data.repository.remote.network.BaseResponse;
import com.xiao.awesome_jetpack.data.repository.remote.network.RetrofitManager;
import com.xiao.awesome_jetpack.ui.home.bean.BannerBean;
import com.xiao.awesome_jetpack.ui.home.bean.FeedArticleList;

import java.util.List;

/**
 *  首页数据请求ViewModel
 * 描述：提供访问数据层的方法，并返回LiveData，以便UI层可以建立观察者关系。
 */
public class RequestHomeViewModel extends ViewModel {
    MutableLiveData<FeedArticleList> mArticleList;
    MutableLiveData<List<BannerBean>> mBannerData;

    public RequestHomeViewModel() {
        mBannerData = new MutableLiveData<List<BannerBean>>();
        mArticleList = new MutableLiveData<FeedArticleList>();
    }

    public MutableLiveData<List<BannerBean>> getBannerData() {
        return mBannerData;
    }

    public MutableLiveData<FeedArticleList> getArticleList() {
        return mArticleList;
    }

    /**
     * 首页banner
     */
    public void requestBanner() {
        RemoteRepository.getInstance().getBanner(getBannerData());
    }

    /**
     * 获取首页文章列表
     */
    public void requestArticleList(int pagenum) {
        RemoteRepository.getInstance().getArticleList(getArticleList(),pagenum);
    }
}
