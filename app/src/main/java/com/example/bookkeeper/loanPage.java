package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class loanPage extends AppCompatActivity {
    String uid;
    Loan loan;

    boolean hasLoans = false;
    boolean loan1 = false;
    boolean loan2 = false;
    boolean loan3 = false;
    boolean loan4 = false;
    boolean loan5 = false;
    boolean loan6 = false;
    List loanInfo1 = new ArrayList();
    List loanInfo2 = new ArrayList();
    List loanInfo3 = new ArrayList();
    List loanInfo4 = new ArrayList();
    List loanInfo5 = new ArrayList();
    List loanInfo6 = new ArrayList();

    TextView textview10;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private ValueEventListener mPostListener;
    private DatabaseReference mLoanReference;
    private static final String TAG = "LoanPage";

    LineChartView lineChartView;

    int[] axisData = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] yAxisData = {0, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_page);

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

        textview10 = (TextView) findViewById(R.id.textview10);
        textview10.setVisibility(View.GONE);

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                loan = dataSnapshot.getValue(Loan.class);
                displayChart(loan);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(loanPage.this, "Failed to load post.", Toast.LENGTH_SHORT).show(); //Error checking
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

    public void displayChart(Loan loan) {
        hasLoans = false;

        if(loan.interestAmount1 != 0){
            hasLoans = true;
            loan1 = true;

        } else{
            loan1 = false;
        }
        if(loan.interestAmount2 != 0){
            hasLoans = true;
            loan2 = true;

        } else{
            loan2 = false;
        }
        if(loan.interestAmount3 != 0){
            hasLoans = true;
            loan3 = true;

        } else{
            loan3 = false;
        }
        if(loan.interestAmount4 != 0){
            hasLoans = true;
            loan4 = true;

        } else{
            loan4 = false;
        }
        if(loan.interestAmount5 != 0){
            hasLoans = true;
            loan5 = true;

        } else{
            loan5 = false;
        }
        if(loan.interestAmount6 != 0){
            hasLoans = true;
            loan6 = true;

        } else{
            loan6 = false;
        }

        if (hasLoans == true) {
            //textview10.setVisibility(View.GONE);
            lineChartView = findViewById(R.id.chart);

            List yAxisValues = new ArrayList();
            List axisValues = new ArrayList();

            List values1 = new ArrayList();
            List values2 = new ArrayList();
            List values3 = new ArrayList();
            List values4 = new ArrayList();
            List values5 = new ArrayList();
            List values6 = new ArrayList();


            Line line1 = new Line(values1).setColor(Color.parseColor("#9C27B0"));
            Line line2 = new Line(values2).setColor(Color.parseColor("#63D84F"));
            Line line3 = new Line(values3).setColor(Color.parseColor("#DA680F"));
            Line line4 = new Line(values4).setColor(Color.parseColor("#6F4E37"));
            Line line5 = new Line(values5).setColor(Color.parseColor("#006C7f"));
            Line line6 = new Line(values6).setColor(Color.parseColor("#D70035"));

            for (int i = 0; i < axisData.length; i++) {
                axisValues.add(new PointValue(i, axisData[i]));
            }

            for (int i = 0; i < yAxisData.length; i++) {
                yAxisValues.add(new PointValue(i, yAxisData[i]));
            }

            List lines = new ArrayList();
            if(loan1){
                values1.add(new PointValue(0, interestCalculator(loan.interestAmount1, loan.interestRate1, 0)));
                values1.add(new PointValue(1, interestCalculator(loan.interestAmount1, loan.interestRate1, 1)));
                values1.add(new PointValue(3, interestCalculator(loan.interestAmount1, loan.interestRate1, 3)));
                values1.add(new PointValue(5, interestCalculator(loan.interestAmount1, loan.interestRate1, 5)));
                values1.add(new PointValue(10, interestCalculator(loan.interestAmount1, loan.interestRate1, 10)));
                lines.add(line1);
            }
            if(loan2){
                values2.add(new PointValue(0, interestCalculator(loan.interestAmount2, loan.interestRate2, 0)));
                values2.add(new PointValue(1, interestCalculator(loan.interestAmount2, loan.interestRate2, 1)));
                values2.add(new PointValue(3, interestCalculator(loan.interestAmount2, loan.interestRate2, 3)));
                values2.add(new PointValue(5, interestCalculator(loan.interestAmount2, loan.interestRate2, 5)));
                values2.add(new PointValue(10, interestCalculator(loan.interestAmount2, loan.interestRate2, 10)));
                lines.add(line2);
            }
            if(loan3){
                values3.add(new PointValue(0, interestCalculator(loan.interestAmount3, loan.interestRate3, 0)));
                values3.add(new PointValue(1, interestCalculator(loan.interestAmount3, loan.interestRate3, 1)));
                values3.add(new PointValue(3, interestCalculator(loan.interestAmount3, loan.interestRate3, 3)));
                values3.add(new PointValue(5, interestCalculator(loan.interestAmount3, loan.interestRate3, 5)));
                values3.add(new PointValue(10, interestCalculator(loan.interestAmount3, loan.interestRate3, 10)));
                lines.add(line3);
            }
            if(loan4){
                values4.add(new PointValue(0, interestCalculator(loan.interestAmount4, loan.interestRate4, 0)));
                values4.add(new PointValue(1, interestCalculator(loan.interestAmount4, loan.interestRate4, 1)));
                values4.add(new PointValue(3, interestCalculator(loan.interestAmount4, loan.interestRate4, 3)));
                values4.add(new PointValue(5, interestCalculator(loan.interestAmount4, loan.interestRate4, 5)));
                values4.add(new PointValue(10, interestCalculator(loan.interestAmount4, loan.interestRate4, 10)));
                lines.add(line4);
            }
            if(loan5){
                values5.add(new PointValue(0, interestCalculator(loan.interestAmount5, loan.interestRate5, 0)));
                values5.add(new PointValue(1, interestCalculator(loan.interestAmount5, loan.interestRate5, 1)));
                values5.add(new PointValue(3, interestCalculator(loan.interestAmount5, loan.interestRate5, 3)));
                values5.add(new PointValue(5, interestCalculator(loan.interestAmount5, loan.interestRate5, 5)));
                values5.add(new PointValue(10, interestCalculator(loan.interestAmount5, loan.interestRate5, 10)));
                lines.add(line5);
            }
            if(loan6){
                values6.add(new PointValue(0, interestCalculator(loan.interestAmount6, loan.interestRate6, 0)));
                values6.add(new PointValue(1, interestCalculator(loan.interestAmount6, loan.interestRate6, 1)));
                values6.add(new PointValue(3, interestCalculator(loan.interestAmount6, loan.interestRate6, 3)));
                values6.add(new PointValue(5, interestCalculator(loan.interestAmount6, loan.interestRate6, 5)));
                values6.add(new PointValue(10, interestCalculator(loan.interestAmount6, loan.interestRate6, 10)));
                lines.add(line6);
            }

            LineChartData data = new LineChartData();
            data.setLines(lines);

            Axis axis = new Axis();
            axis.setName("Time in Years");
            axis.setTextSize(16);
            axis.setTextColor(Color.parseColor("#03A9F4"));
            data.setAxisXBottom(axis);

            Axis yAxis = new Axis();
            yAxis.setName("Amount Owed in Dollars");
            yAxis.setTextColor(Color.parseColor("#03A9F4"));
            yAxis.setTextSize(16);
            data.setAxisYLeft(yAxis);

            lineChartView.setLineChartData(data);
            Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
            viewport.top = 13000;
            viewport.bottom = 0;
            lineChartView.setMaximumViewport(viewport);
            lineChartView.setCurrentViewport(viewport);
        } else {
            textview10.setVisibility(View.VISIBLE);
        }
    }

    public float interestCalculator(double interestAmount, double interestRate, double timeAccumalted) {
        float totalAmount = (float)interestAmount;
        float actualRate;
        if (interestRate < 1 && interestRate != 0) {
            actualRate = (float)interestRate + 1;
        } else if(interestRate == 0){
            actualRate = 1;
        } else {
            actualRate = (float)interestRate / 100 + 1;
        }

        for (int i = 0; i < timeAccumalted; i++) {
            totalAmount = totalAmount * actualRate;
        }

        return totalAmount;
    }

}
