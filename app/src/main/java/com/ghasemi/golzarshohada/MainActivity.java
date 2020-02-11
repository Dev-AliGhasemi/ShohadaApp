package com.ghasemi.golzarshohada;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghasemi.golzarshohada.Font.SetFonts;

public class MainActivity extends Activity {
    TextView textGolzar,textShoada,textQom,textVirayesh,textSalavat;
    Typeface font;
    Animation animationMainLogo,animationPublic;
    ImageView imgLogo,imgKhon;
    SetFonts setFonts ;
    int width;
    int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // برای گرفتن اندازه یک صفحه
//        Display display = getWindowManager().getDefaultDisplay();
//        Point point = new Point();
//        display.getSize(point);
//        width = point.x;
//        height = point.y;
//        imgLogo.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , height /3));
        setContentView(R.layout.activity_main);
        setFonts = new SetFonts(MainActivity.this,"fonts/dirooz.ttf");
        init();
        setFont();
        setAnimation();
        Thread timer = new Thread(){
            public void run()
            {
                try {
                    sleep(6000);
                    Intent intent = new Intent(MainActivity.this,MainPage.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
    private void init() {
        textGolzar = (TextView) findViewById(R.id.act_1_textGolzar);
        textQom = (TextView) findViewById(R.id.act_1_textQom);
        textShoada = (TextView) findViewById(R.id.act_1_textShohada);
        textSalavat = (TextView) findViewById(R.id.act_1_textSalavat);
        textVirayesh = (TextView) findViewById(R.id.act_1_textVirayesh);
        imgLogo = (ImageView) findViewById(R.id.act_1_logo);
        imgKhon = (ImageView) findViewById(R.id.act_1_khon);
    }
    private void setFont() {
        //font = Typeface.createFromAsset(getAssets(),"fonts/dirooz.ttf");
        //textGolzar.setTypeface(font);
        setFonts.setFont("TextView",textGolzar);
        setFonts.setFont("TextView",textShoada);
        setFonts.setFont("TextView",textQom);
        setFonts.setFont("TextView",textVirayesh);
        setFonts.setFont("TextView",textSalavat);
    }
    private void setAnimation() {
        animationMainLogo = AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        animationPublic = AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        imgLogo.setAnimation(animationMainLogo);
        textGolzar.setAnimation(animationPublic);
        textShoada.setAnimation(animationPublic);
        textQom.setAnimation(animationPublic);
        imgKhon.setAnimation(animationPublic);
        textVirayesh.setAnimation(animationPublic);
        textSalavat.setAnimation(animationPublic);
    }

}
