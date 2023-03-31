package com.example.digiagro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {

        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT , email TEXT, address TEXT, blood TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {

        DB.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertData(String name, String email, String address, String blood) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("address", address);
        contentValues.put("blood", blood);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String name, String email, String address, String blood) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("address", address);
        contentValues.put("blood", blood);
        Cursor curser = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (curser.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean delete(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor curser = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (curser.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor curser = DB.rawQuery("Select * from Userdetails", null);
        return curser;
    }
}
