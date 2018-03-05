package com.sad490.smartscrape;

import android.support.v7.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hp on 2018/3/5.
 */

public class BaseActivity extends AppCompatActivity {

    View decorView;
    int screenWidth;//屏宽

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decorView=getWindow().getDecorView();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth=metrics.widthPixels;

    }

    float startX,startY,endX,endY,distanceX,distanceY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                startY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX=event.getX();
                endY=event.getY();
                distanceX=endX-startX;
                distanceY=Math.abs(endY-startY);
                //1.判断手势右滑  2.横向滑动的距离要大于竖向滑动的距离
                if(endX-startX>0&&distanceY<distanceX){
                    decorView.setX(distanceX);
                }
                break;
            case MotionEvent.ACTION_UP:
                endX=event.getX();
                distanceX=endX-startX;
                endY=event.getY();
                distanceY=Math.abs(endY-startY);
                //1.判断手势右滑  2.横向滑动的距离要大于竖向滑动的距离 3.横向滑动距离大于屏幕三分之一才能finish
                if(endX-startX>0&&distanceY<distanceX&&distanceX>screenWidth/3){
                    moveOn(distanceX);
                }
                //1.判断手势右滑  2.横向滑动的距离要大于竖向滑动的距离 但是横向滑动距离不够则返回原位置
                else if(endX-startX>0&&distanceY<distanceX){
                    backOrigin(distanceX);
                }else{
                    decorView.setX(0);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 返回原点
     * @param distanceX 横向滑动距离
     */
    private void backOrigin(float distanceX){
        ObjectAnimator.ofFloat(decorView,"X",distanceX,0).setDuration(300).start();
    }
    /**
     * 划出屏幕
     * @param distanceX 横向滑动距离
     */
    private void moveOn(float distanceX){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(distanceX,screenWidth);
        valueAnimator.setDuration(300);
        valueAnimator.start();

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                decorView.setX((Float) animation.getAnimatedValue());
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}