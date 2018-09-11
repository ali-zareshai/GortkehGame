package com.kavireletronic.ali.gortkehgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class SelectLevelActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView asan_c,med_c,hard_c,pro_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        asan_c=(CardView)findViewById(R.id.card_view_asan);
        med_c=(CardView)findViewById(R.id.card_view_medium);
        hard_c=(CardView)findViewById(R.id.card_view_hard);
        pro_c=(CardView)findViewById(R.id.card_view_pro);

        asan_c.setOnClickListener(this);
        med_c.setOnClickListener(this);
        hard_c.setOnClickListener(this);
        pro_c.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),ListActivity.class);
        if (view.getId()==asan_c.getId()){
            intent.putExtra("level","1");
        }else if (view.getId()==med_c.getId()){
            intent.putExtra("level","2");
        }else if (view.getId()==hard_c.getId()){
            intent.putExtra("level","3");
        }else if (view.getId()==pro_c.getId()){
            intent.putExtra("level","4");
        }
        startActivity(intent);
        finish();

    }
}
