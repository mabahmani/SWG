package com.mab.swg;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class WhichOneIsLargerStartGameFragment extends Fragment {

    private TextView count;
    private TextView score_tv;
    private Button  leftButton;
    private Button  rightButton;
    private Button  equalButton;


    private int leftNumber;
    private int rightNumber;
    private int counter = 3;
    private int score = 0;

    private String playerName;

    private void readArguments(){
        playerName = getArguments().getString("player_name",null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        readArguments();
        return inflater.inflate(R.layout.fragment_which_one_is_larger_start_game,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        gameInit();
        onClicks();
    }

    private void gameInit(){
        leftButton.setVisibility(View.INVISIBLE);
        rightButton.setVisibility(View.INVISIBLE);
        equalButton.setVisibility(View.INVISIBLE);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        equalButton.setEnabled(false);
        leftNumber = randomGenerate();
        rightNumber = randomGenerate();

        leftButton.setText(String.valueOf(leftNumber));
        rightButton.setText(String.valueOf(rightNumber));
        counterAnimation();
    }

    private void gameStart(){
        leftButton.setVisibility(View.VISIBLE);
        rightButton.setVisibility(View.VISIBLE);
        equalButton.setVisibility(View.VISIBLE);
        buttonsAnimation();
        leftButton.setEnabled(true);
        rightButton.setEnabled(true);
        equalButton.setEnabled(true);
        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @SuppressLint("StringFormatInvalid")
            @Override
            public void onFinish() {
                leftButton.setVisibility(View.INVISIBLE);
                rightButton.setVisibility(View.INVISIBLE);
                equalButton.setVisibility(View.INVISIBLE);
                score_tv.setText(getString(R.string.your_score,score));
                updateRankList();
            }
        };
        countDownTimer.start();
    }

    private void updateRankList(){
        Users user = new Users();
        user.setName(playerName);
        user.setScor(score);

        WhichOneIsLargerRankList whichOneIsLargerRankList = WhichOneIsLargerPreferenceManager.getInstance(getActivity()).getRankList();
        whichOneIsLargerRankList.addUser(user);
        WhichOneIsLargerPreferenceManager.getInstance(getActivity()).putRankList(whichOneIsLargerRankList);
    }

    private void onClicks(){
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(leftNumber > rightNumber)
                    score++;
                leftNumber = randomGenerate();
                rightNumber = randomGenerate();
                leftButton.setText(String.valueOf(leftNumber));
                rightButton.setText(String.valueOf(rightNumber));
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rightNumber > leftNumber)
                    score++;
                leftNumber = randomGenerate();
                rightNumber = randomGenerate();
                leftButton.setText(String.valueOf(leftNumber));
                rightButton.setText(String.valueOf(rightNumber));
            }
        });
    }

    private int randomGenerate(){
        Random random = new Random();
        int x= random.nextInt() % 1000;
        if(x < 0){
            x*=-1;
        }
        return x;
    }

    private void buttonsAnimation(){
        ObjectAnimator  TranslationX1 = new ObjectAnimator().ofFloat(
                leftButton,
                "TranslationX",
                -Resources.getSystem().getDisplayMetrics().widthPixels,0
        );
        TranslationX1.setDuration(500);

        ObjectAnimator  TranslationX2 = new ObjectAnimator().ofFloat(
                rightButton,
                "TranslationX",
                Resources.getSystem().getDisplayMetrics().widthPixels,0
        );
        TranslationX2.setDuration(500);

        ObjectAnimator  TranslationY = new ObjectAnimator().ofFloat(
                equalButton,
                "TranslationY",
                Resources.getSystem().getDisplayMetrics().heightPixels,0
        );
        TranslationY.setDuration(500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(TranslationX1,TranslationX2,TranslationY);
        animatorSet.start();
    }
    private void counterAnimation(){
        ObjectAnimator scaleX = new ObjectAnimator().ofFloat(
                count,
                "scaleX",
                1,4
        );
        scaleX.setDuration(1000);

        ObjectAnimator scaleY = new ObjectAnimator().ofFloat(
                count,
                "scaleY",
                1,4
        );
        scaleY.setDuration(1000);

        ObjectAnimator alpha = new ObjectAnimator().ofFloat(
                count,
                "Alpha",
                1,0
        );
        alpha.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX,scaleY,alpha);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(counter > 0)
                    counterAnimation();
                else
                    gameStart();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                count.setText(String.valueOf(counter));
                counter--;
            }
        });
        animatorSet.start();
    }
    private void findViews(View view){
        leftButton = view.findViewById(R.id.left_btn);
        rightButton = view.findViewById(R.id.right_btn);
        equalButton = view.findViewById(R.id.equal_btn);
        count = view.findViewById(R.id.gl_counter);
        score_tv = view.findViewById(R.id.gl_score);
    }
}