package com.example.checking.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.checking.Popupactivity1;

import java.util.ArrayList;
import java.util.Random;

import static com.example.checking.Main6BotH.gs5;

public class CustomView6 extends View {

    private Paint mPaintCircle;
    private float mCircleX, mCircleY;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private ArrayList<Square> sq = new ArrayList<>();
    private ArrayList<Float> Xco = new ArrayList<>();
    private ArrayList<Float> Yco = new ArrayList<>();
    private ArrayList<Float> Co = new ArrayList<>();
    private ArrayList<Float> Sqlist = new ArrayList<>();
    private ArrayList<Float> linelist = new ArrayList<>();
    private float mCircleRadius = 20f;
    private Boolean point1 = false;
    private Boolean point2 = false;
    private Boolean point3 = false;
    private Boolean point4 = false;
    private Boolean point11 = false;
    private Boolean point22 = false;
    private Boolean point33 = false;
    private Boolean point44 = false;
    private Boolean sqtruth = false;
    private Boolean sqformed = false;
    private Boolean line1 = false;
    private Boolean line2 = false;
    private Boolean line3 = false;
    private Boolean line4 = false;
    private Boolean bot1 = false;
    private Boolean bot2 = false;
    private Boolean bot3 = false;
    private Boolean bot4 = false;
    private int GRID_SIZE =gs5;
    int countscore1;
    int countscore2;
    private int flag = 0;
    private float CELL1;
    Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    private float MARGIN1;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint5 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint painttext1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint painttext2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint painttext = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int playerturn = 0;
    private int count = 0;
    private int sqformedint = 0;
    private int calculateflag = 0;
    private Boolean flag1 = false;
    Random random = new Random();
    public void launch() {
        Intent i = new Intent(getContext(), Popupactivity1.class);
        if (countscore1 > countscore2) {
            i.putExtra("winner", "PLAYER1BOT");
        } else if (countscore1 < countscore2) {
            i.putExtra("winner", "PLAYER2BOT");
        } else {
            i.putExtra("winner", "DRAW");
        }
        getContext().startActivity(i);
    }


    public CustomView6(Context context) {
        super(context);
        init(null);
    }

    public CustomView6(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView6(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView6(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(Color.BLACK);
        paint.setColor(Color.RED);
        paint2.setColor(Color.BLUE);
        paint.setStrokeWidth(15);
        paint2.setStrokeWidth(15);
        paint4.setColor(Color.rgb(200, 21, 106));
        paint5.setColor(Color.rgb(72, 85, 218));

        if (set == null)
            return;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        GRID_SIZE=gs5+1;

        Paint paintrect = new Paint();
        paintrect.setColor(Color.rgb(107,67,33));
        RectF rect2 = new RectF();
        rect2.top = 0;
        rect2.bottom = (float) (getHeight() / 10 * 1.7);
        rect2.left = 0;
        rect2.right = getWidth();
        canvas.drawRect(rect2, paintrect);

        painttext1.setTextSize(100);
        painttext1.setColor(Color.BLUE);
        painttext2.setTextSize(100);
        painttext2.setColor(Color.RED);
        painttext.setTextSize(100);
        painttext.setColor(Color.WHITE);
        canvas.drawText(String.valueOf(countscore1), getWidth() / 3 - 25, getHeight() / 10, painttext2);
        canvas.drawText(String.valueOf(countscore2), getWidth() / 3 * 2 - 25, getHeight() / 10, painttext1);
        canvas.drawText("YOU", getWidth()/6- 120, getHeight() / 10 , painttext);
        canvas.drawText("BOT", getWidth() / 3 * 2 + getWidth()/6-60, getHeight() / 10  ,painttext);

        float CELL = getWidth() / (GRID_SIZE + 1);
        float MARGIN = (getHeight() - ((GRID_SIZE) * CELL)) / 2;
        CELL1 = CELL;
        MARGIN1 = MARGIN;

        for (Square s : sq) {
            RectF rect = new RectF();
            rect.top = s.top;
            rect.left = s.left;
            rect.bottom = s.bottom;
            rect.right = s.right;
            canvas.drawRect(rect, s.paint3);

        }
        int r = 0;
        for (Line l : lines) {
            if(r==(lines.size()-1)){
                l.paint1.setStrokeWidth(25);
                canvas.drawLine(l.startX, l.startY, l.stopX, l.stopY, l.paint1);
            }else {
                l.paint1.setStrokeWidth(15);
                canvas.drawLine(l.startX, l.startY, l.stopX, l.stopY, l.paint1);
            }
            r++;
        }
        for (int i = 0; i < GRID_SIZE; i++) {
            mCircleY = gridY(i);
            for (int j = 0; j < GRID_SIZE; j++) {
                mCircleX = gridX(j);
                Xco.add(mCircleX);
            }
            Yco.add(mCircleY);
        }
        if (calculateflag == 0) {
            calculateflag++;
            int k = 0;
            for (int i = 0; i < (GRID_SIZE); i++) {
                for (int j = 0; j < (GRID_SIZE - 1); j++) {
                    linelist.add(Xco.get(k));
                    linelist.add(Yco.get(i));
                    linelist.add(Xco.get(k + 1));
                    linelist.add(Yco.get(i));
                    k++;
                }
                k++;
            }
            int l = 0;
            for (int i = 0; i < (GRID_SIZE - 1); i++) {
                for (int j = 0; j < (GRID_SIZE); j++) {
                    linelist.add(Xco.get(j));
                    linelist.add(Yco.get(l));
                    linelist.add(Xco.get(j));
                    linelist.add(Yco.get(l + 1));
                }
                l++;
            }
        }
        for (int i = 0; i < GRID_SIZE; i++) {
            mCircleY = gridY(i);
            for (int j = 0; j < GRID_SIZE; j++) {
                mCircleX = gridX(j);
                canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);
            }
        }
        if (sq.size() == (gs5 * gs5)) {
            launch();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            flag1 = false;
            int k = 0;
            for (int i = 0; i < (GRID_SIZE); i++) {
                for (int j = 0; j < (GRID_SIZE - 1); j++) {
                    if ((Xco.get(k)) < x && (Xco.get(k + 1) > x)) {
                        if ((Yco.get(i) - 20) < y && (Yco.get(i) + 20) > y) {
                            if (playerturn != 0) {
                                for (int q = 0; q < Co.size(); q++) {
                                    if (Xco.get(k) == Co.get(4 * q) && Yco.get(i) == Co.get(4 * q + 1) && Xco.get(k + 1) == Co.get(4 * q + 2) && Yco.get(i) == Co.get(4 * q + 3)) {
                                        count++;
                                        flag1 = true;
                                        break;
                                    } else {
                                        count = 0;
                                        flag1 = false;
                                    }
                                    if (4 * q + 4 == Co.size()) {
                                        break;
                                    }
                                }
                            }
                            if (count == 0) {
                                playerturn++;

                                if (playerturn % 2 == 1) {
                                    lines.add(new Line(Xco.get(k), Yco.get(i), Xco.get(k + 1), Yco.get(i), paint));
                                    Co.add(Xco.get(k));
                                    Co.add(Yco.get(i));
                                    Co.add(Xco.get(k + 1));
                                    Co.add(Yco.get(i));
                                    for (int m = 0; m < linelist.size(); m++) {
                                        if (Xco.get(k) == linelist.get(4 * m) && Yco.get(i) == linelist.get(4 * m + 1) && Xco.get(k + 1) == linelist.get(4 * m + 2) && Yco.get(i) == linelist.get(4 * m + 3)) {
                                            linelist.remove(4 * m + 3);
                                            linelist.remove(4 * m + 2);
                                            linelist.remove(4 * m + 1);
                                            linelist.remove(4 * m + 0);
                                            break;
                                        }
                                        if (4 * m + 4 == linelist.size()) {
                                            break;
                                        }
                                    }
                                    invalidate();

                                }
                                playerturn++;
                            }

                            count = 0;
                        }
                        k++;
                    }
                    k++;
                }
                count = 0;
            }

            int l = 0;
            for (int i = 0; i < (GRID_SIZE - 1); i++) {
                for (int j = 0; j < (GRID_SIZE); j++) {
                    if ((Xco.get(j) - 20) < x && (Xco.get(j) + 20) > x) {
                        if ((Yco.get(l)) < y && (Yco.get(l + 1)) > y) {
                            if (playerturn != 0) {
                                for (int q = 0; q < Co.size(); q++) {
                                    if (Xco.get(j) == Co.get(4 * q) && Yco.get(l) == Co.get(4 * q + 1) && Xco.get(j) == Co.get(4 * q + 2) && Yco.get(l + 1) == Co.get(4 * q + 3)) {
                                        count++;
                                        flag1 = true;
                                        break;
                                    } else {
                                        count = 0;
                                        flag1 = false;
                                    }
                                    if (4 * q + 4 == Co.size()) {
                                        break;
                                    }
                                }
                            }
                            if (count == 0) {
                                playerturn++;
                                if (playerturn % 2 == 1) {
                                    lines.add(new Line(Xco.get(j), Yco.get(l), Xco.get(j), Yco.get(l + 1), paint));
                                    Co.add(Xco.get(j));
                                    Co.add(Yco.get(l));
                                    Co.add(Xco.get(j));
                                    Co.add(Yco.get(l + 1));
                                    for (int m = 0; m < linelist.size(); m++) {
                                        if (Xco.get(j) == linelist.get(4 * m) && Yco.get(l) == linelist.get(4 * m + 1) && Xco.get(j) == linelist.get(4 * m + 2) && Yco.get(l + 1) == linelist.get(4 * m + 3)) {
                                            linelist.remove(4 * m + 3);
                                            linelist.remove(4 * m + 2);
                                            linelist.remove(4 * m + 1);
                                            linelist.remove(4 * m + 0);
                                            break;
                                        }
                                        if (4 * m + 4 == linelist.size()) {
                                            break;
                                        }
                                    }
                                    invalidate();

                                }
                                playerturn++;
                            }
                        }
                        count = 0;
                    }
                }
                l++;
            }
        }
        int k1 = 0;
        int l1 = 0;
        for (int i = 0; i < (GRID_SIZE - 1); i++) {
            for (int j = 0; j < (GRID_SIZE - 1); j++) {
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(k1) == Co.get(4 * n) && Yco.get(i) == Co.get(4 * n + 1) && Xco.get(k1 + 1) == Co.get(4 * n + 2) && Yco.get(i) == Co.get(4 * n + 3)) {
                        point1 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }
                }
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(k1 + GRID_SIZE) == Co.get(4 * n) && Yco.get(i + 1) == Co.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Co.get(4 * n + 2) && Yco.get(i + 1) == Co.get(4 * n + 3)) {
                        point2 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }

                }
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(j) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                        point3 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }

                }
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(j + 1) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j + 1) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                        point4 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }

                }
                if (point1 == true && point2 == true && point3 == true && point4 == true) {
                    for (int n = 0; n < Sqlist.size(); n++) {
                        if (Xco.get(k1) == Sqlist.get(4 * n) && Yco.get(l1) == Sqlist.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Sqlist.get(4 * n + 2) && Yco.get(l1 + 1) == Sqlist.get(4 * n + 3)) {
                            sqtruth = true;
                            break;
                        }
                        if (4 * n + 4 == Sqlist.size()) {
                            break;
                        }
                    }
                    if (sqtruth != true) {
                        flag++;
                        vib.vibrate(300);
                        if (playerturn % 2 == 1) {
                            sq.add(new Square(Xco.get(k1), Yco.get(l1), Xco.get(k1 + (GRID_SIZE + 1)), Yco.get(l1 + 1), paint4, playerturn));
                            Sqlist.add(Xco.get(k1));
                            Sqlist.add(Yco.get(l1));
                            Sqlist.add(Xco.get(k1 + (GRID_SIZE + 1)));
                            Sqlist.add(Yco.get(l1 + 1));
                            invalidate();
                        }
                    } else {
                        sqtruth = false;
                    }
                }
                k1++;
                point1 = false;
                point2 = false;
                point3 = false;
                point4 = false;
            }
            l1++;
            k1++;

        }

        if (flag > 0) {
            playerturn--;
        }
        flag = 0;

        if (linelist.size() > 0) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();

                if (flag1 == false) {
                    if (playerturn % 2 == 0) {
                        botsquare(x, y);
                        flag1 = true;
                        invalidate();
                    }
                    while (sqformed == true) {
                        if (linelist.size() == 0) {
                            break;
                        } else {
                            botsquare(x, y);
                            invalidate();
                        }
                    }
                    if (sqformedint > 0) {
                        playerturn++;
                    }
                    sqformedint = 0;
                }
            }
        }
        if (sq.size() > 0) {
            countscore1 = 0;
            countscore2 = 0;
            for (Square m : sq) {
                if (m.paint3 == paint4) {
                    countscore1++;
                }
                if (m.paint3 == paint5) {
                    countscore2++;
                }
                invalidate();
            }
        }
        invalidate();
        return true;
    }

    private void botsquare(float x, float y) {
        sqformed = false;
        point11 = false;
        point22 = false;
        point33 = false;
        point44 = false;
        line1 = false;
        line2 = false;
        line3 = false;
        line4 = false;
        bot1 = false;
        bot2 = false;
        bot3 = false;
        bot4 = false;
        int k = 0;
        for (int o = 0; o < (GRID_SIZE); o++) {
            for (int p = 0; p < (GRID_SIZE - 1); p++) {
                if ((Xco.get(k)) < x && (Xco.get(k + 1) > x)) {
                    if ((Yco.get(o) - 20) < y && (Yco.get(o) + 20) > y) {
                        int k1 = 0;
                        int l1 = 0;
                        for (int i = 0; i < (GRID_SIZE - 1); i++) {
                            for (int j = 0; j < (GRID_SIZE - 1); j++) {
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(k1) == Co.get(4 * n) && Yco.get(i) == Co.get(4 * n + 1) && Xco.get(k1 + 1) == Co.get(4 * n + 2) && Yco.get(i) == Co.get(4 * n + 3)) {
                                        point11 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }
                                }
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(k1 + GRID_SIZE) == Co.get(4 * n) && Yco.get(i + 1) == Co.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Co.get(4 * n + 2) && Yco.get(i + 1) == Co.get(4 * n + 3)) {
                                        point22 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }

                                }
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(j) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                        point33 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }

                                }
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(j + 1) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j + 1) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                        point44 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }

                                }
                                if (point11 == true && point22 == true && point33 == true && point44 == false) {
                                    lines.add(new Line(Xco.get(j + 1), Yco.get(l1), Xco.get(j + 1), Yco.get(l1 + 1), paint2));
                                    Co.add(Xco.get(j + 1));
                                    Co.add(Yco.get(l1));
                                    Co.add(Xco.get(j + 1));
                                    Co.add(Yco.get(l1 + 1));
                                    line1 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(j + 1) == linelist.get(4 * n) && Yco.get(l1) == linelist.get(4 * n + 1) && Xco.get(j + 1) == linelist.get(4 * n + 2) && Yco.get(l1 + 1) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line1 == true) {
                                    break;
                                }
                                if (point33 == true && point44 == true && point11 == true && point22 == false) {
                                    lines.add(new Line(Xco.get(k1 + GRID_SIZE), Yco.get(i + 1), Xco.get(k1 + (GRID_SIZE + 1)), Yco.get(i + 1), paint2));
                                    Co.add(Xco.get(k1 + GRID_SIZE));
                                    Co.add(Yco.get(i + 1));
                                    Co.add(Xco.get(k1 + (GRID_SIZE + 1)));
                                    Co.add(Yco.get(i + 1));
                                    line2 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(k1 + GRID_SIZE) == linelist.get(4 * n) && Yco.get(i + 1) == linelist.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == linelist.get(4 * n + 2) && Yco.get(i + 1) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line2 == true) {
                                    break;
                                }
                                if (point22 == true && point33 == true && point44 == true && point11 == false) {
                                    lines.add(new Line(Xco.get(k1), Yco.get(i), Xco.get(k1 + 1), Yco.get(i), paint2));
                                    Co.add(Xco.get(k1));
                                    Co.add(Yco.get(i));
                                    Co.add(Xco.get(k1 + 1));
                                    Co.add(Yco.get(i));
                                    line3 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(k1) == linelist.get(4 * n) && Yco.get(i) == linelist.get(4 * n + 1) && Xco.get(k1 + 1) == linelist.get(4 * n + 2) && Yco.get(i) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line3 == true) {
                                    break;
                                }
                                if (point44 == true && point11 == true && point22 == true && point33 == false) {
                                    lines.add(new Line(Xco.get(j), Yco.get(l1), Xco.get(j), Yco.get(l1 + 1), paint2));
                                    Co.add(Xco.get(j));
                                    Co.add(Yco.get(l1));
                                    Co.add(Xco.get(j));
                                    Co.add(Yco.get(l1 + 1));
                                    line4 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(j) == linelist.get(4 * n) && Yco.get(l1) == linelist.get(4 * n + 1) && Xco.get(j) == linelist.get(4 * n + 2) && Yco.get(l1 + 1) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line4 == true) {
                                    break;
                                }
                                point11 = false;
                                point22 = false;
                                point33 = false;
                                point44 = false;
                                k1++;
                            }
                            if (line1 == true || line2 == true || line3 == true || line4 == true) {
                                break;
                            }
                            l1++;
                            k1++;
                        }
                        if (line1 == false && line2 == false && line3 == false && line4 == false) {
                            ArrayList<Float> botlines = new ArrayList<>();
                            int s = 0;
                            for (int i = 0; i < (GRID_SIZE); i++) {
                                for (int j = 0; j < (GRID_SIZE - 1); j++) {
                                    botlines.add(Xco.get(s));
                                    botlines.add(Yco.get(i));
                                    botlines.add(Xco.get(s + 1));
                                    botlines.add(Yco.get(i));
                                    s++;
                                }
                                s++;
                            }
                            int a = 0;
                            for (int i = 0; i < (GRID_SIZE - 1); i++) {
                                for (int j = 0; j < (GRID_SIZE); j++) {
                                    botlines.add(Xco.get(j));
                                    botlines.add(Yco.get(a));
                                    botlines.add(Xco.get(j));
                                    botlines.add(Yco.get(a + 1));
                                }
                                a++;
                            }
                            for (int n1 = 0; n1 < Co.size(); n1++) {
                                for (int n = 0; n < botlines.size(); n++) {
                                    if (Co.get(4 * n1) == botlines.get(4 * n) && Co.get(4 * n1 + 1) == botlines.get(4 * n + 1) && Co.get(4 * n1 + 2) == botlines.get(4 * n + 2) && Co.get(4 * n1 + 3) == botlines.get(4 * n + 3)) {
                                        botlines.remove(4 * n + 3);
                                        botlines.remove(4 * n + 2);
                                        botlines.remove(4 * n + 1);
                                        botlines.remove(4 * n);
                                        break;
                                    }
                                    if (4 * n + 4 == botlines.size()) {
                                        break;
                                    }

                                }
                                if (4 * n1 + 4 == Co.size()) {
                                    break;
                                }
                            }
                            Log.d("okay", String.valueOf(botlines.size()));
                            k1 = 0;
                            l1 = 0;
                            for (int i = 0; i < (GRID_SIZE - 1); i++) {
                                for (int j = 0; j < (GRID_SIZE - 1); j++) {
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(k1) == Co.get(4 * n) && Yco.get(i) == Co.get(4 * n + 1) && Xco.get(k1 + 1) == Co.get(4 * n + 2) && Yco.get(i) == Co.get(4 * n + 3)) {
                                            bot1 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }
                                    }
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(k1 + GRID_SIZE) == Co.get(4 * n) && Yco.get(i + 1) == Co.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Co.get(4 * n + 2) && Yco.get(i + 1) == Co.get(4 * n + 3)) {
                                            bot2 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }

                                    }
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(j) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                            bot3 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }

                                    }
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(j + 1) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j + 1) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                            bot4 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }

                                    }

                                    if (bot1 == true && bot2 == true && bot3 == false && bot4 == false) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot3 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j + 1) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j + 1) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot4 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == false && bot2 == false && bot3 == true && bot4 == true) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1) == botlines.get(4 * n) && Yco.get(i) == botlines.get(4 * n + 1) && Xco.get(k1 + 1) == botlines.get(4 * n + 2) && Yco.get(i) == botlines.get(4 * n + 3)) {
                                                bot1 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }
                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1 + GRID_SIZE) == botlines.get(4 * n) && Yco.get(i + 1) == botlines.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == botlines.get(4 * n + 2) && Yco.get(i + 1) == botlines.get(4 * n + 3)) {
                                                bot2 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == true && bot2 == false && bot3 == true && bot4 == false) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1 + GRID_SIZE) == botlines.get(4 * n) && Yco.get(i + 1) == botlines.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == botlines.get(4 * n + 2) && Yco.get(i + 1) == botlines.get(4 * n + 3)) {
                                                bot2 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j + 1) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j + 1) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot4 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == false && bot2 == true && bot3 == false && bot4 == true) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1) == botlines.get(4 * n) && Yco.get(i) == botlines.get(4 * n + 1) && Xco.get(k1 + 1) == botlines.get(4 * n + 2) && Yco.get(i) == botlines.get(4 * n + 3)) {
                                                bot1 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }
                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot3 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == false && bot2 == true && bot3 == true && bot4 == false) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1) == botlines.get(4 * n) && Yco.get(i) == botlines.get(4 * n + 1) && Xco.get(k1 + 1) == botlines.get(4 * n + 2) && Yco.get(i) == botlines.get(4 * n + 3)) {
                                                bot1 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }
                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j + 1) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j + 1) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot4 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == true && bot2 == false && bot3 == false && bot4 == true) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot3 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1 + GRID_SIZE) == botlines.get(4 * n) && Yco.get(i + 1) == botlines.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == botlines.get(4 * n + 2) && Yco.get(i + 1) == botlines.get(4 * n + 3)) {
                                                bot2 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    bot1 = false;
                                    bot2 = false;
                                    bot3 = false;
                                    bot4 = false;
                                    k1++;
                                }
                                l1++;
                                k1++;
                            }
                            Log.d("okay", String.valueOf(botlines.size()));
                            if (botlines.size() != 0) {
                                int num = botlines.size() / 4 - 1;
                                if (num != 0) {
                                    int rnum = random.nextInt(num);
                                    lines.add(new Line(botlines.get(4 * rnum), botlines.get(4 * rnum + 1), botlines.get(4 * rnum + 2), botlines.get(4 * rnum + 3), paint2));
                                    Co.add(botlines.get(4 * rnum));
                                    Co.add(botlines.get(4 * rnum + 1));
                                    Co.add(botlines.get(4 * rnum + 2));
                                    Co.add(botlines.get(4 * rnum + 3));
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (botlines.get(4 * rnum) == linelist.get(4 * n) && botlines.get(4 * rnum + 1) == linelist.get(4 * n + 1) && botlines.get(4 * rnum + 2) == linelist.get(4 * n + 2) && botlines.get(4 * rnum + 3) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                } else {
                                    lines.add(new Line(botlines.get(0), botlines.get(1), botlines.get(2), botlines.get(3), paint2));
                                    Co.add(botlines.get(0));
                                    Co.add(botlines.get(1));
                                    Co.add(botlines.get(2));
                                    Co.add(botlines.get(3));
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (botlines.get(0) == linelist.get(4 * n) && botlines.get(1) == linelist.get(4 * n + 1) && botlines.get(2) == linelist.get(4 * n + 2) && botlines.get(3) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                            } else {
                                int num = linelist.size() / 4 - 1;
                                if (num == 0) {
                                    lines.add(new Line(linelist.get(0), linelist.get(1), linelist.get(2), linelist.get(3), paint2));
                                    Co.add(linelist.get(0));
                                    Co.add(linelist.get(1));
                                    Co.add(linelist.get(2));
                                    Co.add(linelist.get(3));
                                    linelist.remove(3);
                                    linelist.remove(2);
                                    linelist.remove(1);
                                    linelist.remove(0);
                                    break;
                                } else {
                                    int rnum = random.nextInt(num);
                                    lines.add(new Line(linelist.get(4 * rnum), linelist.get(4 * rnum + 1), linelist.get(4 * rnum + 2), linelist.get(4 * rnum + 3), paint2));
                                    Co.add(linelist.get(4 * rnum));
                                    Co.add(linelist.get(4 * rnum + 1));
                                    Co.add(linelist.get(4 * rnum + 2));
                                    Co.add(linelist.get(4 * rnum + 3));
                                    linelist.remove(4 * rnum + 3);
                                    linelist.remove(4 * rnum + 2);
                                    linelist.remove(4 * rnum + 1);
                                    linelist.remove(4 * rnum);
                                    break;
                                }
                            }
                        }
                    }
                }
                k++;
            }
            k++;
        }
        line1 = false;
        line2 = false;
        line3 = false;
        line4 = false;
        int l = 0;
        for (int o = 0; o < (GRID_SIZE - 1); o++) {
            for (int p = 0; p < (GRID_SIZE); p++) {
                if ((Xco.get(p) - 20) < x && (Xco.get(p) + 20) > x) {
                    if ((Yco.get(l)) < y && (Yco.get(l + 1)) > y) {
                        int k1 = 0;
                        int l1 = 0;
                        for (int i = 0; i < (GRID_SIZE - 1); i++) {
                            for (int j = 0; j < (GRID_SIZE - 1); j++) {
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(k1) == Co.get(4 * n) && Yco.get(i) == Co.get(4 * n + 1) && Xco.get(k1 + 1) == Co.get(4 * n + 2) && Yco.get(i) == Co.get(4 * n + 3)) {
                                        point11 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }
                                }
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(k1 + GRID_SIZE) == Co.get(4 * n) && Yco.get(i + 1) == Co.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Co.get(4 * n + 2) && Yco.get(i + 1) == Co.get(4 * n + 3)) {
                                        point22 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }

                                }
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(j) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                        point33 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }

                                }
                                for (int n = 0; n < Co.size(); n++) {
                                    if (Xco.get(j + 1) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j + 1) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                        point44 = true;
                                        break;
                                    }
                                    if (4 * n + 4 == Co.size()) {
                                        break;
                                    }

                                }
                                if (point11 == true && point22 == true && point33 == true && point44 == false) {
                                    lines.add(new Line(Xco.get(j + 1), Yco.get(l1), Xco.get(j + 1), Yco.get(l1 + 1), paint2));
                                    Co.add(Xco.get(j + 1));
                                    Co.add(Yco.get(l1));
                                    Co.add(Xco.get(j + 1));
                                    Co.add(Yco.get(l1 + 1));
                                    line1 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(j + 1) == linelist.get(4 * n) && Yco.get(l1) == linelist.get(4 * n + 1) && Xco.get(j + 1) == linelist.get(4 * n + 2) && Yco.get(l1 + 1) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line1 == true) {
                                    break;
                                }
                                if (point33 == true && point44 == true && point11 == true && point22 == false) {
                                    lines.add(new Line(Xco.get(k1 + GRID_SIZE), Yco.get(i + 1), Xco.get(k1 + (GRID_SIZE + 1)), Yco.get(i + 1), paint2));
                                    Co.add(Xco.get(k1 + GRID_SIZE));
                                    Co.add(Yco.get(i + 1));
                                    Co.add(Xco.get(k1 + (GRID_SIZE + 1)));
                                    Co.add(Yco.get(i + 1));
                                    line2 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(k1 + GRID_SIZE) == linelist.get(4 * n) && Yco.get(i + 1) == linelist.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == linelist.get(4 * n + 2) && Yco.get(i + 1) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line2 == true) {
                                    break;
                                }
                                if (point22 == true && point33 == true && point44 == true && point11 == false) {
                                    lines.add(new Line(Xco.get(k1), Yco.get(i), Xco.get(k1 + 1), Yco.get(i), paint2));
                                    Co.add(Xco.get(k1));
                                    Co.add(Yco.get(i));
                                    Co.add(Xco.get(k1 + 1));
                                    Co.add(Yco.get(i));
                                    line3 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(k1) == linelist.get(4 * n) && Yco.get(i) == linelist.get(4 * n + 1) && Xco.get(k1 + 1) == linelist.get(4 * n + 2) && Yco.get(i) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line3 == true) {
                                    break;
                                }
                                if (point44 == true && point11 == true && point22 == true && point33 == false) {
                                    lines.add(new Line(Xco.get(j), Yco.get(l1), Xco.get(j), Yco.get(l1 + 1), paint2));
                                    Co.add(Xco.get(j));
                                    Co.add(Yco.get(l1));
                                    Co.add(Xco.get(j));
                                    Co.add(Yco.get(l1 + 1));
                                    line4 = true;
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (Xco.get(j) == linelist.get(4 * n) && Yco.get(l1) == linelist.get(4 * n + 1) && Xco.get(j) == linelist.get(4 * n + 2) && Yco.get(l1 + 1) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                                if (line4 == true) {
                                    break;
                                }
                                point11 = false;
                                point22 = false;
                                point33 = false;
                                point44 = false;
                                k1++;
                            }
                            if (line1 == true || line2 == true || line3 == true || line4 == true) {
                                break;
                            }
                            l1++;
                            k1++;
                        }
                        if (line1 == false && line2 == false && line3 == false && line4 == false) {
                            ArrayList<Float> botlines = new ArrayList<>();
                            int s = 0;
                            for (int i = 0; i < (GRID_SIZE); i++) {
                                for (int j = 0; j < (GRID_SIZE - 1); j++) {
                                    botlines.add(Xco.get(s));
                                    botlines.add(Yco.get(i));
                                    botlines.add(Xco.get(s + 1));
                                    botlines.add(Yco.get(i));
                                    s++;
                                }
                                s++;
                            }
                            int a = 0;
                            for (int i = 0; i < (GRID_SIZE - 1); i++) {
                                for (int j = 0; j < (GRID_SIZE); j++) {
                                    botlines.add(Xco.get(j));
                                    botlines.add(Yco.get(a));
                                    botlines.add(Xco.get(j));
                                    botlines.add(Yco.get(a + 1));
                                }
                                a++;
                            }
                            for (int n1 = 0; n1 < Co.size(); n1++) {
                                for (int n = 0; n < botlines.size(); n++) {
                                    if (Co.get(4 * n1) == botlines.get(4 * n) && Co.get(4 * n1 + 1) == botlines.get(4 * n + 1) && Co.get(4 * n1 + 2) == botlines.get(4 * n + 2) && Co.get(4 * n1 + 3) == botlines.get(4 * n + 3)) {
                                        botlines.remove(4 * n + 3);
                                        botlines.remove(4 * n + 2);
                                        botlines.remove(4 * n + 1);
                                        botlines.remove(4 * n);
                                        break;
                                    }
                                    if (4 * n + 4 == botlines.size()) {
                                        break;
                                    }

                                }
                                if (4 * n1 + 4 == Co.size()) {
                                    break;
                                }
                            }
                            Log.d("okay", String.valueOf(botlines.size()));
                            k1 = 0;
                            l1 = 0;
                            for (int i = 0; i < (GRID_SIZE - 1); i++) {
                                for (int j = 0; j < (GRID_SIZE - 1); j++) {
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(k1) == Co.get(4 * n) && Yco.get(i) == Co.get(4 * n + 1) && Xco.get(k1 + 1) == Co.get(4 * n + 2) && Yco.get(i) == Co.get(4 * n + 3)) {
                                            bot1 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }
                                    }
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(k1 + GRID_SIZE) == Co.get(4 * n) && Yco.get(i + 1) == Co.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Co.get(4 * n + 2) && Yco.get(i + 1) == Co.get(4 * n + 3)) {
                                            bot2 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }

                                    }
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(j) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                            bot3 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }

                                    }
                                    for (int n = 0; n < Co.size(); n++) {
                                        if (Xco.get(j + 1) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j + 1) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                                            bot4 = true;
                                            break;
                                        }
                                        if (4 * n + 4 == Co.size()) {
                                            break;
                                        }

                                    }

                                    if (bot1 == true && bot2 == true && bot3 == false && bot4 == false) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot3 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j + 1) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j + 1) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot4 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == false && bot2 == false && bot3 == true && bot4 == true) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1) == botlines.get(4 * n) && Yco.get(i) == botlines.get(4 * n + 1) && Xco.get(k1 + 1) == botlines.get(4 * n + 2) && Yco.get(i) == botlines.get(4 * n + 3)) {
                                                bot1 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }
                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1 + GRID_SIZE) == botlines.get(4 * n) && Yco.get(i + 1) == botlines.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == botlines.get(4 * n + 2) && Yco.get(i + 1) == botlines.get(4 * n + 3)) {
                                                bot2 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == true && bot2 == false && bot3 == true && bot4 == false) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1 + GRID_SIZE) == botlines.get(4 * n) && Yco.get(i + 1) == botlines.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == botlines.get(4 * n + 2) && Yco.get(i + 1) == botlines.get(4 * n + 3)) {
                                                bot2 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j + 1) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j + 1) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot4 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == false && bot2 == true && bot3 == false && bot4 == true) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1) == botlines.get(4 * n) && Yco.get(i) == botlines.get(4 * n + 1) && Xco.get(k1 + 1) == botlines.get(4 * n + 2) && Yco.get(i) == botlines.get(4 * n + 3)) {
                                                bot1 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }
                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot3 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == false && bot2 == true && bot3 == true && bot4 == false) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1) == botlines.get(4 * n) && Yco.get(i) == botlines.get(4 * n + 1) && Xco.get(k1 + 1) == botlines.get(4 * n + 2) && Yco.get(i) == botlines.get(4 * n + 3)) {
                                                bot1 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }
                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j + 1) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j + 1) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot4 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    if (bot1 == true && bot2 == false && bot3 == false && bot4 == true) {
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(j) == botlines.get(4 * n) && Yco.get(l1) == botlines.get(4 * n + 1) && Xco.get(j) == botlines.get(4 * n + 2) && Yco.get(l1 + 1) == botlines.get(4 * n + 3)) {
                                                bot3 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                        for (int n = 0; n < botlines.size(); n++) {
                                            if (Xco.get(k1 + GRID_SIZE) == botlines.get(4 * n) && Yco.get(i + 1) == botlines.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == botlines.get(4 * n + 2) && Yco.get(i + 1) == botlines.get(4 * n + 3)) {
                                                bot2 = true;
                                                botlines.remove(4 * n + 3);
                                                botlines.remove(4 * n + 2);
                                                botlines.remove(4 * n + 1);
                                                botlines.remove(4 * n);
                                                break;
                                            }
                                            if (4 * n + 4 == botlines.size()) {
                                                break;
                                            }

                                        }
                                    }
                                    bot1 = false;
                                    bot2 = false;
                                    bot3 = false;
                                    bot4 = false;
                                    k1++;
                                }
                                l1++;
                                k1++;
                            }
                            Log.d("okay", String.valueOf(botlines.size()));
                            if (botlines.size() != 0) {
                                int num = botlines.size() / 4 - 1;
                                if (num != 0) {
                                    int rnum = random.nextInt(num);
                                    lines.add(new Line(botlines.get(4 * rnum), botlines.get(4 * rnum + 1), botlines.get(4 * rnum + 2), botlines.get(4 * rnum + 3), paint2));
                                    Co.add(botlines.get(4 * rnum));
                                    Co.add(botlines.get(4 * rnum + 1));
                                    Co.add(botlines.get(4 * rnum + 2));
                                    Co.add(botlines.get(4 * rnum + 3));
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (botlines.get(4 * rnum) == linelist.get(4 * n) && botlines.get(4 * rnum + 1) == linelist.get(4 * n + 1) && botlines.get(4 * rnum + 2) == linelist.get(4 * n + 2) && botlines.get(4 * rnum + 3) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                } else {
                                    lines.add(new Line(botlines.get(0), botlines.get(1), botlines.get(2), botlines.get(3), paint2));
                                    Co.add(botlines.get(0));
                                    Co.add(botlines.get(1));
                                    Co.add(botlines.get(2));
                                    Co.add(botlines.get(3));
                                    for (int n = 0; n < linelist.size(); n++) {
                                        if (botlines.get(0) == linelist.get(4 * n) && botlines.get(1) == linelist.get(4 * n + 1) && botlines.get(2) == linelist.get(4 * n + 2) && botlines.get(3) == linelist.get(4 * n + 3)) {
                                            linelist.remove(4 * n + 3);
                                            linelist.remove(4 * n + 2);
                                            linelist.remove(4 * n + 1);
                                            linelist.remove(4 * n);
                                            break;
                                        }
                                    }
                                }
                            } else {
                                int num = linelist.size() / 4 - 1;
                                if (num == 0) {
                                    lines.add(new Line(linelist.get(0), linelist.get(1), linelist.get(2), linelist.get(3), paint2));
                                    Co.add(linelist.get(0));
                                    Co.add(linelist.get(1));
                                    Co.add(linelist.get(2));
                                    Co.add(linelist.get(3));
                                    linelist.remove(3);
                                    linelist.remove(2);
                                    linelist.remove(1);
                                    linelist.remove(0);
                                    break;
                                } else {
                                    int rnum = random.nextInt(num);
                                    lines.add(new Line(linelist.get(4 * rnum), linelist.get(4 * rnum + 1), linelist.get(4 * rnum + 2), linelist.get(4 * rnum + 3), paint2));
                                    Co.add(linelist.get(4 * rnum));
                                    Co.add(linelist.get(4 * rnum + 1));
                                    Co.add(linelist.get(4 * rnum + 2));
                                    Co.add(linelist.get(4 * rnum + 3));
                                    linelist.remove(4 * rnum + 3);
                                    linelist.remove(4 * rnum + 2);
                                    linelist.remove(4 * rnum + 1);
                                    linelist.remove(4 * rnum);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            l++;
        }
        line1 = false;
        line2 = false;
        line3 = false;
        line4 = false;
        int k1 = 0;
        int l1 = 0;
        for (int i = 0; i < (GRID_SIZE - 1); i++) {
            for (int j = 0; j < (GRID_SIZE - 1); j++) {
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(k1) == Co.get(4 * n) && Yco.get(i) == Co.get(4 * n + 1) && Xco.get(k1 + 1) == Co.get(4 * n + 2) && Yco.get(i) == Co.get(4 * n + 3)) {
                        point1 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }
                }
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(k1 + GRID_SIZE) == Co.get(4 * n) && Yco.get(i + 1) == Co.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Co.get(4 * n + 2) && Yco.get(i + 1) == Co.get(4 * n + 3)) {
                        point2 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }

                }
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(j) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                        point3 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }

                }
                for (int n = 0; n < Co.size(); n++) {
                    if (Xco.get(j + 1) == Co.get(4 * n) && Yco.get(l1) == Co.get(4 * n + 1) && Xco.get(j + 1) == Co.get(4 * n + 2) && Yco.get(l1 + 1) == Co.get(4 * n + 3)) {
                        point4 = true;
                        break;
                    }
                    if (4 * n + 4 == Co.size()) {
                        break;
                    }

                }
                if (point1 == true && point2 == true && point3 == true && point4 == true) {
                    for (int n = 0; n < Sqlist.size(); n++) {
                        if (Xco.get(k1) == Sqlist.get(4 * n) && Yco.get(l1) == Sqlist.get(4 * n + 1) && Xco.get(k1 + (GRID_SIZE + 1)) == Sqlist.get(4 * n + 2) && Yco.get(l1 + 1) == Sqlist.get(4 * n + 3)) {
                            sqtruth = true;
                            break;
                        } else {
                            sqtruth = false;
                        }
                        if (4 * n + 4 == Sqlist.size()) {
                            break;
                        }
                    }
                    if (sqtruth == false) {
                        flag++;
                        vib.vibrate(300);
                        if (playerturn % 2 == 0) {
                            sq.add(new Square(Xco.get(k1), Yco.get(l1), Xco.get(k1 + (GRID_SIZE + 1)), Yco.get(l1 + 1), paint5, playerturn));
                            Sqlist.add(Xco.get(k1));
                            Sqlist.add(Yco.get(l1));
                            Sqlist.add(Xco.get(k1 + (GRID_SIZE + 1)));
                            Sqlist.add(Yco.get(l1 + 1));
                            sqformed = true;
                            sqformedint++;
                            invalidate();
                        } else {
                            sqtruth = false;
                        }
                    }
                }
                k1++;
                point1 = false;
                point2 = false;
                point3 = false;
                point4 = false;
            }
            l1++;
            k1++;
        }
        line1 = false;
        line2 = false;
        line3 = false;
        line4 = false;
    }


    private float gridX(int col) {
        return CELL1 * (col + 1);
    }

    private float gridY(int row) {
        return (CELL1 * (row + 1) + MARGIN1);
    }

    class Line {
        float startX, startY, stopX, stopY;
        Paint paint1;

        public Line(float startX, float startY, float stopX, float stopY, Paint paint1) {
            this.startX = startX;
            this.startY = startY;
            this.stopX = stopX;
            this.stopY = stopY;
            this.paint1 = paint1;
        }
    }

    class Square {
        float left, top, right, bottom;
        int turn;
        Paint paint3;

        public Square(float left, float top, float right, float bottom, Paint paint3, int turn) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.paint3 = paint3;
            this.turn = turn;
        }
    }
}


































