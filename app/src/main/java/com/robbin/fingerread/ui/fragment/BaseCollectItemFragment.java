package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.AutoLoadOnScrollListener;
import com.robbin.fingerread.dao.CollectDao;

import butterknife.Bind;

/**
 * Created by OneAPM on 2016/9/12.
 */
public abstract  class BaseCollectItemFragment extends  BaseFragment {

    @Bind(R.id.recyclerView)
    public RecyclerView recContent;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar mPbLoading;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter adapter;
    AutoLoadOnScrollListener autoLoadOnScrollListener;
    private int position;
    protected CollectDao dao;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        dao=new CollectDao(getActivity());
        position = getArguments().getInt(getString(R.string.id_pos));
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recContent.setLayoutManager(linearLayoutManager);
        recContent.setHasFixedSize(true);
        recContent.setItemAnimator(new DefaultItemAnimator());
        //adapter=new CollectDailyAdapter(getActivity(),allDaily);
        adapter=initAdapter();
        recContent.setAdapter(adapter);
    }

    public abstract  RecyclerView.Adapter  initAdapter();

}
