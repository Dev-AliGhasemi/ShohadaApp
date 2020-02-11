package com.ghasemi.golzarshohada.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Helper extends SQLiteOpenHelper {

    private Context context;
    private String DB_PATH;
    private static int DB_VER = 1;
    private static String DB_NAME = "database/db_golzar_shohada.db";
    private SQLiteDatabase sqLiteDatabase;

    public Helper(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH = "/data/data/" + this.context.getPackageName() + "/databases/";
        boolean dbExist = checkDatabase();
        if (dbExist) {
            boolean isOldDb = isOldVersion();
            if (isOldDb) {
                /*
                                    //برای جداولی که باید از دیتابیس قبلی به دیتا بیس جدید کپی بشوند
                Cursor cu = sqLiteDatabase.rawQuery("select * from acts_ha", null);
                */
                createDatabase();
                /*
                //برای جداولی که باید از دیتابیس قبلی به دیتا بیس جدید کپی بشوند
                for (int i = 0; i < cu.getCount(); i++) {
                    if (cu.moveToPosition(i)) {
                        int id = cu.getInt(0);
                        String title = cu.getString(1);
                        String text = cu.getString(2);
                        sqLiteDatabase.execSQL("INSERT INTO acts_ha VALUES ( " + id + " ,'" + title + "','" + text + "')");
                    }
                }*/
            }
        }
        else {
            System.out.println("Database doesn't exist");
            createDatabase();
        }
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            }
            catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        boolean dbCheck = false;
        try {
            String path = DB_PATH + DB_NAME;
            File file = new File(path);
            dbCheck = file.exists();
        }
        catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return dbCheck;
    }

    private boolean isOldVersion() {
        int currentVersion = 0;
        SQLiteDatabase myDataBaseCheck;
        try {
            String path = DB_PATH + DB_NAME;
            myDataBaseCheck = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            currentVersion = myDataBaseCheck.getVersion();
            myDataBaseCheck.close();
        }
        catch (SQLiteException e) {
            System.out.println("Database doesn't exist yet");
        }
        return (DB_VER > currentVersion);
    }

    private void copyDatabase() throws IOException {
        //Open your local db as the input stream
        InputStream inputStream = context.getAssets().open("database/" + DB_NAME);

        // Path to the just created empty db
        String outputFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream outputStream = new FileOutputStream(outputFileName);

        // transfer byte to input file to output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        //Close the streams
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void open() throws SQLException, IOException {
        String path = DB_PATH + DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
        super.close();
    }

    public Integer getCoutFielde() {
        Cursor cu = null;
        try {
            cu = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM acts_ha ", null);
            int s = cu.getCount();
            return s;
        }
        finally {
            if (cu != null) {
                cu.close();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
