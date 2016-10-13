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
    private int position;
    private String category;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        position = getArguments().getInt(getString(R.string.id_pos));
        category = getArguments().getSerializable(getString(R.string.id_category)).toString();
        refreshLayout.setOnRefreshListener(this);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recContent.setLayoutManager(linearLayoutManager);
        recContent.setHasFixedSize(true);
        recContent.setItemAnimator(new DefaultItemAnimator());
        adapter=initAdapter();
        recContent.setAdapter(adapter);
        loadLatestNews(category);
        autoLoadOnScrollListener=new AutoLoadOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
            loadMore(currentPage,category);
            }
        };
        recContent.addOnScrollListener(autoLoadOnScrollListener);
        mTvLoadError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLatestNews(category);
            }
        });

    }

    protected abstract void loadMore(int currentPage,String tag);

    protected abstract void loadLatestNews(String tag);

    public abstract RecyclerView.Adapter initAdapter() ;

    @Override
    public void onRefresh() {
        loadLatestNews(category);

    }
    public void showProgress() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mPbLoading.setVisibility(View.GONE);
    }

}
