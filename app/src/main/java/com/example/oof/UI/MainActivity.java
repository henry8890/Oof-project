package com.example.oof.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oof.R;
import com.example.oof.SQLite.DBGetter;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText username;
    EditText password;
    Button signi;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         db = DBGetter.getDB(this);
         username = findViewById(R.id.username);
         password = findViewById(R.id.user_password);
         signi = findViewById(R.id.go);
         register = findViewById(R.id.sign_Up);
    }

    @Override
    protected void onStart() {
        super.onStart();
        signi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAccAvailable(username.getText().toString(), password.getText().toString())){
                    Intent intent = new Intent(MainActivity.this, FinishedClass.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivityMain.class);
                startActivity(intent);
            }
        });
    }


    private boolean isAccAvailable(String use, String pass){
        boolean bool = false;
        SQLiteDatabase db = DBGetter.getDB(this);
        Cursor curse = db.rawQuery("SELECT id from accounts WHERE username = '" + use + "'AND password = '" + pass +  "';", null);
        if (curse.moveToFirst()){
//        int id = curse.getColumnIndex("id");
//        String user = curse.getString(1);
            bool = true;
        }
        curse.close();
        return bool;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
