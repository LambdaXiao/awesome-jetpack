package com.xiao.awesome_jetpack.data.repository.remote;

import androidx.lifecycle.MutableLiveData;

import com.xiao.awesome_jetpack.data.repository.remote.network.BaseObserver;
import com.xiao.awesome_jetpack.data.repository.remote.network.BaseResponse;
import com.xiao.awesome_jetpack.data.repository.remote.network.RetrofitManager;
import com.xiao.awesome_jetpack.ui.home.bean.BannerBean;
import com.xiao.awesome_jetpack.ui.home.bean.FeedArticleList;

import java.util.List;

/**
 * 描述：仓库角色 网络请求  单例
 */
public class RemoteRepository implements IRemoteRequest {

    private static RemoteRepository sRemoteRepository;

    public static RemoteRepository getInstance() {
        if (sRemoteRepository == null) {
            synchronized (RemoteRepository.class) {
                if (sRemoteRepository == null) {
                    sRemoteRepository = new RemoteRepository();
                }
            }
        }
        return sRemoteRepository;
    }

    @Override
    public void getBanner(MutableLiveData<List<BannerBean>> bannerData) {
        RetrofitManager.getInstance().getService().getBanner()
                .compose(RetrofitManager.getInstance().threadTransformer())
                .subscribe(new BaseObserver<BaseResponse<List<BannerBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<BannerBean>> response) {
                        List<BannerBean> bannerList = response.getData();
                        bannerData.setValue(bannerList);
                    }
                });
    }

    @Override
    public void getArticleList(MutableLiveData<FeedArticleList> feedArticleList,int pagenum) {
        RetrofitManager.getInstance().getService().getHomeArticle(pagenum)
                .compose(RetrofitManager.getInstance().threadTransformer())
                .subscribe(new BaseObserver<BaseResponse<FeedArticleList>>() {
                    @Override
                    public void onSuccess(BaseResponse<FeedArticleList> response) {
                        FeedArticleList mArticleData = response.getData();
                        feedArticleList.setValue(mArticleData);
                    }
                });
    }
}
