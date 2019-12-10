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

public class accountSetup extends AppCompatActivity {
    EditText editIncome;
    EditText editSavings;
    EditText editRothIRA;
    EditText editMatch401Amount;
    EditText editRent;
    EditText editElectricity;
    EditText editWater;
    EditText editGarbage;
    EditText editInsurance;
    EditText editGroceries;
    EditText editChildCare;
    EditText editPhone;
    EditText editInternet;
    EditText editGym;
    EditText editStreaming;
    EditText editSubscription;
    Spinner spinnerOccuring;
    Button buttonSubmitIncome;

    String uid;

    DatabaseReference databaseIncome;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);

        databaseIncome = FirebaseDatabase.getInstance().getReference();

        editIncome = (EditText) findViewById(R.id.editIncome);
        editSavings = (EditText) findViewById(R.id.editSavings);
        editRothIRA = (EditText) findViewById(R.id.editRothIRA);
        editMatch401Amount = (EditText) findViewById(R.id.editMatch401Amount);
        editRent = (EditText) findViewById(R.id.editRent);
        editElectricity = (EditText) findViewById(R.id.editElectricity);
        editWater = (EditText) findViewById(R.id.editWater);
        editGarbage = (EditText) findViewById(R.id.editGarbage);
        editInsurance = (EditText) findViewById(R.id.editInsurance);
        editGroceries = (EditText) findViewById(R.id.editGroceries);
        editChildCare = (EditText) findViewById(R.id.editChildCare);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editInternet = (EditText) findViewById(R.id.editInternet);
        editGym = (EditText) findViewById(R.id.editGym);
        editStreaming = (EditText) findViewById(R.id.editStreaming);
        editSubscription = (EditText) findViewById(R.id.editSubscription);
        spinnerOccuring = (Spinner) findViewById(R.id.spinnerOccuring);
        buttonSubmitIncome = (Button) findViewById(R.id.buttonSubmitIncome);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
            //Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();//Error checking
        }// [END get_user_profile]

        buttonSubmitIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpenses(uid);
                addIncome(uid);
            }
        });

    }

    private void addIncome(String uid){
        double incomeFinal;
        double savings;
        double rothIRAAmount;
        double match401Amount;
        double incomeAmount;// = Double.parseDouble(editIncome.getText().toString());
        String reoccurance = spinnerOccuring.getSelectedItem().toString();

        if(!TextUtils.isEmpty(editIncome.getText())){
            incomeAmount = Double.parseDouble(editIncome.getText().toString());
            switch(reoccurance){
                case "Weekly":
                    incomeFinal = incomeAmount * 4;
                    break;
                case "Bi-Weekly":
                    incomeFinal = incomeAmount * 2;
                    break;
                case "Monthly":
                    incomeFinal = incomeAmount;
                    break;
                case "Yearly":
                    incomeFinal = incomeAmount / 12;
                    break;
                default:
                    incomeFinal = -1;
            }

            if(!TextUtils.isEmpty(editSavings.getText())){
                savings = Double.parseDouble(editSavings.getText().toString());
            } else{
                savings = 0;
            }

            if(!TextUtils.isEmpty(editRothIRA.getText())){
                rothIRAAmount = Double.parseDouble(editRothIRA.getText().toString());
            } else{
                rothIRAAmount = 0;
            }

            if(!TextUtils.isEmpty(editMatch401Amount.getText())){
                match401Amount = Double.parseDouble(editMatch401Amount.getText().toString());
            } else{
                match401Amount = 0;
            }

            Income income = new Income(incomeFinal, savings, rothIRAAmount, match401Amount);

            databaseIncome.child("users").child(uid).child("income").setValue(income);

            Toast.makeText(this, "Income added", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, loanSetup.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, "You should enter a valid value for income", Toast.LENGTH_LONG).show();
        }
    }

    private void addExpenses(String uid){
        double rent;
        double electricity;
        double water;
        double garbage;
        double insurance;
        double groceries;
        double childCare;
        double phone;
        double internet;
        double gym;
        double streaming;
        double subscription;
        double total;

        if(!TextUtils.isEmpty(editRent.getText())){//Checks if edit text box is empty.
            rent = Double.parseDouble(editRent.getText().toString());
        } else{
            rent = 0;
        }

        if(!TextUtils.isEmpty(editElectricity.getText())){//Checks if edit text box is empty.
            electricity = Double.parseDouble(editElectricity.getText().toString());
        } else{
            electricity = 0;
        }

        if(!TextUtils.isEmpty(editWater.getText())){
            water = Double.parseDouble(editWater.getText().toString());
        } else{
            water = 0;
        }

        if(!TextUtils.isEmpty(editGarbage.getText())){
            garbage = Double.parseDouble(editGarbage.getText().toString());
        } else{
            garbage = 0;
        }

        if(!TextUtils.isEmpty(editInsurance.getText())){
            insurance = Double.parseDouble(editInsurance.getText().toString());
        } else{
            insurance = 0;
        }

        if(!TextUtils.isEmpty(editGroceries.getText())){
            groceries = Double.parseDouble(editGroceries.getText().toString());
        } else{
            groceries = 0;
        }

        if(!TextUtils.isEmpty(editChildCare.getText())){
            childCare = Double.parseDouble(editChildCare.getText().toString());
        } else{
            childCare = 0;
        }

        if(!TextUtils.isEmpty(editPhone.getText())){
            phone = Double.parseDouble(editPhone.getText().toString());
        } else{
            phone = 0;
        }

        if(!TextUtils.isEmpty(editInternet.getText())){
            internet = Double.parseDouble(editInternet.getText().toString());
        } else{
            internet = 0;
        }

        if(!TextUtils.isEmpty(editGym.getText())){
            gym = Double.parseDouble(editGym.getText().toString());
        } else{
            gym = 0;
        }

        if(!TextUtils.isEmpty(editStreaming.getText())){
            streaming = Double.parseDouble(editStreaming.getText().toString());
        } else{
            streaming = 0;
        }

        if(!TextUtils.isEmpty(editSubscription.getText())){
            subscription = Double.parseDouble(editSubscription.getText().toString());
        } else{
            subscription = 0;
        }

        total = electricity + water + garbage + insurance + groceries + childCare + phone + internet + gym + streaming + subscription + rent;

        Expenses expenses = new Expenses(rent, electricity, water, garbage, insurance, groceries, childCare, phone, internet, gym, streaming, subscription, total);

        databaseIncome.child("users").child(uid).child("expenses").setValue(expenses);
        Toast.makeText(this, "Expenses added", Toast.LENGTH_LONG).show();
    }
}
