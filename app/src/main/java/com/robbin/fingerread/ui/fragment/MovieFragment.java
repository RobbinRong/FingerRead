package com.robbin.fingerread.ui.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.robbin.fingerread.adapter.MovieAdapter;
import com.robbin.fingerread.bean.MovieBean;
import com.robbin.fingerread.network.manager.RetrofitManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MovieFragment extends BaseListFragment {

    private int offset=0;
    private int limit=10;
    int position;
    private MovieAdapter adapter;
    private String[] type={"hot","coming"};
    public MovieFragment() {}
    @SuppressLint("ValidFragment")
    public MovieFragment(int position) {
        this.position=position;
    }

    @Override
    protected void loadMore(int currentPage, String tag,int position) {
        getDataFromHttp(true,currentPage);
    }

    private void getDataFromHttp(final boolean ismore,int currentPage){//ismore?String.valueOf(offset):String.valueOf(0)
        RetrofitManager.builderMovie().getMovies(type[position],String.valueOf(currentPage*limit),String.valueOf(limit))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress();
                    }
                })
                .subscribe(new Action1<MovieBean>() {
                    @Override
                    public void call(MovieBean movieBean) {
                        hideProgress();
                        if(ismore){
                            autoLoadOnScrollListener.setLoading(false);
                            adapter.append(movieBean,position);
                        }
                        else{
                            adapter.change(movieBean,position);
                        }
                        offset+=limit;
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
    protected void loadLatestNews(String tag,int position) {
        getDataFromHttp(false,0);
    }

    @Override
    public RecyclerView.Adapter initAdapter() {
        adapter=new MovieAdapter(new MovieBean(),getActivity());
        return  adapter;
    }
}
