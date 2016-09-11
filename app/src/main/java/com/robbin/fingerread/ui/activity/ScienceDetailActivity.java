package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.ui.fragment.ScienceDetailFragment;


public class ScienceDetailActivity extends BaseActivity {
    public static  final  String KEY_SCIENCE="key_science";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_science_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ArticleBean articleBean= (ArticleBean) getIntent().getSerializableExtra(KEY_SCIENCE);
        showScienceDetailFragment(articleBean);
    }
    private void showScienceDetailFragment(ArticleBean articleBean) {
        Fragment fragment= ScienceDetailFragment.newInstance(articleBean);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, fragment, ScienceDetailFragment.TAG);
        transaction.commit();
    }
    public static  void start(Context context, ArticleBean articleBean){
        Intent intent=new Intent(context,ScienceDetailActivity.class);
        intent.putExtra(KEY_SCIENCE,articleBean);
        context.startActivity(intent);
    }
}
