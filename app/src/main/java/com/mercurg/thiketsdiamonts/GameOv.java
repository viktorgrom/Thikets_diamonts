package com.mercurg.thiketsdiamonts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class GameOv extends AppCompatActivity {

    TextView teVe;
    ImageView imVi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        int score = getIntent().getExtras().getInt("score");
        teVe = findViewById(R.id.tvScore);
        teVe.setText(" "+ score);

        imVi = findViewById(R.id.iv_stars);
        if (score <80){
            imVi.setImageResource(R.drawable.q);
        } else if (score <120){
            imVi.setImageResource(R.drawable.q1);
        } else if (score <=150){
            imVi.setImageResource(R.drawable.q2);
        } else if (score >150){
            imVi.setImageResource(R.drawable.q3);
        }
    }

    public void restart(View view) {
        Intent intent = new Intent(this, GamAct.class);
        startActivity(intent);
        finish();
    }
}
