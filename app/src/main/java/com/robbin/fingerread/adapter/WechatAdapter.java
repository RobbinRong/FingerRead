package com.robbin.fingerread.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.bean.ScienceBean;
import com.robbin.fingerread.bean.WechatArticalBean;
import com.robbin.fingerread.constant.Settings;
import com.robbin.fingerread.ui.activity.ScienceDetailActivity;
import com.robbin.fingerread.ui.activity.WechatDetailActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/12.
 */
public class WechatAdapter extends  RecyclerView.Adapter<WechatAdapter.WechatHolder> {

    private Context context;
    private long lastPos = -1;
    private boolean isAnim = true;
    private WechatArticalBean wechatArticalBean;

    public WechatAdapter(WechatArticalBean wechatArticalBean, Context context) {
        this.wechatArticalBean = wechatArticalBean;
        this.context=context;
    }
    @Override
    public WechatAdapter.WechatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.wechat_item,parent,false);
        return new WechatHolder(view);
    }

    @Override
    public void onBindViewHolder(WechatAdapter.WechatHolder holder, int position) {
        if(Settings.isNightMode){
            holder.rl.setBackgroundColor(Color.rgb(100,100,100));
        }
        final WechatArticalBean.Content content=wechatArticalBean.showapi_res_body.pagebean.contentlist.get(position);
        holder.userName.setText(content.userName);
        holder.title.setText(content.title);
        holder.date.setText(content.date);
        Glide.with(context).load(content.userLogo).placeholder(R.drawable.ic_placeholder).into(holder.userLogo);
        Glide.with(context).load(content.contentImg).placeholder(R.drawable.ic_placeholder).into(holder.contentImg);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WechatDetailActivity.start(context,content);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(wechatArticalBean!=null&&wechatArticalBean.showapi_res_body!=null&&wechatArticalBean.showapi_res_body.pagebean!=null){
            return wechatArticalBean.showapi_res_body.pagebean.contentlist.isEmpty()?0:wechatArticalBean.showapi_res_body.pagebean.contentlist.size();
        }
        return 0;
    }

    public void change(WechatArticalBean wechatArticalBean) {
        this.wechatArticalBean=wechatArticalBean;
        this.notifyDataSetChanged();
    }

    class WechatHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.userLogo)
        ImageView userLogo;
        @Bind(R.id.contentImg)
        ImageView contentImg;
        @Bind(R.id.userName)
        TextView userName;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.wechat_bg)
        RelativeLayout rl;
        public WechatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
