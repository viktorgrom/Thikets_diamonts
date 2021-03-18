package com.mercurg.thiketsdiamonts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartGam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
    }

    public void startGame(View view) {

        Intent intent = new Intent(this, GamAct.class);
        startActivity(intent);
        finish();
    }
}