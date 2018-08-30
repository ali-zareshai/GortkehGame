package com.kavireletronic.ali.gortkehgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Adapter.ListAdapter;
import Model.LevelModel;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LayoutAnimationController animationController;
    private SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);

        SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        animationController= AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_slide_right);
        recyclerView.setLayoutAnimation(animationController);
        recyclerView.scheduleLayoutAnimation();

        ListAdapter listAdapter= null;
        try {
            listAdapter = new ListAdapter(getLevels(),getApplicationContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(listAdapter);
        recyclerView.scrollToPosition(SP.getInt("level_user",1)-1);

    }

    private List<LevelModel> getLevels() throws JSONException {
        List<LevelModel> levelModelList=new ArrayList<>();
        JSONArray jsonArray=new JSONArray(loadJSONFromAsset(getApplicationContext()));
        JSONObject obj;
        for (int i=0;i<jsonArray.length();i++) {
            obj=jsonArray.getJSONObject(i);
            LevelModel levelModel = new LevelModel();
            levelModel.setName(obj.getString("name"));
            levelModel.setAhdad(obj.getString("ahdad"));
            levelModel.setLevel(obj.getString("level"));
            levelModel.setArgame(obj.getString("argam"));

            levelModelList.add(levelModel);

        }
        return levelModelList;
    }


    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("level.txt");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
