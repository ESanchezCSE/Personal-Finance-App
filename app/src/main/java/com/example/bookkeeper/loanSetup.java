package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loanSetup extends AppCompatActivity {
    EditText editInterestAmount1;
    EditText editInterestRate1;
    EditText editInterestAmount2;
    EditText editInterestRate2;
    EditText editInterestAmount3;
    EditText editInterestRate3;
    EditText editInterestAmount4;
    EditText editInterestRate4;
    EditText editInterestAmount5;
    EditText editInterestRate5;
    EditText editInterestAmount6;
    EditText editInterestRate6;
    Button buttonSubmitLoan;
    Button skipBtn;

    String uid;

    DatabaseReference databaseIncome;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_setup);

        databaseIncome = FirebaseDatabase.getInstance().getReference();

        editInterestAmount1 = (EditText) findViewById(R.id.editInterestAmount1);
        editInterestRate1 = (EditText) findViewById(R.id.editInterestRate1);
        editInterestAmount2 = (EditText) findViewById(R.id.editInterestAmount2);
        editInterestRate2 = (EditText) findViewById(R.id.editInterestRate2);
        editInterestAmount3 = (EditText) findViewById(R.id.editInterestAmount3);
        editInterestRate3 = (EditText) findViewById(R.id.editInterestRate3);
        editInterestAmount4 = (EditText) findViewById(R.id.editInterestAmount4);
        editInterestRate4 = (EditText) findViewById(R.id.editInterestRate4);
        editInterestAmount5 = (EditText) findViewById(R.id.editInterestAmount5);
        editInterestRate5 = (EditText) findViewById(R.id.editInterestRate5);
        editInterestAmount6 = (EditText) findViewById(R.id.editInterestAmount6);
        editInterestRate6 = (EditText) findViewById(R.id.editInterestRate6);
        buttonSubmitLoan = (Button) findViewById(R.id.buttonSubmitLoan);
        skipBtn = (Button) findViewById(R.id.skipBtn);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
            //Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();//Error checking
        }// [END get_user_profile]

        buttonSubmitLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLoan(uid);
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLoan(uid);
            }
        });
    }

    private void addLoan(String uid){
        double aLoanTotal;
        double interestAmount1;
        double interestRate1;
        double interestAmount2;
        double interestRate2;
        double interestAmount3;
        double interestRate3;
        double interestAmount4;
        double interestRate4;
        double interestAmount5;
        double interestRate5;
        double interestAmount6;
        double interestRate6;

        if(!TextUtils.isEmpty(editInterestAmount1.getText())){//Checks if edit text box is empty.
            interestAmount1 = Double.parseDouble(editInterestAmount1.getText().toString());
        } else{
            interestAmount1 = 0;
        }

        if(!TextUtils.isEmpty(editInterestRate1.getText())){//Checks if edit text box is empty.
            interestRate1 = Double.parseDouble(editInterestRate1.getText().toString());
        } else{
            interestRate1 = 0;
        }

        if(!TextUtils.isEmpty(editInterestAmount2.getText())){//Checks if edit text box is empty.
            interestAmount2 = Double.parseDouble(editInterestAmount2.getText().toString());
        } else{
            interestAmount2 = 0;
        }

        if(!TextUtils.isEmpty(editInterestRate2.getText())){//Checks if edit text box is empty.
            interestRate2 = Double.parseDouble(editInterestRate2.getText().toString());
        } else{
            interestRate2 = 0;
        }

        if(!TextUtils.isEmpty(editInterestAmount3.getText())){//Checks if edit text box is empty.
            interestAmount3 = Double.parseDouble(editInterestAmount3.getText().toString());
        } else{
            interestAmount3 = 0;
        }

        if(!TextUtils.isEmpty(editInterestRate3.getText())){//Checks if edit text box is empty.
            interestRate3 = Double.parseDouble(editInterestRate3.getText().toString());
        } else{
            interestRate3 = 0;
        }

        if(!TextUtils.isEmpty(editInterestAmount4.getText())){//Checks if edit text box is empty.
            interestAmount4 = Double.parseDouble(editInterestAmount4.getText().toString());
        } else{
            interestAmount4 = 0;
        }

        if(!TextUtils.isEmpty(editInterestRate4.getText())){//Checks if edit text box is empty.
            interestRate4 = Double.parseDouble(editInterestRate4.getText().toString());
        } else{
            interestRate4 = 0;
        }

        if(!TextUtils.isEmpty(editInterestAmount5.getText())){//Checks if edit text box is empty.
            interestAmount5 = Double.parseDouble(editInterestAmount5.getText().toString());
        } else{
            interestAmount5 = 0;
        }

        if(!TextUtils.isEmpty(editInterestRate5.getText())){//Checks if edit text box is empty.
            interestRate5 = Double.parseDouble(editInterestRate5.getText().toString());
        } else{
            interestRate5 = 0;
        }

        if(!TextUtils.isEmpty(editInterestAmount6.getText())){//Checks if edit text box is empty.
            interestAmount6 = Double.parseDouble(editInterestAmount6.getText().toString());
        } else{
            interestAmount6 = 0;
        }

        if(!TextUtils.isEmpty(editInterestRate6.getText())){//Checks if edit text box is empty.
            interestRate6 = Double.parseDouble(editInterestRate6.getText().toString());
        } else{
            interestRate6 = 0;
        }

        aLoanTotal = interestAmount1 + interestAmount2 +interestAmount3 + interestAmount4 + interestAmount5 + interestAmount6;

        Loan loan = new Loan(aLoanTotal, interestAmount1, interestRate1, interestAmount2, interestRate2, interestAmount3, interestRate3, interestAmount4, interestRate4, interestAmount5, interestRate5, interestAmount6, interestRate6);

        databaseIncome.child("users").child(uid).child("loans").setValue(loan);
        if(aLoanTotal != 0) {
            Toast.makeText(this, "Loans added", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Skipped Loan Setup", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, mainPage.class);
        startActivity(intent);
    }

}
