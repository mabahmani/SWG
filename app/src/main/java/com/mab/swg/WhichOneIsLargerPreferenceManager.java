package com.mab.swg;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class WhichOneIsLargerPreferenceManager {

    private static WhichOneIsLargerPreferenceManager instance = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    private WhichOneIsLargerPreferenceManager(Context context){
        sharedPreferences = context.getSharedPreferences("gl_preferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static WhichOneIsLargerPreferenceManager getInstance(Context context){

        if(instance == null)
            instance = new WhichOneIsLargerPreferenceManager(context);

        return instance;
    }

    public void putRankList(WhichOneIsLargerRankList whichOneIsLargerRankList){
        Gson gson = new Gson();
        String rankListJson = gson.toJson(whichOneIsLargerRankList,WhichOneIsLargerRankList.class);
        editor.putString("rank_list",rankListJson);
        editor.apply();
    }

    public WhichOneIsLargerRankList getRankList(){
        String rankListJson = sharedPreferences.getString("rank_list",null);

        if (rankListJson == null)
            return new WhichOneIsLargerRankList();
        Gson gson = new Gson();

        return gson.fromJson(rankListJson,WhichOneIsLargerRankList.class);
    }
}
