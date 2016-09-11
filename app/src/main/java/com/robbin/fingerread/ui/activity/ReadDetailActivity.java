package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.BookBean;
import com.robbin.fingerread.ui.fragment.ReadDetailFragment;

public class ReadDetailActivity extends BaseActivity {
    public static final String KEY_READ = "key_read";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        BookBean bookBean=getIntent().getParcelableExtra(KEY_READ);
        showReadDetailFragment(bookBean);

    }

    private void showReadDetailFragment(BookBean bookBean) {
        Fragment fragment= ReadDetailFragment.newInstance(bookBean);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, ReadDetailFragment.TAG);
        transaction.commit();
    }

    public static  void  start(Context context, BookBean bookBean){
        Intent intent=new Intent(context,ReadDetailActivity.class);
        intent.putExtra(KEY_READ,bookBean);
        context.startActivity(intent);
    }
}
