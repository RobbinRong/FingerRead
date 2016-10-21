package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.MovieCommonZYAdapter;
import com.robbin.fingerread.bean.MovieCommonsZY;
import com.robbin.fingerread.network.manager.RetrofitManager;
import com.robbin.fingerread.ui.activity.MovieCommentZYActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/21.
 */
public class MovieCommentZYFragment extends  BaseListFragment {
    private MovieCommonZYAdapter adapter;

//    private static  final  int OFFSET=0;
    private static  final int LIMIT=20;
    @Override
    protected void loadMore(int currentPage, String tag, int position) {//position就是mivieid
        getDataFromHttp(currentPage,String.valueOf(position),true);
    }

    @Override
    protected void loadLatestNews(String tag, int position) {
        getDataFromHttp(0,String.valueOf(position),false);
    }

    @Override
    public RecyclerView.Adapter initAdapter() {
        adapter=new MovieCommonZYAdapter(null,getActivity());
        return adapter;
    }

    private void getDataFromHttp(int currentpage,String  id, final boolean ismore) {
        RetrofitManager.builderMaoYan().getMoviCommosZY(id,currentpage*LIMIT,LIMIT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieCommonsZY>() {
                    @Override
                    public void call(MovieCommonsZY commonZY) {

                        if(ismore){
                            adapter.append(commonZY);
                        }
                        else {
                            adapter.change(commonZY);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "callzy: "+throwable.getMessage());
                    }
                });
    }

    public static Fragment newInstance(String id) {
            Bundle bundle = new Bundle();
            bundle.putString("position",id);
            Fragment fragment = new MovieCommentZYFragment();
            fragment.setArguments(bundle);
            return fragment;

    }
}
