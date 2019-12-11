package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class mainPage extends AppCompatActivity {

    ImageView bgapp, pig;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        bgapp = (ImageView) findViewById(R.id.bgapp);
        pig = (ImageView) findViewById(R.id.pig);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        bgapp.animate().translationY(-1600).setDuration(800).setStartDelay(300);
        pig.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);
        btn_1 = (Button)findViewById(R.id.button1);
        btn_2 = (Button)findViewById(R.id.button2);
        btn_3 = (Button)findViewById(R.id.button3);
        btn_4 = (Button)findViewById(R.id.button4);
        btn_5 = (Button)findViewById(R.id.button5);
        btn_6 = (Button)findViewById(R.id.button6);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(mainPage.this,personalGoals.class);
                startActivity(intent1);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(mainPage.this,addTransaction.class);
                startActivity(intent2);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(mainPage.this,accountReport.class);
                startActivity(intent3);
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(mainPage.this,loanPage.class);
                startActivity(intent4);
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(mainPage.this,adviceColumn.class);
                startActivity(intent5);
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(mainPage.this, "Successfully Logged out",Toast.LENGTH_SHORT).show();
                Intent intent6 = new Intent(mainPage.this,MainActivity.class);
                startActivity(intent6);
            }
        });

    }


}
