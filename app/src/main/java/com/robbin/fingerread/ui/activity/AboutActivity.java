package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.robbin.fingerread.R;

import butterknife.Bind;

public class AboutActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.tv_version)
    TextView mTvVersion;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbar.setTitle(getString(R.string.about));
        mTvVersion.setText(getVersion());

    }

    public static void start(Context context){
        Intent intent=new Intent(context,AboutActivity.class);
        context.startActivity(intent);
    }
    private String getVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return  packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                this.finish();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
