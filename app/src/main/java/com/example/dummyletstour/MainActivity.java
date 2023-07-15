package com.example.dummyletstour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void move_to_login(View view)
    {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void move_to_signup(View view)
    {
        Intent i = new Intent(this,SignupActivity.class);
        startActivity(i);
    }

}