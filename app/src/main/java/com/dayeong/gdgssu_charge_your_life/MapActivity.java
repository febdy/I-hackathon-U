package com.dayeong.gdgssu_charge_your_life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MapActivity extends Activity {

    private ImageView mapView;
    private Button btDongzak;
    private Button btAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = (ImageView) findViewById(R.id.mapView);
        btDongzak = (Button) findViewById(R.id.btDongzak);
        btAll = (Button) findViewById(R.id.btAll);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.gc();

    }
    public void onClickDongzak(View view) {
        Intent toMain = new Intent(MapActivity.this, MainActivity.class);
        startActivity(toMain);
        this.onDestroy();
    }

}
