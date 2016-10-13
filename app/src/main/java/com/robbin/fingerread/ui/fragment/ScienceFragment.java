package com.robbin.fingerread.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.robbin.fingerread.adapter.ScienceAdapter;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.bean.ScienceBean;
import com.robbin.fingerread.network.manager.RetrofitManager;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by OneAPM on 2016/9/2.
 */
public class ScienceFragment extends BaseListFragment {

    private ScienceAdapter adapter;

    @Override
    protected void loadMore(int currentPage,String tag) {

    }

    @Override
    protected void loadLatestNews(String tag) {
        RetrofitManager.builderScience().getScience(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress();
                    }
                }).map(new Func1<ScienceBean, ScienceBean>() {
            @Override
            public ScienceBean call(ScienceBean scienceBean) {
                return  changeReadStatus(scienceBean);
            }
        }).subscribe(new Action1<ScienceBean>() {
            @Override
            public void call(ScienceBean scienceBean) {
                hideProgress();
                adapter.change(scienceBean);
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

    private ScienceBean changeReadStatus(ScienceBean scienceBean) {
//        List<String> allRead = new DailyDao(getActivity()).getAllRead();
//
//        for(){
//
//            }
        return scienceBean;
    }

    @Override
    public RecyclerView.Adapter initAdapter() {
        adapter=new ScienceAdapter(new ArrayList<ArticleBean>(),getActivity());
        return adapter;
    }
}
