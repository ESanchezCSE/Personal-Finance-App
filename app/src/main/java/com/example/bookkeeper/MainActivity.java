package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    String email, password;

    EditText editEmail;
    EditText editPassword;

    Button logInButton;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get input from text boxes.
        editEmail = (EditText) findViewById(R.id.emailInput);
        editPassword = (EditText) findViewById(R.id.passwordInput);

        //Button
        logInButton = (Button) findViewById(R.id.logInBTN);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();

                sendLogIn(email, password);
            }
        });
    }


    public void sendLogIn(String userEmail, String userPassword) {
        //Checks weather the user is in the system.

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent intent1 = new Intent(MainActivity.this, mainPage.class);
                            startActivity(intent1);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }

                    }
        });
        // [END sign_in_with_email]
    }

    public void sendSignUp(View view) {
        Intent intent2 = new Intent(this, signUpPage.class);
        startActivity(intent2);
    }

}
