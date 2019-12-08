package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loanPage extends AppCompatActivity {
    String uid;
    Loan loan;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private ValueEventListener mPostListener;
    private DatabaseReference mLoanReference;
    DatabaseReference databaseIncome;
    private static final String TAG = "LoanPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_page);

        databaseIncome = FirebaseDatabase.getInstance().getReference();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }// [END get_user_profile]

        // Initialize Database
        mLoanReference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("loans");
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
                Toast.makeText(loanPage.this, "Loan Value at 1: " + loan.getInterestAmount1(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                //Toast.makeText(loanPage.this, "Failed to load post.",Toast.LENGTH_SHORT).show(); //Error checking
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
