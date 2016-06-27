package com.dayeong.gdgssu_charge_your_life;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class SplashScreenActivity extends Activity {

    private Button bt1;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        intent = new Intent(SplashScreenActivity.this, MapActivity.class);

        new BluetoothControl(this, intent);


        // 생성한 비디오뷰를 선언
        final VideoView videoView = (VideoView) findViewById(R.id.videoView1);

        // 안드로이드 res폴더에 raw폴더를 생성 후 재생할 동영상파일을 넣습니다.
        final Uri video = Uri.parse("android.resource://" + getPackageName()
                + "/raw/mp4test2");

        //비디오뷰에 재생할 동영상주소를 연결
        videoView.setVideoURI(video);

        //비디오뷰를 포커스하도록 지정
        videoView.requestFocus();

        //동영상 재생
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                videoView.start();
            }
        });


        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(intent);
                finish();
                return true;
            }
        });




    }

}
