package com.mab.swg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WhichOneIsLargerActivity extends AppCompatActivity {
    private ImageView startGame;
    private ImageView rankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_which_one_is_larger);

        findViews();
        onClicks();
    }

    private void onClicks(){
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNameDialog getNameDialog = new GetNameDialog(
                        WhichOneIsLargerActivity.this,
                        new OnNameSelectedListener() {
                            @Override
                            public void OnNameSelected(String playerName) {
                                WhichOneIsLargerStartGameFragment whichOneIsLargerStartGameFragment = new WhichOneIsLargerStartGameFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("player_name",playerName);
                                whichOneIsLargerStartGameFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction()
                                        .add(R.id.gl_play_fragment,whichOneIsLargerStartGameFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                );
                getNameDialog.show();
            }
        });

        rankList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WhichOneIsLargerRankListFragment whichOneIsLargerRankListFragment = new WhichOneIsLargerRankListFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.gl_rank_list_fragment,whichOneIsLargerRankListFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void findViews(){
        startGame = findViewById(R.id.gl_play_icon);
        rankList = findViewById(R.id.gl_rank_list_icon);
    }
}
