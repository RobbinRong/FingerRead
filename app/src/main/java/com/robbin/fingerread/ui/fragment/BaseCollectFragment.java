package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.robbin.fingerread.R;
import com.robbin.fingerread.constant.CS;

/**
 * Created by OneAPM on 2016/9/12.
 */
public class BaseCollectFragment extends TopNavigationFragment {

    private com.robbin.fingerread.adapter.PagerAdapter pagerAdapter;

    @Override
    public com.robbin.fingerread.adapter.PagerAdapter initPageAdapter() {
        pagerAdapter = new com.robbin.fingerread.adapter.PagerAdapter(getChildFragmentManager(), CS.collect_title) {
            @Override
            public Fragment getItem(int position) {
                BaseCollectItemFragment fragment = null;
                if(position==0){
                    fragment = new CollectDailyFragment();
                }
                else if(position==1){
                    fragment = new CollectScienceFragment();
                }
                else if(position==2){
                    fragment = new CollectWechatFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putInt(getString(R.string.id_pos),position);
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
