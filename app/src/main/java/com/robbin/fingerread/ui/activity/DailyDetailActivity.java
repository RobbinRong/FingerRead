package com.robbin.fingerread.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.ui.fragment.DailyDetailFragment;

public class DailyDetailActivity extends BaseActivity {
    public static final String KEY_NEWS = "key_news";
    public static final String KEY_DETAIL = "key_detail";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        News news=intent .getParcelableExtra(KEY_NEWS);
        NewsDetail detail=intent .getParcelableExtra(KEY_DETAIL);
        if(news!=null){
            showNewsDetailFragment(news);
        }
        else if(detail!=null){
            showNewsDetailFragment(detail);
        }
    }
    private void showNewsDetailFragment(News news) {
        Fragment fragment= DailyDetailFragment.newInstance(news);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, DailyDetailFragment.TAG);
        transaction.commit();
    }
    private void showNewsDetailFragment(NewsDetail detail) {
        Fragment fragment= DailyDetailFragment.newInstance(detail);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, DailyDetailFragment.TAG);
        transaction.commit();
    }


    public static  void start(Context context,  News newses){
        Intent intent=new Intent(context,DailyDetailActivity.class);
        intent.putExtra(KEY_NEWS,newses);
        context.startActivity(intent);
    }
    public static  void start(Context context,  NewsDetail detail){
        Intent intent=new Intent(context,DailyDetailActivity.class);
        intent.putExtra(KEY_DETAIL,detail);
        context.startActivity(intent);
    }
}
