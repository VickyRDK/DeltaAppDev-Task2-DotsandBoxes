package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Main5BotM extends AppCompatActivity {
    public static int gs4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_bot_m);
        Intent intent = getIntent();
        String str = intent.getStringExtra("GridSize");
        gs4 = Integer.parseInt(str);
    }
    @Override
    public void onBackPressed() {
        Intent l = new Intent(Main5BotM.this,popupgameexit.class);
        startActivity(l);
    }
}
