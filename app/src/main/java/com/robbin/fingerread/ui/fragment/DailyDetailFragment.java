package com.robbin.fingerread.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.constant.BaseUrl;
import com.robbin.fingerread.network.manager.RetrofitManager;
import com.robbin.fingerread.ui.activity.DailyDetailActivity;
import com.robbin.fingerread.utils.HtmlUtil;

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


    private News news;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
    news=getArguments().getParcelable(DailyDetailActivity.KEY_NEWS);
        setHasOptionsMenu(true);
        init();
        loadData();
    }

    private void loadData() {

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
                Glide.with(getActivity())
                        .load(newsDetail.getImage()).into(mIvHeader);
                mTvTitle.setText(newsDetail.getTitle());
                mTvSource.setText(newsDetail.getImage_source());
                StringBuffer stringBuffer = HtmlUtil.handleHtml(newsDetail.getBody(),false);
                mWvNews.setDrawingCacheEnabled(true);
                mWvNews.loadDataWithBaseURL("file:///android_asset/", stringBuffer.toString(), "text/html", "utf-8", null);

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
        }
        mCollapsingToolbarLayout.setTitleEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail,menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "["+news.getTitle()+"]:"+ BaseUrl.BASE_ZHIHU_URL+"story/"+news.getId()+" ("+getString(R.string.text_share_from)+getString(R.string.app_name)+")");
        startActivity(Intent.createChooser(sharingIntent,getString(R.string.hint_share_to)));
    }

    public static Fragment newInstance(News news) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DailyDetailActivity.KEY_NEWS, news);
        Fragment fragment = new DailyDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
