package com.mab.swg;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SpeedMatchStartGameFragment extends Fragment {
    private ImageView shapes;
    private ImageView check;
    private Button nonOfThem;
    private Button oneOfThem;
    private Button bothOfTheme;
    private TextView count;
    private TextView score_tv;

    private int shapesId [] = {R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,
                               R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6,
                               R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,R.drawable.t5,R.drawable.t6 };

    private int shapeColorId [] = { 1,2,3,4,5,6,
                                    1,2,3,4,5,6,
                                    1,2,3,4,5,6};

    private int shapeSymbolId [] = {1,1,1,1,1,1,
                                    2,2,2,2,2,2,
                                    3,3,3,3,3,3};

    private int previousShape;
    private int currentShape;
    private int counter = 3;
    private int score = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speed_match_start_game,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        gameInit();
        onClicks();
    }

    private void gameInit(){
        nonOfThem.setEnabled(false);
        oneOfThem.setEnabled(false);
        bothOfTheme.setEnabled(false);
        previousShape = randomGenerate();
        shapes.setImageResource(shapesId[previousShape]);
        counterAnimation();
    }

    private void gameStart(){
        shapesAnimations();
        nonOfThem.setEnabled(true);
        oneOfThem.setEnabled(true);
        bothOfTheme.setEnabled(true);
        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @SuppressLint("StringFormatInvalid")
            @Override
            public void onFinish() {
                shapes.setVisibility(View.INVISIBLE);
                nonOfThem.setVisibility(View.INVISIBLE);
                oneOfThem.setVisibility(View.INVISIBLE);
                bothOfTheme.setVisibility(View.INVISIBLE);
                score_tv.setText(getString(R.string.your_score,score));
            }
        };
        countDownTimer.start();
    }

    private void onClicks(){

        nonOfThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shapeColorId[previousShape] != shapeColorId [currentShape] && shapeSymbolId[previousShape] != shapeSymbolId[currentShape]){
                    check.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    score++;
                }
                else{
                    check.setImageResource(R.drawable.ic_cancel_black_24dp);
                }
                checkAnimation();
                shapesAnimations();
            }
        });

        oneOfThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shapeColorId[previousShape] == shapeColorId [currentShape] || shapeSymbolId[previousShape] == shapeSymbolId[currentShape]){
                    check.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    score++;
                }
                else if(shapeColorId[previousShape] == shapeColorId [currentShape] && shapeSymbolId[previousShape] == shapeSymbolId[currentShape]){
                    check.setImageResource(R.drawable.ic_cancel_black_24dp);
                }
                else{
                    check.setImageResource(R.drawable.ic_cancel_black_24dp);
                }
                checkAnimation();
                shapesAnimations();
            }
        });

        bothOfTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shapeColorId [previousShape] == shapeColorId [currentShape] && shapeSymbolId [previousShape] == shapeSymbolId [currentShape]) {
                    check.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    score++;
                }
                else{
                    check.setImageResource(R.drawable.ic_cancel_black_24dp);
                }

                checkAnimation();
                shapesAnimations();
            }
        });

    }

    private int randomGenerate(){
        Random random = new Random();
        int x= random.nextInt() % 18;
        if(x < 0){
            x*=-1;
        }
        return x;
    }

    private void shapesAnimations(){
        ObjectAnimator centerToLeftTranslation = new ObjectAnimator().ofFloat(
            shapes,
            "TranslationX",
            0f,-Resources.getSystem().getDisplayMetrics().widthPixels
    );
        centerToLeftTranslation.setDuration(500);
        centerToLeftTranslation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                currentShape = randomGenerate();
                shapes.setImageResource(shapesId[currentShape]);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                previousShape = currentShape;
            }
        });

        centerToLeftTranslation.start();

        ObjectAnimator rightToCenterTranslation = new ObjectAnimator().ofFloat(
                shapes,
                "TranslationX",
                Resources.getSystem().getDisplayMetrics().widthPixels,0f
        );
        rightToCenterTranslation.setStartDelay(500);
        rightToCenterTranslation.setDuration(500);
        rightToCenterTranslation.start();
    }

    private void checkAnimation(){
        ObjectAnimator setAlpha = new ObjectAnimator().ofFloat(
                check,
                "Alpha",
                0f,1f,0f
        );
        setAlpha.setDuration(500);
        setAlpha.start();
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
        shapes = view.findViewById(R.id.sp_shapes_icon);
        check = view.findViewById(R.id.sp_check_icon);
        nonOfThem = view.findViewById(R.id.btn_non_of_theme);
        oneOfThem = view.findViewById(R.id.btn_one_of_them);
        bothOfTheme = view.findViewById(R.id.btn_both_of_them);
        count = view.findViewById(R.id.counter);
        score_tv = view.findViewById(R.id.score);
    }
}