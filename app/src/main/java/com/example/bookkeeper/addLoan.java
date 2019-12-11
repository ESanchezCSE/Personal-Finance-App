package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addLoan extends AppCompatActivity {
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
    Button buttonSubmitLoanUpdate;

    Loan loan;

    String uid;

    DatabaseReference databaseLoan;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private ValueEventListener mPostListener;
    private DatabaseReference mLoanReference;
    private static final String TAG = "addLoanPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan);

        databaseLoan = FirebaseDatabase.getInstance().getReference();

        editInterestAmount1 = (EditText) findViewById(R.id.editInterestAmount10);
        editInterestRate1 = (EditText) findViewById(R.id.editInterestRate10);
        editInterestAmount2 = (EditText) findViewById(R.id.editInterestAmount20);
        editInterestRate2 = (EditText) findViewById(R.id.editInterestRate20);
        editInterestAmount3 = (EditText) findViewById(R.id.editInterestAmount30);
        editInterestRate3 = (EditText) findViewById(R.id.editInterestRate30);
        editInterestAmount4 = (EditText) findViewById(R.id.editInterestAmount40);
        editInterestRate4 = (EditText) findViewById(R.id.editInterestRate40);
        editInterestAmount5 = (EditText) findViewById(R.id.editInterestAmount50);
        editInterestRate5 = (EditText) findViewById(R.id.editInterestRate50);
        editInterestAmount6 = (EditText) findViewById(R.id.editInterestAmount60);
        editInterestRate6 = (EditText) findViewById(R.id.editInterestRate60);
        buttonSubmitLoanUpdate = (Button) findViewById(R.id.buttonSubmitLoanUpdate);

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
        mLoanReference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("loans");

        buttonSubmitLoanUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLoan(uid);
            }
        });
    }

    private void updateLoan(String uid){
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
            interestAmount1 = loan.interestAmount1;
        }

        if(!TextUtils.isEmpty(editInterestRate1.getText())){//Checks if edit text box is empty.
            interestRate1 = Double.parseDouble(editInterestRate1.getText().toString());
        } else{
            interestRate1 = loan.interestRate1;
        }

        if(!TextUtils.isEmpty(editInterestAmount2.getText())){//Checks if edit text box is empty.
            interestAmount2 = Double.parseDouble(editInterestAmount2.getText().toString());
        } else{
            interestAmount2 = loan.interestAmount2;
        }

        if(!TextUtils.isEmpty(editInterestRate2.getText())){//Checks if edit text box is empty.
            interestRate2 = Double.parseDouble(editInterestRate2.getText().toString());
        } else{
            interestRate2 = loan.interestRate2;
        }

        if(!TextUtils.isEmpty(editInterestAmount3.getText())){//Checks if edit text box is empty.
            interestAmount3 = Double.parseDouble(editInterestAmount3.getText().toString());
        } else{
            interestAmount3 = loan.interestAmount3;
        }

        if(!TextUtils.isEmpty(editInterestRate3.getText())){//Checks if edit text box is empty.
            interestRate3 = Double.parseDouble(editInterestRate3.getText().toString());
        } else{
            interestRate3 = loan.interestRate3;
        }

        if(!TextUtils.isEmpty(editInterestAmount4.getText())){//Checks if edit text box is empty.
            interestAmount4 = Double.parseDouble(editInterestAmount4.getText().toString());
        } else{
            interestAmount4 = loan.interestAmount4;
        }

        if(!TextUtils.isEmpty(editInterestRate4.getText())){//Checks if edit text box is empty.
            interestRate4 = Double.parseDouble(editInterestRate4.getText().toString());
        } else{
            interestRate4 = loan.interestRate4;
        }

        if(!TextUtils.isEmpty(editInterestAmount5.getText())){//Checks if edit text box is empty.
            interestAmount5 = Double.parseDouble(editInterestAmount5.getText().toString());
        } else{
            interestAmount5 = loan.interestAmount5;
        }

        if(!TextUtils.isEmpty(editInterestRate5.getText())){//Checks if edit text box is empty.
            interestRate5 = Double.parseDouble(editInterestRate5.getText().toString());
        } else{
            interestRate5 = loan.interestRate5;
        }

        if(!TextUtils.isEmpty(editInterestAmount6.getText())){//Checks if edit text box is empty.
            interestAmount6 = Double.parseDouble(editInterestAmount6.getText().toString());
        } else{
            interestAmount6 = loan.interestAmount6;
        }

        if(!TextUtils.isEmpty(editInterestRate6.getText())){//Checks if edit text box is empty.
            interestRate6 = Double.parseDouble(editInterestRate6.getText().toString());
        } else{
            interestRate6 = loan.interestRate6;
        }

        aLoanTotal = interestAmount1 + interestAmount2 +interestAmount3 + interestAmount4 + interestAmount5 + interestAmount6;

        loan = new Loan(aLoanTotal, interestAmount1, interestRate1, interestAmount2, interestRate2, interestAmount3, interestRate3, interestAmount4, interestRate4, interestAmount5, interestRate5, interestAmount6, interestRate6);

        databaseLoan.child("users").child(uid).child("loans").setValue(loan);

        Toast.makeText(this, "Updated Loans", Toast.LENGTH_LONG).show();

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
                loan = dataSnapshot.getValue(Loan.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(addLoan.this, "Failed to load post.", Toast.LENGTH_SHORT).show(); //Error checking
                // [END_EXCLUDE]
            }
        };
        mLoanReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mLoanReference.removeEventListener(mPostListener);
        }
    }
}
