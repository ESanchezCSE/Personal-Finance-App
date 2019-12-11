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

public class addExpense extends AppCompatActivity {
    EditText editRent1;
    EditText editElectricity1;
    EditText editWater1;
    EditText editGarbage1;
    EditText editInsurance1;
    EditText editGroceries1;
    EditText editChildCare1;
    EditText editPhone1;
    EditText editInternet1;
    EditText editGym1;
    EditText editStreaming1;
    EditText editSubscription1;
    Button buttonSubmitExpenses;

    Expenses expenses;

    String uid;

    DatabaseReference databaseExpenses;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private ValueEventListener mPostListener;
    private DatabaseReference mExpensesReference;
    private static final String TAG = "addExpensesPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        databaseExpenses = FirebaseDatabase.getInstance().getReference();
        editRent1 = (EditText) findViewById(R.id.editRent1);
        editElectricity1 = (EditText) findViewById(R.id.editElectricity1);
        editWater1 = (EditText) findViewById(R.id.editWater1);
        editGarbage1 = (EditText) findViewById(R.id.editGarbage1);
        editInsurance1 = (EditText) findViewById(R.id.editInsurance1);
        editGroceries1 = (EditText) findViewById(R.id.editGroceries1);
        editChildCare1 = (EditText) findViewById(R.id.editChildCare1);
        editPhone1 = (EditText) findViewById(R.id.editPhone1);
        editInternet1 = (EditText) findViewById(R.id.editInternet1);
        editGym1 = (EditText) findViewById(R.id.editGym1);
        editStreaming1 = (EditText) findViewById(R.id.editStreaming1);
        editSubscription1 = (EditText) findViewById(R.id.editSubscription1);
        buttonSubmitExpenses = (Button) findViewById(R.id.buttonSubmitExpenses);

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
        mExpensesReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("expenses");

        buttonSubmitExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExpenses(uid);
            }
        });
    }

    private void updateExpenses(String uid){
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

        if(!TextUtils.isEmpty(editRent1.getText())){//Checks if edit text box is empty.
            rent = Double.parseDouble(editRent1.getText().toString());
        } else{
            rent = expenses.rentBill;
        }

        if(!TextUtils.isEmpty(editElectricity1.getText())){//Checks if edit text box is empty.
            electricity = Double.parseDouble(editElectricity1.getText().toString());
        } else{
            electricity = expenses.electricityBill;
        }

        if(!TextUtils.isEmpty(editWater1.getText())){
            water = Double.parseDouble(editWater1.getText().toString());
        } else{
            water = expenses.waterBill;
        }

        if(!TextUtils.isEmpty(editGarbage1.getText())){
            garbage = Double.parseDouble(editGarbage1.getText().toString());
        } else{
            garbage = expenses.garbageBill;
        }

        if(!TextUtils.isEmpty(editInsurance1.getText())){
            insurance = Double.parseDouble(editInsurance1.getText().toString());
        } else{
            insurance = expenses.insuranceBill;
        }

        if(!TextUtils.isEmpty(editGroceries1.getText())){
            groceries = Double.parseDouble(editGroceries1.getText().toString());
        } else{
            groceries = expenses.groceriesBill;
        }

        if(!TextUtils.isEmpty(editChildCare1.getText())){
            childCare = Double.parseDouble(editChildCare1.getText().toString());
        } else{
            childCare = expenses.childCareBill;
        }

        if(!TextUtils.isEmpty(editPhone1.getText())){
            phone = Double.parseDouble(editPhone1.getText().toString());
        } else{
            phone = expenses.phoneBill;
        }

        if(!TextUtils.isEmpty(editInternet1.getText())){
            internet = Double.parseDouble(editInternet1.getText().toString());
        } else{
            internet = expenses.internetBill;
        }

        if(!TextUtils.isEmpty(editGym1.getText())){
            gym = Double.parseDouble(editGym1.getText().toString());
        } else{
            gym = expenses.gymBill;
        }

        if(!TextUtils.isEmpty(editStreaming1.getText())){
            streaming = Double.parseDouble(editStreaming1.getText().toString());
        } else{
            streaming = expenses.streamingBill;
        }

        if(!TextUtils.isEmpty(editSubscription1.getText())){
            subscription = Double.parseDouble(editSubscription1.getText().toString());
        } else{
            subscription = expenses.subscriptionBill;
        }

        total = electricity + water + garbage + insurance + groceries + childCare + phone + internet + gym + streaming + subscription + rent;

        Expenses expenses1 = new Expenses(rent, electricity, water, garbage, insurance, groceries, childCare, phone, internet, gym, streaming, subscription, total);

        databaseExpenses.child("users").child(uid).child("expenses").setValue(expenses1);
        Toast.makeText(this, "Updated Expenses", Toast.LENGTH_LONG).show();

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
                expenses = dataSnapshot.getValue(Expenses.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(addExpense.this, "Failed to load database for expenses.", Toast.LENGTH_SHORT).show(); //Error checking
                // [END_EXCLUDE]
            }
        };
        mExpensesReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mExpensesReference.removeEventListener(mPostListener);
        }
    }
}

