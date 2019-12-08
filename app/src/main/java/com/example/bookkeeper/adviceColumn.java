package com.example.bookkeeper;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import android.widget.Toast;






public class adviceColumn extends AppCompatActivity {


    String uid;


    DatabaseReference databaseIncome;

    Expenses expenses; // this is either
    Loan loans;
    Income income;git remote -v

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private static final String TAG = "MainActivity";

    private ValueEventListener mPostListener;

    private DatabaseReference mExpenseReference;
    private DatabaseReference mLoanReference;
    private DatabaseReference mIncomeReference;

    TextView textElement;

    double rentBill;
    double electricityBill;
    double waterBill;
    double garbageBill;
    double insuranceBill;
    double groceriesBill;
    double childCareBill;
    double phoneBill;
    double internetBill;
    double gymBill;
    double streamingBill;
    double subscriptionBill;
    double totalCostOfBills;

    double incomeMonthly;

    double freeIncome;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_column);

        databaseIncome = FirebaseDatabase.getInstance().getReference();

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
        mExpenseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("expenses");//loans will be changed here for either income or expenses.
        mLoanReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("loans");//loans will be changed here for either income or expenses.
        mIncomeReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("income");//loans will be changed here for either income or expenses.

        textElement = (TextView) findViewById(R.id.advice);


        double totalExpenses;


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
                income = dataSnapshot.getValue(Income.class);//loan is what you set earlier, expenses income.

                expenses = dataSnapshot.getValue(Expenses.class);//loan is what you set earlier, expenses income.
                //loans = dataSnapshot.getValue(Loan.class);//loan is what you set earlier, expenses income.


                //Toast.makeText(adviceColumn.this, "Total Bill Value at: " + expenses.getTotalCostOfBills(),Toast.LENGTH_LONG).show();// Error Checking

                incomeMonthly = income.getIncomeMonthly();
                totalCostOfBills = expenses.getTotalCostOfBills();


                //freeIncome = incomeMonthly - totalCostOfBills;

                textElement.setText("Total Cost of Bills are " + String.valueOf(totalCostOfBills));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(adviceColumn.this, "Failed to load post.",Toast.LENGTH_LONG).show(); //Error checking
                // [END_EXCLUDE]
            }
        };
        mExpenseReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;


    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mExpenseReference.removeEventListener(mPostListener);
        }
    }



}
