package com.kavireletronic.ali.gortkehgame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.valdesekamdem.library.mdtoast.MDToast;
import com.viethoa.DialogUtils;

import Utils.LevelGame;

public class SelectLevelActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView asan_c,med_c,hard_c,pro_c;
    private ImageView img1,img2,img3,img4;
    private RelativeLayout refreshgmage;
    private Dialog myDialog;
    private SharedPreferences SP;
    private static SharedPreferences.Editor editor;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = SP.edit();

        editor.putBoolean("help_show_marahreh",true);
        editor.apply();

        asan_c=(CardView)findViewById(R.id.card_view_asan);
        med_c=(CardView)findViewById(R.id.card_view_medium);
        hard_c=(CardView)findViewById(R.id.card_view_hard);
        pro_c=(CardView)findViewById(R.id.card_view_pro);

        img1=(ImageView)findViewById(R.id.thumbnail1);
        img2=(ImageView)findViewById(R.id.thumbnail2);
        img3=(ImageView)findViewById(R.id.thumbnail3);
        img4=(ImageView)findViewById(R.id.thumbnail4);

        refreshgmage=(RelativeLayout)findViewById(R.id.refreshgameR);

        refreshgmage.setOnClickListener(this);

        asan_c.setOnClickListener(this);
        med_c.setOnClickListener(this);
        hard_c.setOnClickListener(this);
        pro_c.setOnClickListener(this);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        intent=new Intent(getApplicationContext(),ListActivity.class);
        if (view.getId()==asan_c.getId() || view.getId()==img1.getId()){
            intent.putExtra("level","1");
        }else if (view.getId()==med_c.getId() || view.getId()==img2.getId()){
            intent.putExtra("level","2");
        }else if (view.getId()==hard_c.getId() || view.getId()==img3.getId()){
            intent.putExtra("level","3");
        }else if (view.getId()==pro_c.getId() || view.getId()==img4.getId()){
            intent.putExtra("level","4");
        }else if (view.getId()==refreshgmage.getId()){
            refrehgGameM();
            return;
        }
        startActivity(intent);

    }

    private void refrehgGameM() {
        String title = getString(R.string.refresh_gmae);
        String message = getString(R.string.refresh_game_que);
        String negativeButton = getString(R.string.noo);
        String positiveButton = getString(R.string.baleh);
        myDialog = DialogUtils.createDialogMessage(this, title, message,
                negativeButton, positiveButton, false, new DialogUtils.DialogListener() {
                    @Override
                    public void onPositiveButton() {
                        editor.putInt(LevelGame.getLevelSp("1"),1);
                        editor.putInt(LevelGame.getLevelSp("2"),1);
                        editor.putInt(LevelGame.getLevelSp("3"),1);
                        editor.putInt(LevelGame.getLevelSp("4"),1);
                        editor.putInt("emtiaz",0);
                        editor.apply();
                        editor.putInt("stars_em",3);
                        editor.apply();
                        editor.putInt("jams",0);
                        editor.apply();
                        MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.refresh_success),MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS);
                        mdToast.show();
                    }

                    @Override
                    public void onNegativeButton() {
                        myDialog.cancel();
                    }
                });

        if (myDialog != null && !myDialog.isShowing()) {
            myDialog.show();
        }
    }
}
