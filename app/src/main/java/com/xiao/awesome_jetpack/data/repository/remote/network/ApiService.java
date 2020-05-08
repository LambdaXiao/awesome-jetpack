package com.xiao.awesome_jetpack.data.repository.remote.network;

import com.xiao.awesome_jetpack.ui.home.bean.BannerBean;
import com.xiao.awesome_jetpack.ui.home.bean.FeedArticleList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 描述：API配置
 */
public interface ApiService {
    public static String HOST = "https://www.wanandroid.com/";

    @GET("article/list/{pagenum}/json")
    Observable<BaseResponse<FeedArticleList>> getHomeArticle(@Path("pagenum") int pagenum);

    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean>>> getBanner();
}
