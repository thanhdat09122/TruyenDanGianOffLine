package com.example.mypc.truyenoffline.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import com.example.mypc.truyenoffline.database.preferences.MyPreferences;


public class AutoScrollingText extends android.support.v7.widget.AppCompatTextView {

    private static final float DEFAULT_SPEED = 40.0f;

    public Scroller scroller;
    public float speed = DEFAULT_SPEED;
    public boolean continuousScrolling = true;
    private MyPreferences mySharedference;

    public AutoScrollingText(Context context) {
        super(context);
      scrollerInstance(context);
    }

    public AutoScrollingText(Context context, AttributeSet attributes) {
        super(context, attributes);
       scrollerInstance(context);
    }

    public void scrollerInstance(Context context) {
        scroller = new Scroller(context, new LinearInterpolator());
        setScroller(scroller);
        mySharedference = MyPreferences.getInstance(context);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (scroller.isFinished() && mySharedference.getAutoScroll()) {
           scroll();
        }
    }

    public void scroll() {
        int viewHeight = getHeight();
        int visibleHeight = viewHeight - getPaddingBottom() - getPaddingTop();
        int lineHeight = getLineHeight();

        int offset = -1 * visibleHeight;
        int distance = visibleHeight + getLineCount() * lineHeight;
        int duration = (int) (distance * speed);


        scroller.startScroll(0, 0, 0, distance, duration);
    }



    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setContinuousScrolling(boolean continuousScrolling) {
        this.continuousScrolling = continuousScrolling;
    }

    public boolean isContinuousScrolling() {
        return continuousScrolling;
    }

}