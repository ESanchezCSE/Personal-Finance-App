package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class accountSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);
    }

    public void proceedToLoanSetup(View view) {
        Intent intent = new Intent(this, loanSetup.class);
        startActivity(intent);
    }
}
