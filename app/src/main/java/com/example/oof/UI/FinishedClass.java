package com.example.oof.UI;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.oof.SQLite.DBGetter;

public class FinishedClass extends Activity {
    @Deprecated
    private void updateAccount(String username, String password, String newedU, String newedP){
        SQLiteDatabase db = DBGetter.getDB(this);
        ContentValues values = new ContentValues();
        values.put("username", newedU);
        values.put("password", newedP);
        db.update("accounts", values, "username = '" + username + "' AND password = '" + password + "';", null);
    }

    private void updateByID(String id, String username, String Password, String newUsername, String newPassword){
        SQLiteDatabase db = DBGetter.getDB(this);
        ContentValues userDetails = new ContentValues();
        userDetails.put("username", newUsername);
        userDetails.put("password", newPassword);
        db.update("accounts", userDetails, "id = '" + id + "'", null);
    }
}
