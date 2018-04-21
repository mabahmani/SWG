package com.mab.swg;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SpeedMatchPreferenceManager {

    private static SpeedMatchPreferenceManager instance = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    private SpeedMatchPreferenceManager(Context context){
        sharedPreferences = context.getSharedPreferences("sp_preferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SpeedMatchPreferenceManager getInstance(Context context){

        if(instance == null)
            instance = new SpeedMatchPreferenceManager(context);

        return instance;
    }

    public void putRankList(SpeedMatchRankList speedMatchRankList){
        Gson gson = new Gson();
        String rankListJson = gson.toJson(speedMatchRankList,SpeedMatchRankList.class);
        editor.putString("rank_list",rankListJson);
        editor.apply();
    }

    public SpeedMatchRankList getRankList(){
        String rankListJson = sharedPreferences.getString("rank_list",null);

        if (rankListJson == null)
            return new SpeedMatchRankList();
        Gson gson = new Gson();

        return gson.fromJson(rankListJson,SpeedMatchRankList.class);
    }
}
