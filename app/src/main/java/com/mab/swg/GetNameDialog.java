package com.mab.swg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetNameDialog extends Dialog{
    private Button startGame;
    private EditText playerName;
    private OnNameSelectedListener listener;

    public GetNameDialog(@NonNull Context context,OnNameSelectedListener listener) {
        super(context);

        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_get_name);
        findViews();
        setOnClicks();

        getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.MATCH_PARENT);
    }

    private void setOnClicks(){
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNameSelected(playerName.getText().toString());
                dismiss();
            }
        });
    }

    private void findViews(){
        startGame = findViewById(R.id.start_game);
        playerName = findViewById(R.id.your_name_et);
    }
}
