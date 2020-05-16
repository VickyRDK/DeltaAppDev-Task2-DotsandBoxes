package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.example.checking.View.CustomView;


public class MainActivity extends AppCompatActivity {

    private CustomView mCustomView;
    public static int gs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Gridsize");
        gs = Integer.parseInt(str);

    }

    @Override
    public void onBackPressed() {
        Intent l = new Intent(MainActivity.this,popupgameexit.class);
        startActivity(l);
}
}

