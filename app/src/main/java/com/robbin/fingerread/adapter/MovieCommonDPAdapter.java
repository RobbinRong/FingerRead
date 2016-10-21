package com.robbin.fingerread.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.MovieCommonsDP;
import com.robbin.fingerread.bean.MovieCommonsZY;
import com.robbin.fingerread.constant.Settings;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/21.
 */
public class MovieCommonDPAdapter   extends RecyclerView.Adapter<MovieCommonDPAdapter.ViewHoler>{
    private MovieCommonsDP commonsDP;
    private Context context;

    public MovieCommonDPAdapter(MovieCommonsDP commonsDP, Context context) {
        this.commonsDP = commonsDP;
        this.context = context;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_common_dp,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        MovieCommonsDP.Cmts data = commonsDP.cmts.get(position);
        if(Settings.isNightMode){
            holder.cvItem.setCardBackgroundColor(Color.rgb(100,100,100));
        }
        Glide.with(context).load(data.avatarurl).placeholder(R.drawable.ic_placeholder).into(holder.imUserLogo);
        holder.tvName.setText(data.nickName);
        holder.tvCity.setText(data.cityName);
        holder.tvApprove.setText(data.approve+"");
        holder.tvScore.setText(data.score*2+"");
        holder.tvContent.setText(data.content);
        holder.tvDate.setText(data.startTime);
    }

    @Override
    public int getItemCount() {
        if(null!=commonsDP&&commonsDP.cmts!=null&&!commonsDP.cmts.isEmpty()){
            return  commonsDP.cmts.size();
        }
        return 0;

    }
    public void change(MovieCommonsDP commonsDP){
        this.commonsDP=commonsDP;
        this.notifyDataSetChanged();
    }
    public void append(MovieCommonsDP commonsDP){
        this.commonsDP.cmts.addAll(commonsDP.cmts);
        this.notifyDataSetChanged();
    }


    class ViewHoler extends RecyclerView.ViewHolder{
        @Bind(R.id.cv_item)
        CardView cvItem;
        @Bind(R.id.iv_dp)
        com.makeramen.roundedimageview.RoundedImageView imUserLogo;
        @Bind(R.id.tv_name_dp)
        TextView tvName;
        @Bind(R.id.tv_city)
        TextView tvCity;
        @Bind(R.id.tv_score_dp)
        TextView tvScore;
        @Bind(R.id.tv_content_dp)
        TextView tvContent;
        @Bind(R.id.tv_date_dp)
        TextView tvDate;
        @Bind(R.id.tv_common_approve_dp)
        TextView tvApprove;

        public ViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
