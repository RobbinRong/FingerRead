package com.robbin.fingerread.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.robbin.fingerread.adapter.ScienceAdapter;
import com.robbin.fingerread.adapter.WechatAdapter;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.bean.WechatArticalBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
public class CollectWechatFragment extends  BaseCollectItemFragment  {
    @Override
    public RecyclerView.Adapter initAdapter() {
        WechatArticalBean wechatArticalBean = dao.getAllWechat();
        return  new WechatAdapter(wechatArticalBean,getActivity());
    }
}
