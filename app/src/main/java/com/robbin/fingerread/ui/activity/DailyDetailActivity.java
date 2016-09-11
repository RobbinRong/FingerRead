package com.robbin.fingerread.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.ui.fragment.DailyDetailFragment;

public class DailyDetailActivity extends BaseActivity {
    public static final String KEY_NEWS = "key_news";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        News news= getIntent().getParcelableExtra(KEY_NEWS);
        showNewsDetailFragment(news);
    }
    private void showNewsDetailFragment(News news) {
        Fragment fragment= DailyDetailFragment.newInstance(news);
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
}
