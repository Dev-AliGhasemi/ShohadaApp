package com.ghasemi.golzarshohada.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Computer Freak on 7/20/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static String DB_NAME = "db_golzar_shohada.db";
    private static String DB_PATH;
    private static int DB_VERSION = 0;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    public DatabaseHelper(Context context) throws IOException {
        super(context,DB_NAME,null,1);
        this.context = context;
        DB_PATH = "/data/data/"+ context.getPackageName()+"/databases/";
        if (existDatabase()) {
            Log.e("database@@@@","database exist");
            if (isOldVesion()) {
                importDatabaseIfNotExist();
                Log.e("database@@@@","database imported");
            }
        } else {
            Log.e("database@@@@","database not exist");
            importDatabaseIfNotExist();
        }
    }
    public void importDatabaseIfNotExist(){
        try {
            sqLiteDatabase = this.getReadableDatabase();
            copyDatabase();
        }catch (Exception e){

        }
    }
    private boolean isOldVesion(){
        SQLiteDatabase sqLiteDatabase;
        int currentVersion=0;
        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READONLY);
            currentVersion = sqLiteDatabase.getVersion();
        }catch (Exception e){

        }
        return DB_VERSION > currentVersion;
    }
    private boolean existDatabase(){
        boolean exist = false;
        try {
            File file = new File(DB_PATH+DB_NAME);
            exist = file.exists();
        }catch (Exception e){
            e.printStackTrace();
        }
        return exist;
    }
    private void copyDatabase() throws IOException {
        InputStream inputStream = this.context.getAssets().open(DB_NAME);
        OutputStream outputStream = new FileOutputStream(DB_PATH+DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer))> 0 ){
            outputStream.write(buffer,0,length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
    public String selectSingleData(String command){
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery(command,null);
        cursor.moveToFirst();
        // Toast.makeText(context.getApplicationContext(),cursor.getString(1),Toast.LENGTH_LONG).show();
        //cursor.close();
        // sqLiteDatabase.close();
        return cursor.getString(0) ;
    }
    public int[] selectFavorite(String command){
        int arrayFavorite[]=null;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery(command,null);
        cursor.moveToFirst();
        arrayFavorite = new int[cursor.getCount()];
        for (int i = 0 ; i<cursor.getCount();i++){
            arrayFavorite[i] = cursor.getInt(0);
            cursor.moveToNext();
        }
        return arrayFavorite;
    }
    public void updateData(String command){
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        sqLiteDatabase.execSQL(command);

    }
    public void getIdFav(String command){
        String arrayData[]=null;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery(command,null);
        cursor.moveToFirst();
        arrayData = new String[cursor.getCount()];
        for (int i = 0 ; i<cursor.getCount();i++){
            arrayData[i] = cursor.getString(0);
            cursor.moveToNext();
        }

    }
    public String[] selectMultiData(String command){
        String arrayData[]=null;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery(command,null);
        cursor.moveToFirst();
        arrayData = new String[cursor.getCount()];
        for (int i = 0 ; i<cursor.getCount();i++){
            arrayData[i] = cursor.getString(0);
            cursor.moveToNext();
        }
        // Toast.makeText(context.getApplicationContext(),cursor.getString(1),Toast.LENGTH_LONG).show();
        //cursor.close();
        // sqLiteDatabase.close();
        return arrayData;
    }
    public void closeDatabase(){
        cursor.close();
        sqLiteDatabase.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
