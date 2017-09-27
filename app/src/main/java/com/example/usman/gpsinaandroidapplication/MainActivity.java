package com.example.usman.gpsinaandroidapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button signUp;
    Intent nextScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.loginButton);
        signUp = (Button) findViewById(R.id.signUpButton);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                nextScreen = new Intent(getApplicationContext(), loginScreenActivity.class);
                startActivity(nextScreen);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                nextScreen = new Intent(getApplicationContext(), signUpScreenActivity.class);
                startActivity(nextScreen);

            }
        });

    }

}
