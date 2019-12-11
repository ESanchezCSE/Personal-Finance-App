package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addIncome extends AppCompatActivity {
    EditText editIncome1;
    EditText editSavings1;
    EditText editRothIRA1;
    EditText editMatch401Amount1;
    Spinner spinnerOccuring1;
    Button buttonSubmitIncome1;

    Income income;

    String uid;

    DatabaseReference databaseIncome;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private ValueEventListener mPostListener;
    private DatabaseReference mIncomeReference;
    private static final String TAG = "addIncomePage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        databaseIncome = FirebaseDatabase.getInstance().getReference();
        editIncome1 = (EditText) findViewById(R.id.editIncome1);
        editSavings1 = (EditText) findViewById(R.id.editSavings1);
        editRothIRA1 = (EditText) findViewById(R.id.editRothIRA1);
        editMatch401Amount1 = (EditText) findViewById(R.id.editMatch401Amount1);
        spinnerOccuring1 = (Spinner) findViewById(R.id.spinnerOccuring1);
        buttonSubmitIncome1 = (Button) findViewById(R.id.buttonSubmitIncome1);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
            //Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();//Error checking
        }// [END get_user_profile]

        // Initialize Database
        mIncomeReference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("income");

        buttonSubmitIncome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateIncome(uid);
            }
        });
    }

    private void updateIncome(String uid) {
        double incomeFinal;
        double savings;
        double rothIRAAmount;
        double match401Amount;
        double incomeAmount;// = Double.parseDouble(editIncome.getText().toString());
        String reoccurance = spinnerOccuring1.getSelectedItem().toString();

        if (!TextUtils.isEmpty(editIncome1.getText())) {
            incomeAmount = Double.parseDouble(editIncome1.getText().toString());
            switch (reoccurance) {
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
        } else{
                incomeFinal = income.incomeMonthly;
            }

            if (!TextUtils.isEmpty(editSavings1.getText())) {
                savings = Double.parseDouble(editSavings1.getText().toString());
            } else {
                savings = income.savingsAmount;
            }

            if (!TextUtils.isEmpty(editRothIRA1.getText())) {
                rothIRAAmount = Double.parseDouble(editRothIRA1.getText().toString());
            } else {
                rothIRAAmount = income.rothIRAAmount;
            }

            if (!TextUtils.isEmpty(editMatch401Amount1.getText())) {
                match401Amount = Double.parseDouble(editMatch401Amount1.getText().toString());
            } else {
                match401Amount = income.match401Amount;
            }

            income = new Income(incomeFinal, savings, rothIRAAmount, match401Amount);

            databaseIncome.child("users").child(uid).child("income").setValue(income);

            Toast.makeText(this, "Updated Income", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, addTransaction.class);
            startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                income = dataSnapshot.getValue(Income.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(addIncome.this, "Failed to load post.", Toast.LENGTH_SHORT).show(); //Error checking
                // [END_EXCLUDE]
            }
        };
        mIncomeReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mIncomeReference.removeEventListener(mPostListener);
        }
    }
}
