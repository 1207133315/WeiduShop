package com.bw.weidushop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bw.weidushop.R;


public class Buy extends View {

    private Paint paint;
    int width;
    int height;
    public Buy(Context context) {
        this(context,null);
    }

    public Buy(Context context, AttributeSet attrs) {
        this(context, attrs,-1);

    }

    public Buy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getWidth();
        height=getHeight();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,paint);
        canvas.drawRect(getWidth()/2,0,getWidth(),getHeight(),paint);
        Log.d("Buy", "width:" + getWidth());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.common_btn_buy);
        Rect rect = new Rect(0, 0, getWidth()/4*3, getHeight()/4*3);
        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(getWidth()/3,getHeight()/4,getWidth()/8*7,getHeight()/4*3);

        // 绘制图片
        canvas.drawBitmap(bitmap,rect,dst,null);


    }


}
