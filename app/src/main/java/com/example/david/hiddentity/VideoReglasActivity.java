package com.example.david.hiddentity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.VideoView;

public class VideoReglasActivity extends AppCompatActivity {

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_reglas);

        final ImageButton play=findViewById(R.id.play);
        final ImageButton stop=findViewById(R.id.stop);
        final ImageButton pause=findViewById(R.id.pause);
        final ImageButton avanzar=findViewById(R.id.avanzar);
        final ImageButton retroceder=findViewById(R.id.retroceder);
        final VideoView mp =findViewById(R.id.video);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final SeekBar volumen=findViewById(R.id.volumen);

        volumen.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        //mp.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");

        Intent intent = this.getIntent();
        String nombreVideo = intent.getStringExtra("video");
        Uri video = null;
        if(nombreVideo.equalsIgnoreCase("crear")){
             video = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.creacion_mazos);
        }else if(nombreVideo.equalsIgnoreCase("parametros")){
             video = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.parametros_video);
        }else if(nombreVideo.equalsIgnoreCase("jugar")){
             video = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.explicacion_partida);
        }else{
            Intent intent2 = new Intent(VideoReglasActivity.this,ReglasActivity.class);
            startActivity(intent2);
        }
       // Uri path = Uri.parse(String.valueOf(R.raw.creacion_mazos));
        mp.setVideoURI(video);

        mp.start();


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                mp.seekTo(0);
            }
        });

        avanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.seekTo(mp.getCurrentPosition()+5000);
            }
        });
        retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.seekTo(mp.getCurrentPosition()-5000);
            }
        });


        volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //mp.setVolume(seekBar.getProgress()/100.0f,seekBar.getProgress()/100.0f);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,seekBar.getProgress(),0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
