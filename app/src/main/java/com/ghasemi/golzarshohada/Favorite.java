package com.ghasemi.golzarshohada;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.ghasemi.golzarshohada.RecyclerViewAdapter.RecyclerAdapterForFavorite;
import com.ghasemi.golzarshohada.database.DatabaseHelper;

import java.io.IOException;

/**
 * Created by Computer Freak on 7/26/2018.
 */

public class Favorite extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_favorite);
        try {
             databaseHelper = new DatabaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
        String [] arrayFname = databaseHelper.selectMultiData("select Fname from tbl_shohada where favorite=1");
        String [] arrayLname = databaseHelper.selectMultiData("select Lname from tbl_shohada where favorite=1");
        String [] arrayID = databaseHelper.selectMultiData("select ID from tbl_shohada where favorite=1");
        recyclerView.setLayoutManager(new LinearLayoutManager(this,1,false));
        recyclerView.setAdapter(new  RecyclerAdapterForFavorite(this,arrayFname,arrayLname,arrayID));
        recyclerView.
    }
    public void init(){
        recyclerView = (RecyclerView) findViewById(R.id.act_5_recyclerFavorite);
    }
}
