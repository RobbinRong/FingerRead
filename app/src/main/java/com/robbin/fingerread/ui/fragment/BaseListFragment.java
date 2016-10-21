package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.AutoLoadOnScrollListener;
import com.robbin.fingerread.constant.ScienceApi;
import com.robbin.fingerread.view.RefreshLayout;

import butterknife.Bind;

/**
 * Created by OneAPM on 2016/9/6.
 */
public abstract class BaseListFragment extends BaseFragment implements RefreshLayout.OnRefreshListener {
    @Bind(R.id.pull_to_refresh)
    RefreshLayout refreshLayout;

    @Bind(R.id.recyclerView)
    RecyclerView recContent;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar mPbLoading;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter adapter;
    AutoLoadOnScrollListener autoLoadOnScrollListener;
    private int position;//这两个都是一个只有listview的fragment去请求数据时所需要的，
    // 比如我我要请求哪个tag的科学、日报、我要请求哪个id（int）的电影的评论
    private String category;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if(getArguments()!=null){
            position = getArguments().getInt(getString(R.string.id_pos));
            if(getArguments().getSerializable(getString(R.string.id_category))!=null){
                category = getArguments().getSerializable(getString(R.string.id_category)).toString();
            }
        }
        refreshLayout.setOnRefreshListener(this);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recContent.setLayoutManager(linearLayoutManager);
        recContent.setHasFixedSize(true);
        recContent.setItemAnimator(new DefaultItemAnimator());
        adapter=initAdapter();
        recContent.setAdapter(adapter);
        loadLatestNews(category,position);
        autoLoadOnScrollListener=new AutoLoadOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
            loadMore(currentPage,category,position);
            }
        };
        recContent.addOnScrollListener(autoLoadOnScrollListener);
        mTvLoadError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLatestNews(category,position);
            }
        });

    }

    protected abstract void loadMore(int currentPage,String tag,int position);

    protected abstract void loadLatestNews(String tag,int position);

    public abstract RecyclerView.Adapter initAdapter() ;

    @Override
    public void onRefresh() {
        loadLatestNews(category,position);

    }
    public void showProgress() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mPbLoading.setVisibility(View.GONE);
    }

}
