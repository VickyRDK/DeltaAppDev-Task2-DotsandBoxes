package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class BotselectActivity extends AppCompatActivity {
    Button easy;
    Button medium;
    Button hard;
    ImageView image;
    Animation frombottom;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botselect);



        easy = (Button) findViewById(R.id.easy);
        medium = (Button) findViewById(R.id.medium);
        hard = (Button) findViewById(R.id.hard);
        image = (ImageView) findViewById(R.id.bot);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom1);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop1);
        image.setAnimation(fromtop);
        easy.setAnimation(frombottom);
        medium.setAnimation(frombottom);
        hard.setAnimation(frombottom);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BotselectActivity.this,Main4BotE.class);
                Intent gm = getIntent();
                String str = gm.getStringExtra("Gridsize");
                intent.putExtra("GridSize",str);
                startActivity(intent);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BotselectActivity.this,Main5BotM.class);
                Intent gm = getIntent();
                String str = gm.getStringExtra("Gridsize");
                intent.putExtra("GridSize",str);
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BotselectActivity.this,Main6BotH.class);
                Intent gm = getIntent();
                String str = gm.getStringExtra("Gridsize");
                intent.putExtra("GridSize",str);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
