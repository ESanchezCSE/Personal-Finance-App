package com.example.bookkeeper;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;



import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class adviceColumn extends AppCompatActivity {

    String uid;

    TextView status1,income1,expense1;
    DatabaseReference reff1,reff2;
    FirebaseAuth mAuth;
    double totalincome,totalexpense;



    Expenses expenses; // this is either
    Loan loans;
    Income income;


    private static final String TAG = "MainActivity";



    TextView textElement;


    double totalCostOfBills;
    double rent;
    double groceries;
    double childCare;
    double phone;
    double internet;
    double insurance;
    double garbage;
    double water;




    double incomeMonthly;
    double savingsAmount;
    double companyMatching;
    double iraAmount;

    double freeIncome;
    double tempFreeIncome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_column);

        textElement = (TextView) findViewById(R.id.advice);




        reff1 = FirebaseDatabase.getInstance().getReference();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }// [END get_user_profile]


        // Initialize Database
        reff1 = FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("income");
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                incomeMonthly = Double.valueOf(dataSnapshot.child("incomeMonthly").getValue().toString());
                savingsAmount = Double.valueOf(dataSnapshot.child("savingsAmount").getValue().toString());
                companyMatching = Double.valueOf(dataSnapshot.child("match401Amount").getValue().toString());
                iraAmount = Double.valueOf(dataSnapshot.child("rothIRAAmount").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Initialize Database
        reff2 = FirebaseDatabase.getInstance().getReference()
                .child("users").child(uid).child("expenses");
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                totalCostOfBills = Double.valueOf(dataSnapshot.child("totalCostOfBills").getValue().toString());
                rent = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());
                groceries = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());
                childCare = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());
                phone = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());
                internet = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());
                insurance = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());
                garbage = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());
                water = Double.valueOf(dataSnapshot.child("rentBill").getValue().toString());

                freeIncome = incomeMonthly - totalCostOfBills;
                tempFreeIncome = freeIncome;

                if(freeIncome > 0){
                    textElement.setText("Congrats!  ");
                }
                else{
                    textElement.setText("Oh no!  ");
                }
                textElement.append("You currently have $"+freeIncome+"0 of free income.\n\n");

                if(freeIncome > 1) {

                    //check for savings
                    if(savingsAmount <=  totalCostOfBills  && tempFreeIncome > 0){
                        double a = totalCostOfBills - savingsAmount;
                        if(tempFreeIncome < a){
                            a = tempFreeIncome;
                        }

                        textElement.append("You don't currently have enough savings as it is prudent to have at least one month's of expenses in a savings account, just in case.  " +
                                "We suggest contributing into that, until the account reaches $" + totalCostOfBills + "0.  You can contribute $" + a + "0 this month!\n\n");

                        tempFreeIncome = tempFreeIncome - a;
                    }

                    //check 401k
                    double maxCompanyMatching = companyMatching * incomeMonthly * 12;
                    if(companyMatching >= 0.001 && iraAmount <= maxCompanyMatching  && tempFreeIncome > 0){
                        double b = maxCompanyMatching - iraAmount;
                        double a = b;
                        if(tempFreeIncome < a){
                            a = tempFreeIncome;
                        }
                        textElement.append("Your company matches up to " + companyMatching + "% of your 401k contribution!  You have put $" + iraAmount + "0 in this year and should put in $" + b + "0 more to maximize the matching.  " +
                                "You can contribute $" + a + "0 this month!\n\n");

                        tempFreeIncome = tempFreeIncome - a;
                    }

                    //check 3 months savings
                    if(savingsAmount <=  3 * totalCostOfBills  && tempFreeIncome > 0){
                        double a = 3 * totalCostOfBills - savingsAmount;
                        if(tempFreeIncome < a){
                            a = tempFreeIncome;
                        }

                        textElement.append("We suggest increasing your savings account to hold at least three month's of expenses.  " +
                                "The target for this account is $" + 3 * totalCostOfBills + "0.  You can contribute $" + a + "0 this month!\n\n");

                        tempFreeIncome = tempFreeIncome - a;
                    }

                    //check 3 months savings
                    if(savingsAmount <=  3 * totalCostOfBills + 6000 && tempFreeIncome > 0){
                        double a = savingsAmount - 3 * totalCostOfBills;

                        if(a < 0){
                            a=0;
                        }

                        double b = 6000 - a;

                        if(tempFreeIncome < b){
                            b = tempFreeIncome;
                        }



                        textElement.append("You can contribute up to $6000 yearly into a Roth or Traditional IRA.  " +
                                "You currently have $" + a + "0.  You can contribute $" + b + "0 this month!\n\n");

                        tempFreeIncome = tempFreeIncome - b;
                    }

                    if(tempFreeIncome > 0){
                        textElement.append("\n\nYou have $"+tempFreeIncome + "0 left to spend this month!  As you are financially stable for this month, go wild!");
                    }
                }

                else{
                    if(rent + groceries + water + childCare + phone + internet + insurance + garbage <= .85 * incomeMonthly){
                        textElement.append("We suggest lowering non-mandatory expenses to get you back on track!\n\n");
                    }

                    if (rent >= incomeMonthly/3) {
                        textElement.append("We suggest looking for a place with lower rent.  Your rent should ideally be around $"+incomeMonthly/3 + "0!");
                    }


                }


             }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

}
