package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendLogIn(View view) {
        Intent intent1 = new Intent(this, mainPage.class);
        startActivity(intent1);
    }

    public void sendSignUp(View view) {
        Intent intent2 = new Intent(this, signUpPage.class);
        startActivity(intent2);
    }

}
