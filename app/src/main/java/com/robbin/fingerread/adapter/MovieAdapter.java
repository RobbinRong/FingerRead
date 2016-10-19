package com.robbin.fingerread.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.MovieBean;
import com.robbin.fingerread.constant.Settings;
import com.robbin.fingerread.ui.activity.MovieDetailActivity;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHoler> {

    private MovieBean movieBean;
    private Context context;
    private int position;
    public static final String TAG="movieAdapter";
    public MovieAdapter(MovieBean movieBean, Context context) {
        this.movieBean = movieBean;
        this.context = context;
    }

    @Override
    public MovieViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new MovieViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHoler holder, int position) {
        if(Settings.isNightMode){
            holder.cvItem.setBackgroundColor(Color.rgb(100,100,100));
        }
        final MovieBean.Movie movie=movieBean.data.movies.get(position);
        Glide.with(context).load(movie.img).placeholder(R.drawable.ic_gaoyuanyuan).into(holder.ivImg);
        holder.tvNm.setText(movie.nm);
        boolean is3d = movie.ver.contains("3D");
        boolean is2d=movie.ver.contains("2D");
        boolean isimax=movie.imax;
        if(is3d&&isimax){
            holder.tvD3.setText("3D");
            holder.tvD3.setVisibility(View.VISIBLE);
            holder.tvImax.setVisibility(View.VISIBLE);
        }
        else if(is3d&&!isimax){
            holder.tvD3.setText("3D");
            holder.tvD3.setVisibility(View.VISIBLE);
        }
        else if(is2d&&isimax){
            holder.tvD3.setText("2D");
            holder.tvD3.setVisibility(View.VISIBLE);
            holder.tvImax.setVisibility(View.VISIBLE);
        }else {
            holder.tvD3.setVisibility(View.INVISIBLE);
            holder.tvImax.setVisibility(View.INVISIBLE);
        }
        holder.tvScm.setText(movie.scm);
        holder.tvShowInfo.setText(movie.showInfo);
        if(movie.sc==0.0){
            holder.llRating.setVisibility(View.INVISIBLE);
            holder.llWish.setVisibility(View.VISIBLE);
            holder.tvWish.setText(String.valueOf(movie.wish));
        }
        else {
            holder.llRating.setVisibility(View.VISIBLE);
            holder.llWish.setVisibility(View.INVISIBLE);
            holder.tvSc.setText(String.valueOf(movie.sc));
        }
        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: img");
            }
        });
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.start(context,movie.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.movieBean!=null&&this.movieBean.data!=null){
           return this.movieBean.data.movies.isEmpty()?0:this.movieBean.data.movies.size();
        }
        return 0;
    }

    public void change(MovieBean movieBean,int position) {
        this.position=position;
        this.movieBean=movieBean;
        this.notifyDataSetChanged();
    }

    public void append(MovieBean movieBean,int position) {
        this.position=position;
        this.movieBean.data.movies.addAll(movieBean.data.movies);
        this.notifyDataSetChanged();
    }
    class  MovieViewHoler extends RecyclerView.ViewHolder{

        @Bind(R.id.cv_item)
        CardView cvItem;
        @Bind(R.id.iv_img)
        ImageView ivImg;
        @Bind(R.id.tv_nm)
        TextView tvNm;
        @Bind(R.id.d3)
        TextView tvD3;
        @Bind(R.id.tv_imax)
        TextView tvImax;
        @Bind(R.id.tv_rating)
        TextView tvRating;
        @Bind(R.id.tv_showinfo)
        TextView tvShowInfo;
        @Bind(R.id.tv_scm)
        TextView tvScm;
        @Bind(R.id.tv_sc)
        TextView tvSc;
        @Bind(R.id.ll_rating)
        LinearLayout llRating;
        @Bind(R.id.ll_wish)
        LinearLayout llWish;
        @Bind(R.id.tv_wish)
        TextView tvWish;
        public MovieViewHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
