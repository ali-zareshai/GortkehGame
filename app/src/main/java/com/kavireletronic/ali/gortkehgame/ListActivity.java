package com.kavireletronic.ali.gortkehgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Adapter.ListAdapter;
import Model.LevelModel;
import Utils.LevelGame;

public class ListActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LayoutAnimationController animationController;
    private SharedPreferences SP;
    private boolean doubleBackToExitPressedOnce = false;
    private Toolbar jame_toolbar;
    private ImageView jam1,jam2,jam3,jam4,jam5,jam6;
    private static SharedPreferences.Editor editor;
    private String level;
    private TextView level_name;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.bye),MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS);
            mdToast.show();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.twice_click),MDToast.LENGTH_SHORT,MDToast.TYPE_INFO);
        mdToast.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        jame_toolbar=(Toolbar)findViewById(R.id.toolbar_jam);
        jam1=(ImageView)findViewById(R.id.jam1);
        jam2=(ImageView)findViewById(R.id.jam2);
        jam3=(ImageView)findViewById(R.id.jam3);
        jam4=(ImageView)findViewById(R.id.jam4);
        jam5=(ImageView)findViewById(R.id.jam5);
        jam6=(ImageView)findViewById(R.id.jam6);




        level_name=(TextView)findViewById(R.id.name_level);

        level=getIntent().getExtras().getString("level").toString();
        /// set file level ///
        level_name.setText(LevelGame.nameLevel(level,getApplicationContext()));


        SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = SP.edit();

        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        animationController= AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_slide_right);
        recyclerView.setLayoutAnimation(animationController);
        recyclerView.scheduleLayoutAnimation();

        ListAdapter listAdapter= null;
        try {
            listAdapter = new ListAdapter(getLevels(),level,getApplicationContext());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(listAdapter);
        recyclerView.scrollToPosition(SP.getInt(LevelGame.getLevelSp(level),1)-1);
        setJames();
        /// show help in first show avtivity in every game
        if (SP.getBoolean("help_show_marahreh",true)){
            showSnakeHelp();
            editor.putBoolean("help_show_marahreh",false);
            editor.apply();
        }


    }

    private void showSnakeHelp(){
        Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(), getString(R.string.help_list), 3000);
        View viewSnake = snackbar.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)viewSnake.getLayoutParams();
        params.gravity = Gravity.TOP;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            viewSnake.setBackgroundColor(getColor(R.color.backsnake));
        }else {
            viewSnake.setBackgroundColor(getResources().getColor(R.color.backsnake));
        }
        params.setMargins(params.leftMargin,120,params.rightMargin,params.bottomMargin);
        viewSnake.setLayoutParams(params);

        TextView tv = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Vazir.ttf");
        tv.setTypeface(font);
        tv.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        snackbar.show();
    }

    private void setJames() {
        if (SP.getInt("jams",0)!=0){
            jame_toolbar.setVisibility(View.VISIBLE);
            switch (SP.getInt("jams",0)){
                case 1:
                    jam1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    jam1.setVisibility(View.VISIBLE);
                    jam2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    jam1.setVisibility(View.VISIBLE);
                    jam2.setVisibility(View.VISIBLE);
                    jam3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    jam1.setVisibility(View.VISIBLE);
                    jam2.setVisibility(View.VISIBLE);
                    jam3.setVisibility(View.VISIBLE);
                    jam4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    jam1.setVisibility(View.VISIBLE);
                    jam2.setVisibility(View.VISIBLE);
                    jam3.setVisibility(View.VISIBLE);
                    jam4.setVisibility(View.VISIBLE);
                    jam5.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    jam1.setVisibility(View.VISIBLE);
                    jam2.setVisibility(View.VISIBLE);
                    jam3.setVisibility(View.VISIBLE);
                    jam4.setVisibility(View.VISIBLE);
                    jam5.setVisibility(View.VISIBLE);
                    jam6.setVisibility(View.VISIBLE);
                    break;
            }
        }

        if (SP.getBoolean("new_jam",false)){
            editor.putBoolean("new_jam",false);
            editor.apply();
            showWinnerDialog();
        }
    }

    private String getFile(String level){
        switch (level){
            case "1":
                return "asan.json";
            case "2":
                return "medium.json";
            case "3":
                return "hard.json";
            case "4":
                return "pro.json";
        }
        return "";
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
            levelModel.setInterval(obj.getString("interval"));

            levelModelList.add(levelModel);

        }
        return levelModelList;
    }


    private String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(getFile(level));

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


    private void showWinnerDialog(){
        new FancyGifDialog.Builder(this)
                .setTitle(getString(R.string.winner_title))
                .setMessage(getString(R.string.winner_body))
                .setNegativeBtnText(getString(R.string.exit))
                .setPositiveBtnBackground("#FF4081")
                .setPositiveBtnText(getString(R.string.continue_))
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.winner)   //Pass your Gif here
                .isCancellable(true)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
//                        Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        finish();
                    }
                })
                .build();
    }
}
