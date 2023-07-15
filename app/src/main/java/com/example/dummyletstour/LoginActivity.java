package com.example.dummyletstour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    EditText email_et,password_et; TextView signup_tv;
    Button login_btn;
    FirebaseAuth firebaseAuth;

    final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null)
        {
            finish(); startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }

        setup_ui_views();

        signup_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Login Failed:(", Toast.LENGTH_SHORT).show();
                            Log.w(TAG,"loginUserWithEmail:Failed",task.getException());
                        }
                    }
                });
            }
        });

    }

    private void setup_ui_views()
    {
        email_et = (EditText) findViewById(R.id.login_email_edittext);
        password_et = (EditText) findViewById(R.id.login_password_edittext);
        signup_tv = (TextView) findViewById(R.id.login_signup_textview);
        login_btn = (Button) findViewById(R.id.login_login_btn);
    }


}