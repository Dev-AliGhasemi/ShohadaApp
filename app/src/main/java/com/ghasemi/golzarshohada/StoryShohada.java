package com.ghasemi.golzarshohada;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.ghasemi.golzarshohada.Font.SetFonts;
import com.ghasemi.golzarshohada.database.DatabaseHelper;

import java.io.IOException;


public class StoryShohada extends AppCompatActivity {
    TextView textTitle,textStory;
    private SetFonts setFonts;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_shohada);
        init();
        setFont();
        try {
            databaseHelper = new DatabaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textTitle.setText(getIntent().getStringExtra("textTitle"));
        textStory.setText(databaseHelper.selectSingleData("select Story from tbl_shohada where id = "+getIntent().getStringExtra("id")));
    }
    private void init(){
        textTitle = (TextView) findViewById(R.id.act_4_textTitle);
        textStory = (TextView) findViewById(R.id.act_4_textStory);
        textStory.setMovementMethod(new ScrollingMovementMethod());
    }
    private void setFont(){
        setFonts = new SetFonts(StoryShohada.this,"fonts/iran_sans.otf");
        setFonts.setFont("TextView",textTitle);
        setFonts.setFont("TextView",textStory);
    }
}
