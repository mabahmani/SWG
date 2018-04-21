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

    public void putBestUser(Users user){
        Gson gson = new Gson();
        String userJson = gson.toJson(user,Users.class);
        editor.putString("best_user",userJson);
        editor.apply();
    }

    public Users getBestUser(){
        String userJson = sharedPreferences.getString("best_user",null);
        if (userJson == null)
            return null;

        Gson gson = new Gson();

        return gson.fromJson(userJson,Users.class);

    }
}
