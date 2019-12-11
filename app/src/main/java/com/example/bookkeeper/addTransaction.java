package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addTransaction extends AppCompatActivity {

    Button btn_1,btn_2,btn_3, btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        btn_1 = (Button)findViewById(R.id.addIncomeBtn);
        btn_2 = (Button)findViewById(R.id.addExpensesBtn);
        btn_3 = (Button)findViewById(R.id.addLoanBtn);
        btn_4 = (Button)findViewById(R.id.backToMainPageBtn);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(addTransaction.this,addIncome.class);
                startActivity(intent1);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(addTransaction.this,addExpense.class);
                startActivity(intent2);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(addTransaction.this,addLoan.class);
                startActivity(intent3);
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(addTransaction.this,mainPage.class);
                startActivity(intent3);
            }
        });
    }
}
