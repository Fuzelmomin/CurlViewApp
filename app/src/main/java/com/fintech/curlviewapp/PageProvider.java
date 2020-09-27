package com.fintech.curlviewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class PageProvider implements CurlView.PageProvider {

    // Bitmap resources.
    private int[] mBitmapIds;
    Context contexts;


    public PageProvider(Context contexts, int[] mBitmapIds) {
        this.contexts = contexts;
        this.mBitmapIds = mBitmapIds;
    }

    @Override
    public int getPageCount() {
        return mBitmapIds.length;
    }

    private Bitmap loadBitmap(int width, int height, int index) {
        Bitmap b = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        b.eraseColor(0xFFFFFFFF);
        Canvas c = new Canvas(b);
      //  Drawable d = getDrawable(mBitmapIds[index]);

        Drawable d = null;
        try{
            d = ContextCompat.getDrawable(contexts,mBitmapIds[index]);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        /*int margin = 7;
        int border = 3;
        Rect r = new Rect(margin, margin, width - margin, height - margin);*/

        int border = 3;
        Rect r = new Rect(0, 0, width , height);

        int imageWidth = r.width() - (border * 2);
        int imageHeight = imageWidth * d.getIntrinsicHeight()
                / d.getIntrinsicWidth();
        if (imageHeight > r.height() - (border * 2)) {
            imageHeight = r.height() - (border * 2);
            imageWidth = imageHeight * d.getIntrinsicWidth()
                    / d.getIntrinsicHeight();
        }

        r.left += ((r.width() - imageWidth) / 2) - border;
        r.right = r.left + imageWidth + border + border;
        r.top += ((r.height() - imageHeight) / 2) - border;
        r.bottom = r.top + imageHeight + border + border;

        Paint p = new Paint();
        p.setColor(0xFFC0C0C0);
        c.drawRect(r, p);
        r.left += border;
        r.right -= border;
        r.top += border;
        r.bottom -= border;

        d.setBounds(r);
        d.draw(c);

        return b;
    }

    @Override
    public void updatePage(CurlPage page, int width, int height, int index) {

        switch (index) {

            case 0: {

                Bitmap front = loadBitmap(width,height,index);
                Bitmap back = loadBitmap(width,height,index+1);
                page.setTexture(front, CurlPage.SIDE_FRONT);
                page.setTexture(back, CurlPage.SIDE_BACK);

                NewMainActivity.index = index+1;

                break;
            }

            default:{

                Bitmap front = loadBitmap(width,height,index+1);
                Bitmap back = loadBitmap(width,height,index+2);
                page.setTexture(front, CurlPage.SIDE_FRONT);
                page.setTexture(back, CurlPage.SIDE_BACK);

                NewMainActivity.index = index+2;

            }
        }




    }

}
