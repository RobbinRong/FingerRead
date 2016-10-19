package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.robbin.fingerread.R;

import butterknife.Bind;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;

public class MovieVDDetailActivity extends BaseActivity {

    public static final  String KEY_VDDETAIL_ACTIVITY="movievddetailactivity";
    private String url;
    /*@Bind(R.id.surfaceView1)
    SurfaceView surfaceView;
    @Bind(R.id.skbProgress)
    SeekBar skbProgress;
    private Player player;
    @Bind(R.id.iv_controller)
    ImageButton imControl;*/
    @Bind(R.id.vv)
    io.vov.vitamio.widget.VideoView vv;
    private boolean ispaly=true;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_vddetail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        url = getIntent().getStringExtra(KEY_VDDETAIL_ACTIVITY);
        /*setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        imControl.setOnClickListener(new ClickEvent());
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(surfaceView, skbProgress);
*/
        vv.setVideoPath(url); //设置播放路径
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });
// 设置video的控制器
        vv.setMediaController(new MediaController(this));
    }

    /*
    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            if (arg0 == btnPause) {
                player.pause();
            } else if (arg0 == btnPlayUrl) {
                player.playUrl(url);
            } else if (arg0 == btnStop) {
                player.stop();
            }

        }
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }*/
    public  static  void start(Context context,String url){
        Intent intent=new Intent(context,MovieVDDetailActivity.class);
        intent.putExtra("movievddetailactivity",url);
        context.startActivity(intent);
    }
}
