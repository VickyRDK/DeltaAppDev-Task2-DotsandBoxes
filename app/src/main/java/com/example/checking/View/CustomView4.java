package com.example.checking.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
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

import static com.example.checking.Main4BotE.gs3;

public class CustomView4 extends View {

    private Paint mPaintCircle;
    private float mCircleX, mCircleY;
    private ArrayList<Line> lines = new ArrayList<Line>();
    private ArrayList<Square> sq = new ArrayList<>();
    private ArrayList<Float> Xco = new ArrayList<>();
    private ArrayList<Float> Yco = new ArrayList<>();
    private ArrayList<Float> Co = new ArrayList<>();
    private ArrayList<Float> Sqlist = new ArrayList<>();
    private ArrayList<Float> linelist = new ArrayList<>();
    private ArrayList<Integer> linenum = new ArrayList<>();
    private ArrayList<Line> botlines = new ArrayList<>();
    private float mCircleRadius = 20f;
    private Boolean point1 = false;
    private Boolean point2 = false;
    private Boolean point3 = false;
    private Boolean point4 = false;
    private Boolean sqtruth = false;
    private Boolean sqformed = false;
    private int GRID_SIZE = gs3;
    int countscore1;
    int countscore2;
    private int flag = 0;
    private float CELL1;
    private float MARGIN1;
    Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
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

    public CustomView4(Context context) {
        super(context);
        init(null);
    }

    public CustomView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        GRID_SIZE=gs3+1;


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
        if (sq.size() == (gs3 * gs3)) {
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
                                Log.d("okay", String.valueOf(playerturn));
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
                                Log.d("okay", String.valueOf(playerturn));
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
                                Log.d("okay", String.valueOf(playerturn));
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
                                Log.d("okay", String.valueOf(playerturn));
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


                if (flag1 == false) {
                    float x = event.getX();
                    float y = event.getY();

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

        int k = 0;
        sqformed = false;
        for (int i = 0; i < (GRID_SIZE); i++) {
            for (int j = 0; j < (GRID_SIZE - 1); j++) {
                if ((Xco.get(k)) < x && (Xco.get(k + 1) > x)) {
                    if ((Yco.get(i) - 20) < y && (Yco.get(i) + 20) > y) {
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
                k++;
            }
            k++;
        }
        int l = 0;
        for (int i = 0; i < (GRID_SIZE - 1); i++) {
            for (int j = 0; j < (GRID_SIZE); j++) {
                if ((Xco.get(j) - 20) < x && (Xco.get(j) + 20) > x) {
                    if ((Yco.get(l)) < y && (Yco.get(l + 1)) > y) {
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
            l++;
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


































