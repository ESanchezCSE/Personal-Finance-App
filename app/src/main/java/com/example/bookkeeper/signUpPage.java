package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signUpPage extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;

    Button submitBTN;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        //Views
        mEmailField = (EditText) findViewById(R.id.editEmailText);
        mPasswordField = (EditText) findViewById(R.id.editPasswordText);

        //Buttons
        submitBTN = (Button) findViewById(R.id.proceedToAccountSetup);


        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });
    }

    public static final String TAG = "FireBase";

    public void createUser(String email, String password){

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            Intent intent = new Intent(signUpPage.this, accountSetup.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                        }

                    }
                });
        // [END create_user_with_email]
    }



    /*public void proceedToAccountSetup(View view) {
        Intent intent = new Intent(this, accountSetup.class);
        startActivity(intent);
    }*/
}
