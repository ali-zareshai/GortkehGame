package com.kavireletronic.ali.gortkehgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class SelectLevelActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView asan_c,med_c,hard_c,pro_c;
    private ImageView img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        asan_c=(CardView)findViewById(R.id.card_view_asan);
        med_c=(CardView)findViewById(R.id.card_view_medium);
        hard_c=(CardView)findViewById(R.id.card_view_hard);
        pro_c=(CardView)findViewById(R.id.card_view_pro);

        img1=(ImageView)findViewById(R.id.thumbnail1);
        img2=(ImageView)findViewById(R.id.thumbnail2);
        img3=(ImageView)findViewById(R.id.thumbnail3);
        img4=(ImageView)findViewById(R.id.thumbnail4);

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
        Intent intent=new Intent(getApplicationContext(),ListActivity.class);
        if (view.getId()==asan_c.getId() || view.getId()==img1.getId()){
            intent.putExtra("level","1");
        }else if (view.getId()==med_c.getId() || view.getId()==img2.getId()){
            intent.putExtra("level","2");
        }else if (view.getId()==hard_c.getId() || view.getId()==img3.getId()){
            intent.putExtra("level","3");
        }else if (view.getId()==pro_c.getId() || view.getId()==img4.getId()){
            intent.putExtra("level","4");
        }
        startActivity(intent);

    }
}
