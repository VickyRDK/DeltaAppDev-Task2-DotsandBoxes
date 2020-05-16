package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class Main4BotE extends AppCompatActivity {
public static int gs3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_bot_e);

        Intent intent = getIntent();
        String str = intent.getStringExtra("GridSize");
        gs3 = Integer.parseInt(str);
    }
    @Override
    public void onBackPressed() {
        Intent l = new Intent(Main4BotE.this,popupgameexit.class);
        startActivity(l);
    }
}
