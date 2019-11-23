package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class mainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void proceedToPersonalGoals(View view) {
        Intent intent1 = new Intent(this, personalGoals.class);
        startActivity(intent1);
    }

    public void proceedToAddTransaction(View view) {
        Intent intent2 = new Intent(this, addTransaction.class);
        startActivity(intent2);
    }

    public void proceedToLoanPage(View view) {
        Intent intent3 = new Intent(this, loanPage.class);
        startActivity(intent3);
    }

    public void proceedToAccountReport(View view) {
        Intent intent4 = new Intent(this, accountReport.class);
        startActivity(intent4);
    }

    public void proceedToAdviceColumn(View view) {
        Intent intent5 = new Intent(this, adviceColumn.class);
        startActivity(intent5);
    }

   public void proceedToAccountSetup(View view) {
        Intent intent6 = new Intent(this, accountSetup.class);
        startActivity(intent6);
    }

}
