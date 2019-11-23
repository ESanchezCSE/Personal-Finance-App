package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class accountSetup extends AppCompatActivity {
    private static final String FILE_NAME = "test.txt";

    EditText income;
    EditText rent;
    EditText food;
    EditText phone_bill;
    EditText utilities;
    EditText car_insurance;
    EditText health_insurance;
    EditText loan;
    EditText student_loan;
    EditText interest_rate;
    EditText saving;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);

        income = findViewById(R.id.input_income);
        rent = findViewById(R.id.input_rent);
        food = findViewById(R.id.input_monthly_food);
        phone_bill = findViewById(R.id.input_phone_bill);
        utilities = findViewById(R.id.input_utilities);
        car_insurance = findViewById(R.id.input_car_insurance);
        health_insurance = findViewById(R.id.input_health_insurance);
        loan = findViewById(R.id.input_loan);
        student_loan = findViewById(R.id.input_student_loan);
        interest_rate = findViewById(R.id.input_interest_rate);
        saving = findViewById(R.id.input_saving);
    }

    //method to save data entry into txt file
    public void submit(View v)
    {
        String t_income = income.getText().toString();
        String t_rent = rent.getText().toString();
        String t_food = food.getText().toString();
        String t_phone = phone_bill.getText().toString();
        String t_utilities = utilities.getText().toString();
        String t_car = car_insurance.getText().toString();
        String t_health = health_insurance.getText().toString();
        String t_loan = loan.getText().toString();
        String t_stdloan = student_loan.getText().toString();
        String t_interest = interest_rate.getText().toString();
        String t_saving = saving.getText().toString();
        String text;
        //concatenate strings into a comma separated text
        text = t_income + "," + t_rent + "," + t_food + "," + t_phone + "," + t_utilities + "," + t_car + "," + t_health + "," + t_loan + "," + t_stdloan + "," + t_interest + "," + t_saving;
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);  //opening file
            fos.write(text.getBytes());                     //writing to the file

            //clearing entry field after pressing submit button
            income.getText().clear();
            rent.getText().clear();
            food.getText().clear();
            phone_bill.getText().clear();
            utilities.getText().clear();
            car_insurance.getText().clear();
            health_insurance.getText().clear();
            loan.getText().clear();
            student_loan.getText().clear();
            interest_rate.getText().clear();
            saving.getText().clear();


            //display toast message to where the txt file is stored
            Toast.makeText(this, "Saved to"+ getFilesDir()+"/"+FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            if (fos !=null) {
                try {
                    fos.close();                       //closing the file after writing
                }  catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
