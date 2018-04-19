package com.mab.swg;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class SpeedMatchStartGameFragment extends Fragment {
    private ImageView shapes;
    private Button nonOfThem;
    private Button oneOfThem;
    private Button bothOfTheme;

    private int shapesId [] = {R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,
                               R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6,
                               R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,R.drawable.t5,R.drawable.t6 };

    private int shapeColorId [] = { 1,2,3,4,5,6,
                                    1,2,3,4,5,6,
                                    1,2,3,4,5,6};

    private int shapeSymbolId [] = {1,1,1,1,1,1,
                                    2,2,2,2,2,2,
                                    3,3,3,3,3,3};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speed_match_start_game,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        onClicks();
        Random random = new Random();
        int x= random.nextInt() % 18;
        if(x < 0){
            x*=-1;
        }
        shapes.setImageResource(shapesId[x]);
    }

    private void onClicks(){

        nonOfThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapesAnimations();
            }
        });

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
                Random random = new Random();
                int x= random.nextInt() % 18;
                if(x < 0){
                    x*=-1;
                }
                Log.d("Tag",x+"");
                shapes.setImageResource(shapesId[x]);
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

    private void findViews(View view){
        shapes = view.findViewById(R.id.sp_shapes_icon);
        nonOfThem = view.findViewById(R.id.btn_non_of_theme);
    }
}
