package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Gridselection extends AppCompatActivity {
    EditText grid;
    Button next;
    ImageView image;
    Animation frombottom;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridselection);

        grid = (EditText) findViewById(R.id.grid);
        next = (Button) findViewById(R.id.gamemode);
        image = (ImageView) findViewById(R.id.select);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom1);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop1);
        image.setAnimation(fromtop);
        next.setAnimation(frombottom);
        grid.setAnimation(frombottom);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String size = grid.getText().toString();
                if(grid.getText().toString().trim().length() <= 0){
                    grid.setError("Please enter a number");
                }else if(Integer.parseInt(size)<11 ==true && Integer.parseInt(size)>2 == true){
                    Intent intent = new Intent(Gridselection.this,beforegame.class);
                    intent.putExtra("gridsize",size);
                    startActivity(intent);
                }else{
                    grid.setError("Enter a number between 3 and 10 to play");
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
