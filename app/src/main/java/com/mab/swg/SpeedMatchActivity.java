package com.mab.swg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SpeedMatchActivity extends AppCompatActivity {
    ImageView startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_match);

        findViews();
        OnClicks();
    }

    private void OnClicks(){
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNameDialog getNameDialog = new GetNameDialog(
                        SpeedMatchActivity.this,
                        new OnNameSelectedListener() {
                            @Override
                            public void OnNameSelected(String playerName) {
                                Log.d("Tag",playerName);

                                SpeedMatchStartGameFragment speedMatchStartGameFragment = new SpeedMatchStartGameFragment();
                                getFragmentManager().beginTransaction()
                                        .add(R.id.sp_play_fragment,speedMatchStartGameFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                );
                getNameDialog.show();

            }
        });
    }

    private void findViews(){

        startGame = findViewById(R.id.sp_play_icon);
    }
}
