package com.mab.swg;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.sp_title);
        tv2 = findViewById(R.id.gl_title);

        frameLayout = findViewById(R.id.speed_match_start_fragment);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SpeedMatchActivity.class);
                startActivity(intent);
            }
        });

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/GILSANUB.TTF");
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
    }
}