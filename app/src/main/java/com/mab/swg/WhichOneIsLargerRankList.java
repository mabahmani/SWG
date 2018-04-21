package com.mab.swg;

import java.util.ArrayList;
import java.util.List;

public class WhichOneIsLargerRankList {
    private List<Users> rankList;

    public WhichOneIsLargerRankList(){
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
