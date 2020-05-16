package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.checking.View.CustomView;
import com.example.checking.View.CustomView1;

public class Main2Activity extends AppCompatActivity {
    private CustomView1 mCustomView;
    public static int gs1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Gridsize");
        gs1 = Integer.parseInt(str);
    }
    @Override
    public void onBackPressed() {
        Intent l = new Intent(Main2Activity.this,popupgameexit.class);
        startActivity(l);
    }
}
