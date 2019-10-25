package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String email, password;

    EditText editEmail;
    EditText editPassword;

    Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get input from text boxes.
        editEmail = (EditText) findViewById(R.id.emailInput);
        editPassword = (EditText) findViewById(R.id.passwordInput);

        //Button
        logInButton = (Button) findViewById(R.id.logInBTN);
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
        if ((userEmail.equals("admin")) && (userPassword.equals("password"))){
            Intent intent1 = new Intent(this, mainPage.class);
            startActivity(intent1);
        } else{

        }
    }

    public void sendSignUp(View view) {
        Intent intent2 = new Intent(this, signUpPage.class);
        startActivity(intent2);
    }

}
