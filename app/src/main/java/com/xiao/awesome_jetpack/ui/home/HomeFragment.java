package com.xiao.awesome_jetpack.ui.home;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiao.awesome_jetpack.R;
import com.xiao.awesome_jetpack.base.BaseFragment;
import com.xiao.awesome_jetpack.databinding.HomeFragmentBinding;
import com.xiao.awesome_jetpack.request.RequestHomeViewModel;
import com.xiao.awesome_jetpack.ui.home.adapter.ArticleListAdapter;
import com.xiao.awesome_jetpack.ui.home.bean.BannerBean;
import com.xiao.awesome_jetpack.ui.home.bean.FeedArticleBean;
import com.xiao.awesome_jetpack.ui.home.viewmodel.HomeViewModel;
import com.xiao.awesome_jetpack.utils.ScreenUtils;
import com.xiao.awesome_jetpack.widget.RecyclerViewBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    private RequestHomeViewModel requestHomeViewModel; // 请求的ViewModel Request
    private List<BannerBean> mBannerList;
    private ArticleListAdapter articleListAdapter;
    private List<FeedArticleBean> mFeedArticleDataList;
    private int pagenum = 0;//页面页数下标
    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        requestHomeViewModel = new ViewModelProvider(this).get(RequestHomeViewModel.class);

        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container, false);
        binding.setVm(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        //banner
        binding.rvBanner.setIndicatorInterval(5000);
        mBannerList = new ArrayList<>();
        binding.rvBanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int position, AppCompatImageView bannerView) {
                if (mActivity != null) {
                    //加入判断防止报 You cannot start a load for a destroyed activity
                    Glide.with(mActivity)
                            .load(mBannerList.get(position).getImagePath())
                            .centerCrop()
                            .override(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenWidth() * 444 / 800)
                            .into(bannerView);
                }
            }
        });

        binding.rvBanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int position) {
//                Banner banner = banners.get(position);
//                if (banner != null) {
//                    try {
//                        AnalyzeUrlHelp.analyze(mActivity, banner.getUrl(), banner.getTitle(), banner.getDes());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });

        requestHomeViewModel.getBannerData().observe(getViewLifecycleOwner(),bannerBeans -> {
            mBannerList.clear();
            mBannerList.addAll(bannerBeans);
            binding.rvBanner.setRvBannerData(mBannerList);
        });

        binding.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.mRecyclerView.setHasFixedSize(true);
        binding.mSmartRefreshLayout.setEnableLoadMore(true);
        binding.mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = false;
                isLoadMore = true;
                pagenum++;
                requestHomeViewModel.requestArticleList(pagenum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                isLoadMore = false;
                pagenum = 0;
                requestHomeViewModel.requestArticleList(pagenum);
            }
        });

        mFeedArticleDataList = new ArrayList<FeedArticleBean>();
        articleListAdapter = new ArticleListAdapter(getActivity(), mFeedArticleDataList);
        binding.mRecyclerView.setAdapter(articleListAdapter);
        //数据变化观察
        requestHomeViewModel.getArticleList().observe(getViewLifecycleOwner(),feedArticleList -> {
            if (isRefresh) {
                mFeedArticleDataList.clear();
            }
            mFeedArticleDataList.addAll(feedArticleList.getDatas());
            articleListAdapter.setList(mFeedArticleDataList);
            binding.mSmartRefreshLayout.finishRefresh();
            binding.mSmartRefreshLayout.finishLoadMore();
            isRefresh = false;
            isLoadMore = false;
        });

        requestHomeViewModel.requestBanner();
        requestHomeViewModel.requestArticleList(pagenum);
    }

}
