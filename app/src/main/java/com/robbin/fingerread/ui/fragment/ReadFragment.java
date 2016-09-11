package com.robbin.fingerread.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.robbin.fingerread.adapter.ReadAdapter;
import com.robbin.fingerread.bean.ReadingBean;
import com.robbin.fingerread.network.manager.RetrofitManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by OneAPM on 2016/8/23.
 */
public class ReadFragment extends BaseListFragment {
    private ReadAdapter adapter;

    @Override
    protected void loadMore() {

    }

    @Override
    protected void loadLatestNews(String tag) {
        RetrofitManager.builderRead().getBook(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress();
                    }
                }).map(new Func1<ReadingBean, ReadingBean>() {
            @Override
            public ReadingBean call(ReadingBean readingBean) {
                return  changeReadStatus(readingBean);
            }
        }).subscribe(new Action1<ReadingBean>() {
            @Override
            public void call(ReadingBean readingBean) {
                hideProgress();
                adapter.change(readingBean);
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

    private ReadingBean changeReadStatus(ReadingBean readingBean) {
//        List<String> allRead = new DailyDao(getActivity()).getAllRead();
//
//        for(){
//
//            }
        return readingBean;
    }

    @Override
    public RecyclerView.Adapter initAdapter() {
        adapter=new ReadAdapter(new ReadingBean(),getActivity());
        return adapter;
    }
}
