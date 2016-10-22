package com.robbin.fingerread.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.BaseMovieAdapter;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/10/17.
 */
public class BaseMovieFragment extends BaseFragment implements View.OnClickListener,ViewPager.OnPageChangeListener{

    @Bind(R.id.vp_movie)
    ViewPager vpMovie;
    @Bind(R.id.btn_coming)
    Button btnComing;
    @Bind(R.id.btn_hot)
    Button btnHot;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_movie;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        getActivity().findViewById(R.id.tab_layout).setVisibility(View.GONE);
        BaseMovieAdapter baseMovieAdapter=new BaseMovieAdapter(getActivity().getSupportFragmentManager());
        vpMovie.setAdapter(baseMovieAdapter);
        btnHot.setOnClickListener(this);
        btnComing.setOnClickListener(this);
        vpMovie.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_hot:
                vpMovie.setCurrentItem(0);
                btnHot.setTextColor(Color.rgb(0,119,217));
                btnComing.setTextColor(Color.rgb(0,0,0));
                break;
            case R.id.btn_coming:
                vpMovie.setCurrentItem(1);
                btnHot.setTextColor(Color.rgb(0,0,0));
                btnComing.setTextColor(Color.rgb(0,119,217));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            btnHot.setTextColor(Color.rgb(0,119,217));
            btnComing.setTextColor(Color.rgb(0,0,0));
        }
        else if(position==1) {

            btnHot.setTextColor(Color.rgb(0,0,0));
            btnComing.setTextColor(Color.rgb(0,119,217));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
