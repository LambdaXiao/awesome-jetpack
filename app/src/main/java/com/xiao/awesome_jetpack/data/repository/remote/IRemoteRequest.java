package com.xiao.awesome_jetpack.data.repository.remote;

import androidx.lifecycle.MutableLiveData;

import com.xiao.awesome_jetpack.ui.home.bean.BannerBean;
import com.xiao.awesome_jetpack.ui.home.bean.FeedArticleList;

import java.util.List;

/**
 * 远程请求标准接口（在仓库里面） 也就是网络Network请求
 * 只为 RemoteRepository 服务
 */
public interface IRemoteRequest {

    // 得到首页banner
    void getBanner(MutableLiveData<List<BannerBean>> bannerData);

    // 得到首页文章列表
    void getArticleList(MutableLiveData<FeedArticleList> feedArticleList,int pagenum);
}
