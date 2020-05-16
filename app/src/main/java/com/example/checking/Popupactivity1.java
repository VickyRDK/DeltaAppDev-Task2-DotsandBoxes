package com.example.checking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Popupactivity1 extends AppCompatActivity {
    TextView winner;
    TextView draw;
    TextView wins;
    ImageView crown;
    ImageView thumbsdown;
    Button mainmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupactivity1);

        winner = (TextView) findViewById(R.id.winner);
        crown = (ImageView) findViewById(R.id.crown);
        thumbsdown = (ImageView) findViewById(R.id.thumbsdown);
        mainmenu = (Button) findViewById(R.id.mainmenu);
        Intent hello = getIntent();
        String str = hello.getStringExtra("winner");
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.victory);
        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.defeat);

        if(str.matches("DRAW")){
            mp1.start();
            crown.setVisibility(View.INVISIBLE);
            thumbsdown.setVisibility(View.VISIBLE);
            winner.setText("DRAW");
        }else if(str.matches("PLAYER1BOT")){
            mp.start();
            crown.setVisibility(View.VISIBLE);
            thumbsdown.setVisibility(View.INVISIBLE);
            winner.setText("YOU WIN");
        }
        else if(str.matches("PLAYER2BOT")){
            crown.setVisibility(View.INVISIBLE);
            thumbsdown.setVisibility(View.VISIBLE);
            mp1.start();
            winner.setText("BOT WINS");
        }else{
            mp.start();
            crown.setVisibility(View.VISIBLE);
            thumbsdown.setVisibility(View.INVISIBLE);
            winner.setText("  "+str+"\n     WINS");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int)(width*.9),(int)(height*.55));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);

        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(Popupactivity1.this,HomeActivity.class);
                startActivity(m);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(Popupactivity1.this,"Click the MAIN MENU button",Toast.LENGTH_SHORT).show();
    }
}
