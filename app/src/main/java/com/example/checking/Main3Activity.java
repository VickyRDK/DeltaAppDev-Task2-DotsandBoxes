package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.checking.View.CustomView;
import com.example.checking.View.CustomView2;

public class Main3Activity extends AppCompatActivity {
    private CustomView2 mCustomView;
    public static int gs2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        String str = intent.getStringExtra("Gridsize");
        gs2 = Integer.parseInt(str);
    }

    @Override
    public void onBackPressed() {
        Intent l = new Intent(Main3Activity.this,popupgameexit.class);
        startActivity(l);
    }
}
