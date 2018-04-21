package com.mab.swg;

import java.util.ArrayList;
import java.util.List;

public class SpeedMatchRankList {
    private List<Users> rankList;

    public SpeedMatchRankList(){
        this.rankList = new ArrayList<>();
    }

    public void addUser(Users user){
        this.rankList.add(user);
    }

    public List<Users> getRankList() {
        return rankList;
    }

    public void setRankList(List<Users> rankList) {
        this.rankList = rankList;
    }
}
