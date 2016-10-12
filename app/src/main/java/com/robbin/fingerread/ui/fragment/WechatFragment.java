package com.robbin.fingerread.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.robbin.fingerread.adapter.WechatAdapter;
import com.robbin.fingerread.bean.ScienceBean;
import com.robbin.fingerread.bean.WechatArticalBean;
import com.robbin.fingerread.network.manager.RetrofitManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/12.
 */
public class WechatFragment extends  BaseListFragment {
    private WechatAdapter adapter;
    @Override
    protected void loadMore() {

    }

    @Override
    protected void loadLatestNews(String tag) {
        RetrofitManager.builderWechat().getArticalList(1,tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mTvLoadError.setVisibility(View.GONE);
                        showProgress();
                    }
                }).subscribe(new Action1<WechatArticalBean>() {
            @Override
            public void call(WechatArticalBean wechatArticalBean) {
                hideProgress();
                adapter.change(wechatArticalBean);
                refreshLayout.setRefreshing(false);
                mTvLoadError.setVisibility(View.GONE);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                hideProgress();
                refreshLayout.setRefreshing(false);
                mTvLoadError.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public RecyclerView.Adapter initAdapter() {
        adapter=new WechatAdapter(new WechatArticalBean(),getActivity());
        return adapter;
    }
}
