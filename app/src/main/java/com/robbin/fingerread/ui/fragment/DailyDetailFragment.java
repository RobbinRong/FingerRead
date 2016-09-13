package com.robbin.fingerread.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.FingerReadApplication;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.constant.BaseUrl;
import com.robbin.fingerread.dao.CollectDao;
import com.robbin.fingerread.network.manager.RetrofitManager;
import com.robbin.fingerread.ui.activity.DailyDetailActivity;
import com.robbin.fingerread.utils.HtmlUtil;

import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by OneAPM on 2016/8/30.
 */
public class DailyDetailFragment extends BaseFragment{


    @Bind(R.id.iv_header)
    ImageView mIvHeader;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_source)
    TextView mTvSource;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.wv_news)
    WebView mWvNews;
    @Bind(R.id.nested_view)
    NestedScrollView mNestedView;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    @Bind(R.id.mainContent)
    CoordinatorLayout mainContent;
    protected boolean isCollected;
    private News news;
    private CollectDao dao;
    private  NewsDetail detail;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        news=getArguments().getParcelable(DailyDetailActivity.KEY_NEWS);
        detail=getArguments().getParcelable(DailyDetailActivity.KEY_DETAIL);
        setHasOptionsMenu(true);
        init();
        loadData();
    }

    public void loadSucess(NewsDetail detail){
        List<NewsDetail> allDaily = dao.getAllDaily();
        for(NewsDetail detail1:allDaily){
            if(detail1.getId()==detail.getId()){
                isCollected=true;
                break;
            }
        }
        Glide.with(getActivity())
                .load(detail.getImage()).into(mIvHeader);
        mTvTitle.setText(detail.getTitle());
        mTvSource.setText(detail.getImage_source());
        StringBuffer stringBuffer = HtmlUtil.handleHtml(detail.getBody(),false);
        mWvNews.setDrawingCacheEnabled(true);
        mWvNews.loadDataWithBaseURL("file:///android_asset/", stringBuffer.toString(), "text/html", "utf-8", null);
    }

    private void loadData() {
        if(detail!=null){
          loadSucess(detail);
            return;
        }

        RetrofitManager.builderZhiHu().getNewsDetail(news.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                  //      showProgress();
                    }
                }).subscribe(new Action1<NewsDetail>() {
            @Override
            public void call(NewsDetail newsDetail) {
                //hideProgress();
                detail=newsDetail;
                loadSucess(detail);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //hideProgress();
                mTvLoadError.setVisibility(View.VISIBLE);
            }
        });
    }
//
//    public void showProgress() {
//        mPbLoading.setVisibility(View.VISIBLE);
//    }
//
//    public void hideProgress() {
//        mPbLoading.setVisibility(View.GONE);
//    }

    private void init() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        ActionBar supportActionBar = activity.getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            if(detail!=null){
                supportActionBar.setTitle(detail.getTitle());
            }else if (news!=null){
                supportActionBar.setTitle(news.getTitle());
            }
        }
        mCollapsingToolbarLayout.setTitleEnabled(true);
        dao=new CollectDao(FingerReadApplication.AppContext);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail,menu);
        updateCollectionMenu(menu.findItem(R.id.menu_collect));
    }
    protected void updateCollectionMenu(MenuItem item){
        if(isCollected){
            item.setIcon(R.drawable.ic_star_black);
        }else {
            item.setIcon(R.drawable.ic_star_white);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                return  true;
            case R.id.menu_action_share:
                share();
                return  true;
            case R.id.menu_collect:
                if(isCollected){
                    removeFromCollection();
                    isCollected=false;
                    updateCollectionMenu(item);
                    Snackbar.make(mainContent, R.string.notify_remove_from_collection,Snackbar.LENGTH_SHORT).show();
                }
                else {
                    addToCollection();
                    isCollected = true;
                    updateCollectionMenu(item);
                    Snackbar.make(mainContent, R.string.notify_add_to_collection,Snackbar.LENGTH_SHORT).show();

                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addToCollection() {
        dao.insertDaily(detail);
    }

    private void removeFromCollection() {
        dao.delete(detail);
    }

    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        if(news!=null){
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "["+news.getTitle()+"]:"+ BaseUrl.BASE_ZHIHU_URL+"story/"+news.getId()+" ("+getString(R.string.text_share_from)+getString(R.string.app_name)+")");
        }else if(detail!=null){
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "["+detail.getTitle()+"]:"+ BaseUrl.BASE_ZHIHU_URL+"story/"+detail.getId()+" ("+getString(R.string.text_share_from)+getString(R.string.app_name)+")");
        }
        startActivity(Intent.createChooser(sharingIntent,getString(R.string.hint_share_to)));
    }

    public static Fragment newInstance(News news) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DailyDetailActivity.KEY_NEWS, news);
        Fragment fragment = new DailyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    public static Fragment newInstance(NewsDetail detail) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DailyDetailActivity.KEY_DETAIL, detail);
        Fragment fragment = new DailyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
