package fr.valenciennes.university.surakartagame;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {


    private static final int LINE_THICK = 5;

    private static final int ELT_MARGIN = 20;

    private static final int ELT_STROKE_WIDTH = 15;

    private int width, height, eltW, eltH;

    private Paint gridPaint, oPaint, xPaint;

    private GameEngine gameEngine;

    private OnePlayerActivity activity;

    private int[] select = new int[2];
    private boolean act = true;


    public BoardView(Context context) {

        super(context);

    }


    public BoardView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        gridPaint = new Paint();

        oPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        oPaint.setColor(Color.BLACK);

        oPaint.setStyle(Paint.Style.FILL);

        oPaint.setStrokeWidth(ELT_STROKE_WIDTH);

        xPaint = new Paint(oPaint);

        xPaint.setColor(Color.GREEN);

        xPaint.setStyle(Paint.Style.FILL);

    }


    public void setOnePlayerActivity(OnePlayerActivity a) {

        activity = a;

    }


    public void setGameEngine(GameEngine g) {

        gameEngine = g;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        height = View.MeasureSpec.getSize(heightMeasureSpec);

        width = View.MeasureSpec.getSize(widthMeasureSpec);

        eltW = (width - LINE_THICK) / 6;

        eltH = (height - LINE_THICK) / 6;


        setMeasuredDimension(width, height);

    }


    @Override

    protected void onDraw(Canvas canvas) {

        drawBoard(canvas);

    }



    @Override

    public boolean onTouchEvent(MotionEvent event) {


        if (!act && !gameEngine.isEnded() && event.getAction() == MotionEvent.ACTION_DOWN) {

            if(gameEngine.isSelectionOk(select[0],select[1])) {

                int x = (int) (event.getX() / eltW);

                int y = (int) (event.getY() / eltH);

                //Vérification de chaque condition selon els règle du jeu

                if(gameEngine.isFree(x,y)){

                    if(gameEngine.DeplacementPossible(select[0], select[1], x, y)) {

                        gameEngine.Deplacement(select[0], select[1], x, y);

                    }

                }


            }

            act = true;

            invalidate();

        } else{
                int x = (int) (event.getX() / eltW);

                int y = (int) (event.getY() / eltH);


                select[0] = x;
                select[1] = y;


            act = false;

            invalidate();

        }


        if(gameEngine.checkEnd()){
            activity.gameEnded(gameEngine.currentPlayer);
        }

        return super.onTouchEvent(event);

    }

    private void drawBoard(Canvas canvas) {

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {

                drawElt(canvas, gameEngine.elt(i, j), i, j);

            }

        }

    }


    private void drawElt(Canvas canvas, char c, int x, int y) {

        if (c == 'O') {

            float cx = (eltW * x) + eltW / 2;

            float cy = (eltH * y) + eltH / 2;


            canvas.drawCircle(cx, cy, Math.min(eltW, eltH) / 2 - ELT_MARGIN , oPaint);

        } else if (c == 'X') {

            float cx = (eltW * x) + eltW / 2;

            float cy = (eltH * y) + eltH / 2;


            canvas.drawCircle(cx, cy, Math.min(eltW, eltH) / 2 - ELT_MARGIN , xPaint);



        }

    }


}