package com.kavireletronic.ali.gortkehgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Vibrator;

import com.github.anastr.flattimelib.CountDownTimerView;
import com.github.anastr.flattimelib.intf.OnTimeFinish;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Utils.FormatHelper;
import Utils.LevelGame;
import Utils.RandomNum;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;


import static maes.tech.intentanim.CustomIntent.customType;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView,javabTahih,mar_txt,emtiaz_txt;
    private List<String> ahdad=new ArrayList<>();
    private Timer timer;
    private TimerTask timerTask;
    private Button start,ok_javab,new_game;
    private Handler mHandler;
    private CardView javab;
    private EditText javabEdit;
    private android.view.animation.Animation animFaz1, animFaz2;
    private int count=0;
    private String getJavabFinal;
    private String level_,mar_,ahdad_,argame_,interval_,level_game;
    private SharedPreferences SP;
    private static SharedPreferences.Editor editor;
    private Vibrator vibrator;
    private boolean doubleBackToExitPressedOnce = false;
    private int star;
    private ImageView star2,star3;
    private static final String SHOWCASE_ID = "sequence example";
    private FancyShowCaseQueue fancyShowCaseQueue;
    private CountDownTimerView countDownTimerView;
    private FloatingActionButton floatingActionButton;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            startListActivity();
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

    private void startListActivity(){
        Intent intent=new Intent(getApplicationContext(),ListActivity.class);
        intent.putExtra("level",level_game);
        startActivity(intent);
        customType(GameActivity.this,"fadein-to-fadeout");
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textView=(TextView) findViewById(R.id.numberimgage);
        start=(Button)findViewById(R.id.btn_spotify);
        javab=(CardView)findViewById(R.id.javab_card);
        javabEdit=(EditText)findViewById(R.id.javabedit);
        ok_javab=(Button)findViewById(R.id.btn_ok);
        new_game=(Button)findViewById(R.id.btn_new);
        javabTahih=(TextView)findViewById(R.id.javatahiehtxt);
        mar_txt=(TextView) findViewById(R.id.mar_txt);
        emtiaz_txt=(TextView)findViewById(R.id.emtiaz_txt);
        star2=(ImageView)findViewById(R.id.star2);
        star3=(ImageView)findViewById(R.id.star3);
        countDownTimerView=(CountDownTimerView)findViewById(R.id.mCountDownTimer);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton) ;

        SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = SP.edit();
//        showShowCast();
        star=SP.getInt("stars",3);


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        /// get intent
        level_=getIntent().getExtras().getString("level").toString();
        mar_=getIntent().getExtras().getString("mar").toString();
        ahdad_=getIntent().getExtras().getString("ahdad").toString();
        argame_=getIntent().getExtras().getString("argam").toString();
        interval_=getIntent().getExtras().getString("interval").toString();
        level_game=getIntent().getExtras().getString("level_game").toString();

        mar_txt.setText(FormatHelper.toPersianNumber( "مرحله: "+mar_));
        emtiaz_txt.setText(FormatHelper.toPersianNumber("امتیاز: "+String.valueOf(SP.getInt("emtiaz",0))));


        start.setOnClickListener(this);
        ok_javab.setOnClickListener(this);
        new_game.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);

        timer=new Timer();
        mHandler = new Handler();
        showShowCast();

//        javabEdit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                checkOnlineJavab(javabEdit.getText().toString());
//                Log.e("online:","checkkkk");
////                javabEdit.setText(FormatHelper.toPersianNumber(FormatHelper.toEngNumber(javabEdit.getText().toString())));
//            }
//        });

    }

    private void checkOnlineJavab(String s) {

        if (!s.equals("") && s.equals(getJavabFinal)){
            javabIsTrue();
            Log.e("string",s);
            Log.e("online","javab is true");
        }
    }

    private void startCounter(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                countDownTimerView.start(5000);
            }
        });
        countDownTimerView.setOnTimeFinish(new OnTimeFinish() {
            @Override
            public void onFinish() {
                startnew();
            }
        });
    }
    private void stopCounter(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                countDownTimerView.success();
            }
        });

    }

    private void showShowCast() {
        if (SP.getBoolean("first_run",true)){
            editor.putBoolean("first_run",false);
            editor.apply();
            ///______________________________________________________
            showcast();
        }
    }

    private void showcast() {
        final FancyShowCaseView fancyShowCaseView1 = new FancyShowCaseView.Builder(this)
                .title(getString(R.string.help_mar))
                .focusOn(mar_txt)
                .customView(R.layout.layout_help_mar, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {
                        view.findViewById(R.id.btn_action_1).setOnClickListener(mClickListener);
                    }
                })
                .closeOnTouch(false)
                .build();

        final FancyShowCaseView fancyShowCaseView2 = new FancyShowCaseView.Builder(this)
                .title(getString(R.string.help_emtiaz))
                .focusOn(emtiaz_txt)
                .customView(R.layout.layout_help_emtiaz, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {
                        view.findViewById(R.id.btn_action_1).setOnClickListener(mClickListener);
                    }
                })
                .closeOnTouch(false)
                .build();

        final FancyShowCaseView fancyShowCaseView3 = new FancyShowCaseView.Builder(this)
                .title(getString(R.string.help_start))
                .focusOn(start)
                .customView(R.layout.layout_help_start, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {
                        view.findViewById(R.id.btn_action_1).setOnClickListener(mClickListener);
                    }
                })
                .closeOnTouch(false)
                .build();

        final FancyShowCaseView fancyShowCaseView4 = new FancyShowCaseView.Builder(this)
                .title(getString(R.string.help_total))
                .focusOn(textView)
                .customView(R.layout.layout_help_total, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {
                        view.findViewById(R.id.btn_action_1).setOnClickListener(mClickListener);
                    }
                })
                .closeOnTouch(false)
                .build();

        fancyShowCaseQueue = new FancyShowCaseQueue()
                .add(fancyShowCaseView1)
                .add(fancyShowCaseView2)
                .add(fancyShowCaseView3)
                .add(fancyShowCaseView4);

        fancyShowCaseQueue.show();
    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("", "onClick: ");
            fancyShowCaseQueue.getCurrent().hide();
        }
    };


    private void setJavabTahih(String str){
        javabTahih.setText(FormatHelper.toPersianNumber("جواب صحیح: "+str));
    }

    private void setNumberToImage() {
        textView.setTextSize(100);
        ahdad= RandomNum.getInit(Integer.parseInt(argame_),Integer.parseInt(ahdad_),Integer.parseInt(level_));
        getJavabFinal=String.valueOf(RandomNum.getJavab());
        Log.e("javab >>> ",getJavabFinal);
        setJavabTahih(getJavabFinal);
        loop();

    }

    private void setTextViewn(String n){
        textView.setText(FormatHelper.toPersianNumber(n));
    }
    private void loop(){
        timerTask=new TimerTask() {
            @Override
            public void run() {
                if (ahdad.size()<=count){
                    timerTask.cancel();
                    timer.cancel();
                    timer.purge();
                    getJavab();
                    startCounter();
                }else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setTextViewn("");
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    setTextViewn(ahdad.get(count));
                                    count++;
                                }
                            }, 250);

                        }
                    });


                }

            }
        };
        timer.schedule(timerTask,2000,Long.parseLong(interval_)+250);
    }

    private void getJavab() {
        animFaz1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.exit);
        mHandler.post(new Runnable() {
                @Override
                public void run() {
                    textView.startAnimation(animFaz1);
                    textView.setVisibility(View.INVISIBLE);
                }
        });




        animFaz2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                javab.startAnimation(animFaz2);
                javabEdit.requestFocus();
            }
        });
        /// show keybord in fouse edittext
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(javabEdit, InputMethodManager.SHOW_IMPLICIT);


    }


    @Override
    public void onClick(View view) {
        if (view.getId()==start.getId()){
            start.setVisibility(View.GONE);
            textView.setTextSize(30);
            textView.setText(FormatHelper.toPersianNumber(getString(R.string.amade)));
            setNumberToImage();
            floatingActionButton.setVisibility(View.GONE);

        }else if (view.getId()==new_game.getId()){
            stopCounter();
            startnew();
        }else if (view.getId()==ok_javab.getId()){
            checkJavab();

        }else if (view.getId()==floatingActionButton.getId()){
            showcast();
        }
    }

    private void checkJavab() {
        String userJavab=javabEdit.getText().toString().trim();

        if (userJavab.equals("")){
            MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.insert_ahdad),MDToast.LENGTH_LONG,MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }
        stopCounter();
        userJavab=FormatHelper.toEngNumber(userJavab);
        ok_javab.setEnabled(false);
        if (userJavab.equalsIgnoreCase(getJavabFinal)){
            javabIsTrue();
        }else {
            javabIsFalse();
        }
    }

    private void javabIsFalse() {
        MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.badjavab),MDToast.LENGTH_LONG,MDToast.TYPE_ERROR);
        mdToast.show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(400, VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            //deprecated in API 26
            vibrator.vibrate(400);
        }
        javabTahih.setVisibility(View.VISIBLE);
        updateEmtiaz(false);

    }

    private void setShowStar(int star) {
        switch (star){
            case 3:
                break;
            case 2:
                star3.setVisibility(View.GONE);
                break;
            case 1:
                star2.setVisibility(View.GONE);
                break;
            case 0:
                resetGame();
                break;

        }
    }

    private void resetGame() {
        editor.putInt(LevelGame.getLevelSp(level_game),1);
        editor.putInt("emtiaz",0);
        editor.putInt("stars",3);
        editor.putInt("jams",0);
        editor.apply();
        MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.gameover),MDToast.LENGTH_LONG,MDToast.TYPE_WARNING);
        mdToast.show();
        startListActivity();

    }

    private void updateEmtiaz(Boolean status) {
        if (status){
            if (SP.getInt(LevelGame.getLevelSp(level_game),1)==Integer.parseInt(mar_)){
                int level=SP.getInt(LevelGame.getLevelSp(level_game),1);
                level++;
                editor.putInt(LevelGame.getLevelSp(level_game),level);
                int emtiaz=SP.getInt("emtiaz",0);
                emtiaz=emtiaz+LevelGame.getEmtiazLevel(level_game);
                editor.putInt("stars",3);
                editor.putInt("emtiaz",emtiaz);
                editor.apply();
                chechWinner(emtiaz);
            }
        }else {
            if (SP.getInt(LevelGame.getLevelSp(level_game),1)==Integer.parseInt(mar_)){
                int emtiaz=SP.getInt("emtiaz",0);
                emtiaz=emtiaz-5;
                editor.putInt("emtiaz",emtiaz);
                emtiaz_txt.setText(FormatHelper.toPersianNumber("امتیاز:"+emtiaz));


                star=star-1;
                setShowStar(star);
                editor.putInt("stars",star);
                editor.apply();

            }
        }
    }

    private void chechWinner(int emtiaz) {
        int jam    =getjame(emtiaz);
        int has_jam=SP.getInt("jams",0);
        if (jam>has_jam){
            editor.putInt("jams",jam);
            editor.putBoolean("new_jam",true);
            editor.apply();
        }
    }

    private int getjame(int emtiaz) {
        if (emtiaz>=100 && emtiaz<200){
            return 1;
        }else if (emtiaz>=200 && emtiaz<300){
            return 2;
        }else if (emtiaz>=300 && emtiaz<400){
            return 3;
        }else if (emtiaz>=400 && emtiaz<500){
            return 4;
        }else if (emtiaz>=500 && emtiaz<600){
            return 5;
        }else if (emtiaz>=600){
            return 6;
        }

        return 0;
    }

    private void javabIsTrue() {
        MDToast mdToast= MDToast.makeText(getApplicationContext(),getString(R.string.truejavab),MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS);
        mdToast.show();
        updateEmtiaz(true);
        startListActivity();

    }

    private void startnew() {
        animFaz2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.exit);
        javab.startAnimation(animFaz2);


        animFaz1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                javab.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView.startAnimation(animFaz1);
                start.setVisibility(View.VISIBLE);
            }
        });
        ahdad=null;
        ahdad=new ArrayList<>();
        textView.setTextSize(60);
        textView.setText(getString(R.string.start));
        getJavabFinal="";
        timer=new Timer();
        ok_javab.setEnabled(true);
        javabTahih.setVisibility(View.GONE);
        javabEdit.setText("");
        count=0;
        hideSoftKeyboard(this);
    }



    private static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
