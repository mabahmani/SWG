package com.mab.swg;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;

public class WhichOneIsLargerRankListFragment extends Fragment{
    private RecyclerView rankList;
    private UserAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_which_one_is_larger_rank_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        rankListInit();
    }

    private void rankListInit(){
        WhichOneIsLargerRankList whichOneIsLargerRankList = WhichOneIsLargerPreferenceManager.getInstance(getActivity()).getRankList();

        Comparator comparator = new Comparator<Users>() {
            @Override
            public int compare(Users x, Users y) {
                if(x.getScor() > y.getScor())
                    return -1;
                else if(x.getScor() < y.getScor())
                    return +1;
                else
                    return 0;
            }
        };

        Collections.sort(whichOneIsLargerRankList.getRankList(),comparator);

        userAdapter = new UserAdapter(whichOneIsLargerRankList.getRankList());
        rankList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rankList.setAdapter(userAdapter);

    }

    private void findViews(View view){
        rankList = view.findViewById(R.id.sp_rank_list);
    }
}
