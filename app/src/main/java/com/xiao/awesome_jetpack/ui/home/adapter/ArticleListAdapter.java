package com.xiao.awesome_jetpack.ui.home.adapter;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.ViewDataBinding;

import com.xiao.awesome_jetpack.R;
import com.xiao.awesome_jetpack.common.adapter.BaseRecyclerAdapter;
import com.xiao.awesome_jetpack.databinding.ItemSearchPagerBinding;
import com.xiao.awesome_jetpack.ui.home.bean.FeedArticleBean;

import java.util.List;


/**
 * 描述：
 */
public class ArticleListAdapter extends BaseRecyclerAdapter<FeedArticleBean> {

    public ArticleListAdapter(Context ctx, List<FeedArticleBean> list) {
        super(ctx, list);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_search_pager;
    }

    @Override
    protected void bindData(ViewDataBinding binding, int position, FeedArticleBean article) {
        ItemSearchPagerBinding  itemSearchPagerBinding = (ItemSearchPagerBinding) binding;
        if (!TextUtils.isEmpty(article.getTitle())) {
            itemSearchPagerBinding.itemSearchPagerTitle.setText(article.getTitle());
        }
//        if (article.isCollect() || isCollectPage) {
//            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
//        } else {
        itemSearchPagerBinding.itemSearchPagerLikeIv.setImageResource(R.drawable.icon_like_article_not_selected);
//        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            itemSearchPagerBinding.itemSearchPagerAuthor.setText( article.getAuthor());
        }
//        setTag(helper, article);
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
//            if (isCollectPage) {
//                helper.setText(R.id.item_search_pager_chapterName, article.getChapterName());
//            } else {
            itemSearchPagerBinding.itemSearchPagerChapterName.setText(classifyName);
//            }
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            itemSearchPagerBinding.itemSearchPagerNiceDate.setText(article.getNiceDate());
        }
//        if (isSearchPage) {
//            CardView cardView = helper.getView(R.id.item_search_pager_group);
//            cardView.setForeground(null);
//            if (isNightMode) {
//                cardView.setBackground(ContextCompat.getDrawable(mContext, R.color.card_color));
//            } else {
//                cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
//            }
//        }
    }
}
