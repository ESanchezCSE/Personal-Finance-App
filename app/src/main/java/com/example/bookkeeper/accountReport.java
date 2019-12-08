package com.example.bookkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class accountReport extends AppCompatActivity {
    String uid;

TextView status1,income1,expense1;
Button btn;
DatabaseReference reff;
FirebaseAuth mAuth;
double totalincome,totalexpense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_report);

        income1=(TextView) findViewById(R.id.income);
        expense1=(TextView) findViewById(R.id.expense);
        status1=(TextView)findViewById(R.id.status);
        btn=(Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       reff = FirebaseDatabase.getInstance().getReference();

                                       // Initialize Firebase Auth
                                       mAuth = FirebaseAuth.getInstance();
                                       // [END initialize_auth]

                                       // [START get_user_profile]
                                       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                       if (user != null) {
                                           uid = user.getUid();
                                       }// [END get_user_profile]


                                       // Initialize Database
                                       reff = FirebaseDatabase.getInstance().getReference()
                                               .child("users").child(uid).child("income");
                                       reff.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               String i_field = dataSnapshot.child("incomeMonthly").getValue().toString();
                                               income1.setText(i_field);
                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });

                                   }
            });






    }
}
