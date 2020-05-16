package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Main6BotH extends AppCompatActivity {
public static int gs5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_bot_h);
        Intent intent = getIntent();
        String str = intent.getStringExtra("GridSize");
        gs5 = Integer.parseInt(str);
    }
    @Override
    public void onBackPressed() {
        Intent l = new Intent(Main6BotH.this,popupgameexit.class);
        startActivity(l);
    }
}
