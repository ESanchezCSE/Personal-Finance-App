package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class signUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
    }

    public void proceedToAccountSetup(View view) {
        Intent intent = new Intent(this, accountSetup.class);
        startActivity(intent);
    }
}
