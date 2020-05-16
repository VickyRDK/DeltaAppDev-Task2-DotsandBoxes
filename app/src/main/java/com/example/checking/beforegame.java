package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class beforegame extends AppCompatActivity {
   Button player1;
    Button player2;
    Button player3;
    Button player4;
    ImageView image;
    Animation frombottom;
    Animation fromtop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beforegame);

        player1 = (Button) findViewById(R.id.player1);
        player2 = (Button) findViewById(R.id.player2);
        player3 = (Button) findViewById(R.id.player3);
        player4 = (Button) findViewById(R.id.player4);
        image = (ImageView) findViewById(R.id.game);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom1);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop1);

        image.setAnimation(fromtop);
        player1.setAnimation(frombottom);
        player2.setAnimation(frombottom);
        player3.setAnimation(frombottom);
        player4.setAnimation(frombottom);


        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beforegame.this,MainActivity.class);
                Intent gridsize = getIntent();
                String str = gridsize.getStringExtra("gridsize");
                intent.putExtra("Gridsize",str);
                Log.d("okay",str);
                startActivity(intent);
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beforegame.this,Main2Activity.class);
                Intent gridsize = getIntent();
                String str = gridsize.getStringExtra("gridsize");
                intent.putExtra("Gridsize",str);
                startActivity(intent);
            }
        });
        player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beforegame.this,Main3Activity.class);
                Intent gridsize = getIntent();
                String str = gridsize.getStringExtra("gridsize");
                intent.putExtra("Gridsize",str);
                startActivity(intent);
            }
        });
        player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(beforegame.this,BotselectActivity.class);
                Intent gridsize = getIntent();
                String str = gridsize.getStringExtra("gridsize");
                intent.putExtra("Gridsize",str);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
