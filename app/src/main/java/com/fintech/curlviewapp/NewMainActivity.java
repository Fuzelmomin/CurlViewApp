package com.fintech.curlviewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class NewMainActivity extends AppCompatActivity {

    public static CurlView curlView;

    int[] mBitmapIds= {R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six,
            R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten};

    public static int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        /*if (getLastNonConfigurationInstance() != null) {
            index = (Integer) getLastNonConfigurationInstance();
        }*/
        curlView = findViewById(R.id.curl_view1);

        curlView.setPageProvider(new PageProvider(NewMainActivity.this,mBitmapIds));
        curlView.setPageProvider(new NewPageProvider());
        curlView.setViewMode(CurlView.SHOW_TWO_PAGES);
        curlView.setMargins(.1f, .05f, .1f, .05f);
        curlView.setCurrentIndex(index);
        curlView.setBackgroundColor(0xFF202830);

        // This is something somewhat experimental. Before uncommenting next
        // line, please see method comments in CurlView.
        // mCurlView.setEnableTouchPressure(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        curlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        curlView.onResume();
    }




    class NewPageProvider implements CurlView.PageProvider {


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
                d = ContextCompat.getDrawable(NewMainActivity.this,mBitmapIds[index]);
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

            switch (index){

                case 0: {
                    Bitmap front = loadBitmap(width, height, 0);
                    Bitmap back = loadBitmap(width, height, 1);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }
                case 1: {
                    Bitmap front = loadBitmap(width, height, 2);
                    Bitmap back = loadBitmap(width, height, 3);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }

                case 2: {
                    Bitmap front = loadBitmap(width, height, 4);
                    Bitmap back = loadBitmap(width, height, 5);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }

                case 3: {
                    Bitmap front = loadBitmap(width, height, 6);
                    Bitmap back = loadBitmap(width, height, 7);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }
                case 4: {
                    Bitmap front = loadBitmap(width, height, 8);
                    Bitmap back = loadBitmap(width, height, 9);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }

                case 5: {
                    Bitmap front = loadBitmap(width, height, 10);
                    Bitmap back = loadBitmap(width, height, 11);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }

                case 6: {
                    Bitmap front = loadBitmap(width, height, 12);
                    Bitmap back = loadBitmap(width, height, 13);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }

                case 7: {
                    Bitmap front = loadBitmap(width, height, 14);
                    Bitmap back = loadBitmap(width, height, 15);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);

                    Log.d("INDEXPOINT","case0");
                    break;
                }
            }

        }

    }


}