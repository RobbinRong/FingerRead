package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.bean.WechatArticalBean;
import com.robbin.fingerread.ui.fragment.BaseListFragment;
import com.robbin.fingerread.ui.fragment.DailyDetailFragment;
import com.robbin.fingerread.ui.fragment.WechatDetailFragment;

public class WechatDetailActivity extends BaseActivity {


    public static  final String KEY_WECHAT="key_wechat";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        WechatArticalBean.Content content= (WechatArticalBean.Content) intent.getSerializableExtra(KEY_WECHAT);
        if(content!=null){
            showWechatDetailFragment(content);
        }
    }

    private void showWechatDetailFragment(WechatArticalBean.Content content) {
        Fragment fragment= WechatDetailFragment.newInstance(content);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, WechatDetailFragment.TAG);
        transaction.commit();
    }

    public static  void start(Context context, WechatArticalBean.Content content){
        Intent intent=new Intent(context,WechatDetailActivity.class);
        intent.putExtra(KEY_WECHAT,content);
        context.startActivity(intent);
    }
}
