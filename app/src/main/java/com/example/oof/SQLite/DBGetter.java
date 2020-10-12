package com.example.oof.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGetter {
    static SQLiteDatabase db;
    static DBhelper dBhelper;
    public static SQLiteDatabase getDB(Context conable){
        dBhelper = new DBhelper(conable, "OOF.sqlite", null, 1);
        db = dBhelper.getWritableDatabase();
        return db;
    }
}
