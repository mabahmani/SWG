package com.mab.swg;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SpeedMatchRankListFragment extends Fragment{
    private TextView bestUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speed_match_rank_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        bestUser.setText(SpeedMatchPreferenceManager.getInstance(getActivity()).getBestUser()+"");
    }

    void findViews(View view){
        bestUser = view.findViewById(R.id.best_user_tv);
    }
}
