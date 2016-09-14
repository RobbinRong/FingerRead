package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.AutoLoadOnScrollListener;
import com.robbin.fingerread.adapter.DailyAdapter;
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.bean.NewsList;
import com.robbin.fingerread.dao.DailyDao;
import com.robbin.fingerread.network.manager.RetrofitManager;
import com.robbin.fingerread.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by OneAPM on 2016/8/23.
 */
public  class DailyFragment extends BaseFragment implements RefreshLayout.OnRefreshListener{
//
//    @Bind(R.id.toolbar)
//    Toolbar mToolbar;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    @Bind(R.id.rcv_daily_list)
    RecyclerView mRcvNewsList;
    @Bind(R.id.ptr_news_list)
    RefreshLayout refreshLayout;
    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar mPbLoading;
    LinearLayoutManager linearLayoutManager;
    private DailyAdapter dailyAdapter;
    private AutoLoadOnScrollListener autoLoadOnScrollListener;

    private String curDate;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(mToolbar);

        //refreshLayout.setOnLoadListener(this);

        getActivity().findViewById(R.id.tab_layout).setVisibility(View.GONE);
        refreshLayout.setOnRefreshListener(this);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        mRcvNewsList.setLayoutManager(linearLayoutManager);
        mRcvNewsList.setHasFixedSize(true);
        mRcvNewsList.setItemAnimator(new DefaultItemAnimator());
        dailyAdapter = new DailyAdapter(getActivity(),new ArrayList<News>());//=====================================
        mRcvNewsList.setAdapter(dailyAdapter);
        loadLatestNews();
        autoLoadOnScrollListener = new AutoLoadOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMore(curDate);
            }
        };
        mRcvNewsList.addOnScrollListener(autoLoadOnScrollListener);
        mTvLoadError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLatestNews();
            }
        });
    }

    //加载更多
    private void loadMore(String date) {

        RetrofitManager.builderZhiHu().getBeforeNews(date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<NewsList, NewsList>() {
                    @Override
                    public NewsList call(NewsList newsList) {
                        return changeReadStatus(newsList);
                    }
                })
                .subscribe(new Action1<NewsList>() {
                    @Override
                    public void call(NewsList newsList) {
                        autoLoadOnScrollListener.setLoading(false);
                        dailyAdapter.addData(newsList.getStories());
                        curDate = newsList.getDate();
                        mTvLoadError.setVisibility(View.GONE);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        autoLoadOnScrollListener.setLoading(false);
                        hideProgress();
                        mTvLoadError.setVisibility(View.VISIBLE);
                    }
                });
    }
    private void loadLatestNews() {

        RetrofitManager.builderZhiHu().getLatestNews().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress();
                    }
                }).map(new Func1<NewsList, NewsList>() {
            @Override
            public NewsList call(NewsList newsList) {
                return changeReadStatus(newsList);
            }
        }).subscribe(new Action1<NewsList>() {
            @Override
            public void call(NewsList newsList) {
                hideProgress();
                curDate=newsList.getDate();
                dailyAdapter.change(newsList.getStories());
                refreshLayout.setRefreshing(false);
                mTvLoadError.setVisibility(View.GONE);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                hideProgress();
                mTvLoadError.setVisibility(View.VISIBLE);
            }
        });
    }

    public NewsList changeReadStatus(NewsList newsList){
        List<String> allRead = new DailyDao(getActivity()).getAllRead();
        for(News news:newsList.getStories()){
            news.setDate(newsList.getDate());
            for (String id:allRead){
                if(id.equals(news.getId()+"")){
                    news.setRead(true);
                }
            }

        }
        return newsList;
    }

    //刷新
    @Override
    public void onRefresh() {
        loadLatestNews();

    }
    public void showProgress() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mPbLoading.setVisibility(View.GONE);
    }


}
