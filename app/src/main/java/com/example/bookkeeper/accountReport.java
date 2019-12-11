package com.example.bookkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class accountReport extends AppCompatActivity {
    String uid;
    PieChartView pieChartView;

TextView status1,income1,expense1;
DatabaseReference reff1,reff2;
FirebaseAuth mAuth;
Button btn;
double totalincome,totalexpense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_report);

        income1 = (TextView) findViewById(R.id.income);
        expense1 = (TextView) findViewById(R.id.expense);
        status1 = (TextView) findViewById(R.id.status);
        btn = (Button) findViewById(R.id.button);
        pieChartView = findViewById(R.id.chart);



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
                String i_field = dataSnapshot.child("incomeMonthly").getValue().toString();
                totalincome = Double.valueOf(i_field);
                String temp_income = Double.toString(totalincome);
                income1.setText(temp_income);


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
                String e_field = dataSnapshot.child("totalCostOfBills").getValue().toString();
                totalexpense = Double.valueOf(e_field);
                String i_expense = Double.toString(totalexpense);
                expense1.setText(i_expense);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalincome > totalexpense) {
                    String message = "You are earning more keep it up!";
                    status1.setText(message);
                } else if (totalexpense > totalincome) {
                    String message = "You are spending more, Watch out!";
                    status1.setText(message);
                } else if (totalincome == totalexpense) {
                    String message = "Your finance is stable";
                    status1.setText(message);
                }

                List pieData = new ArrayList<>();
                int i = (int) Math.round(totalincome);
                int j = (int) Math.round(totalexpense);

                pieData.add(new SliceValue(i, Color.LTGRAY).setLabel("Income"));
                pieData.add(new SliceValue(j, Color.RED).setLabel("Expense"));

                PieChartData pieChartData = new PieChartData(pieData);
                pieChartData.setHasLabels(true).setValueLabelTextSize(14);
                pieChartData.setHasCenterCircle(true).setCenterText1("Monthly Finance").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#000000"));
                pieChartView.setPieChartData(pieChartData);
            }
        });



    }






    }
