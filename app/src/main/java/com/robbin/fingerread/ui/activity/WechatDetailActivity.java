package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.bean.WechatArticalBean;

public class WechatDetailActivity extends AppCompatActivity {

    public static  final String KEY_WECHAT="key_wechat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_detail);
    }
    public static  void start(Context context, WechatArticalBean.Content content){
        Intent intent=new Intent(context,WechatDetailActivity.class);
        intent.putExtra(KEY_WECHAT,content);
        context.startActivity(intent);
    }
}
