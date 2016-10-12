package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.PagerAdapter;
import com.robbin.fingerread.bean.WechatArticalCategoryBean;
import com.robbin.fingerread.constant.WechatApi;
import com.robbin.fingerread.network.manager.RetrofitManager;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by OneAPM on 2016/8/23.
 */
public class BaseWechatFragment extends TopNavigationFragment {
    private PagerAdapter pagerAdapter;

    @Override
    public PagerAdapter initPageAdapter() {
        //以下代码是异步执行的，会导致adapter都已经返回了空了，还没新建adapter对象
      /*  RetrofitManager.builderWechat().getArticalCategory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<WechatArticalCategoryBean>() {
            @Override
            public void call(WechatArticalCategoryBean articalCategoryBean) {
                Log.e(TAG, "call: "+articalCategoryBean.toString());
                final ArrayList<WechatArticalCategoryBean.Type> types= articalCategoryBean.showapi_res_body.typeList;
                String [] category=new String[types.size()];
                for(int i=0;i<category.length;i++){
                    category[i]=types.get(i).name;
                }
                pagerAdapter = new PagerAdapter(getChildFragmentManager(),category) {
                    @Override
                    public Fragment getItem(int position) {
                        WechatFragment fragment = new WechatFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt(getString(R.string.id_pos), position);
                        bundle.putSerializable(getString(R.string.id_category), types.get(position).id);
                        fragment.setArguments(bundle);
                        return fragment;
                    }
                };
                Log.e(TAG, "call: "+pagerAdapter.getCount());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                String[] artical_category = WechatApi.artical_category;
                pagerAdapter = new PagerAdapter(getChildFragmentManager(), artical_category) {
                    @Override
                    public Fragment getItem(int position) {
                        WechatFragment fragment = new WechatFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt(getString(R.string.id_pos), position);
                        bundle.putSerializable(getString(R.string.id_category), WechatApi.category_id[position]);
                        fragment.setArguments(bundle);
                        return fragment;
                    }
                };
            }
        });*/
        pagerAdapter = new PagerAdapter(getChildFragmentManager(),WechatApi.artical_category) {
            @Override
            public Fragment getItem(int position) {
                WechatFragment fragment = new WechatFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(getString(R.string.id_pos), position);
                bundle.putSerializable(getString(R.string.id_category), WechatApi.category_id[position]);
                fragment.setArguments(bundle);
                return fragment;
            }
        };
        return pagerAdapter;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(getChildFragmentManager().getFragments()!=null){
            getChildFragmentManager().getFragments().clear();
        }
    }
}
