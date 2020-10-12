package com.example.oof.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.oof.R;
import com.example.oof.SQLite.DBGetter;

import java.util.UUID;

public class RegisterActivityMain extends Activity {
    SQLiteDatabase db;
    private TextView superText;
    private String uuid;
    private Button generate;
    private EditText username;
    private EditText inputPass;
    private Button reged;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        db = DBGetter.getDB(this);
        superText = findViewById(R.id.UUID);
        generate = findViewById(R.id.create);
        username = findViewById(R.id.username);
        inputPass = findViewById(R.id.userpass);
        reged = findViewById(R.id.reg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        generatorSetup();
    }

    @SuppressLint("SetTextI18n")
    private void generatorSetup(){
        uuid = UUID.randomUUID().toString();
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                superText.setText("" + uuid);
            }
        });
        reged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entered();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void entered(){
        String user = username.getText().toString();
        String pass = inputPass.getText().toString();
        if (accountAvailable()){
            register(user, pass);
        } else {
            superText.setText("Account username or password taken.");
        }
    }

    private void register (String usedname, String usedpass){
        SQLiteDatabase db = DBGetter.getDB(this);
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("Username", usedname);
        mContentValues.put("Password", usedpass);
        String id = UUID.randomUUID().toString();
        mContentValues.put("id", id);
        db.insert("accounts", null, mContentValues);
    }

    private boolean accountAvailable(){
        boolean bool;
        String userstring = username.getText().toString();
        SQLiteDatabase db = DBGetter.getDB(this);
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM accounts WHERE username = '" + userstring + "';", null);
        mCursor.moveToFirst();
        bool = mCursor.isNull(0);
        return bool;
   }

}
