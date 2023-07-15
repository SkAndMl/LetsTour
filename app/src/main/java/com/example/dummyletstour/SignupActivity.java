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


public class SignupActivity extends AppCompatActivity {

    EditText password_et,confirm_password_et,name_et,email_et;
    TextView login_tv;
    Button signup_btn;
    FirebaseAuth firebaseAuth;

    final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        firebaseAuth = FirebaseAuth.getInstance();

        setup_ui_views();

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = email_et.getText().toString();
                String password = password_et.getText().toString();
                String confirm_password = confirm_password_et.getText().toString();

                validate(email,password,confirm_password);
            }
        });

    }

    private void setup_ui_views()
    {
        password_et = (EditText) findViewById(R.id.signup_password_edittext);
        confirm_password_et = (EditText) findViewById(R.id.signup_confirm_password_edittext);
        name_et = (EditText) findViewById(R.id.signup_name_edittext);
        email_et = (EditText) findViewById(R.id.signup_email_edittext);
        login_tv = (TextView) findViewById(R.id.signup_login_textview);
        signup_btn = (Button) findViewById(R.id.final_signup_btn);
    }

    private boolean validate_password(String s)
    {
        char special_symbols[] = {'@','#','&'};
        boolean uppercase_present = false; boolean lowercase_present = false;
        boolean number_present = false; boolean special_symbol_present = false;

        for(int i=0;i<s.length();i++)
        {
            if(uppercase_present && lowercase_present && number_present && special_symbol_present) break;

            if(!lowercase_present)
            {
                if(s.charAt(i)>=97 && s.charAt(i)<=122) lowercase_present = true;
            }

            if(!uppercase_present)
            {
                if(s.charAt(i)>=65 && s.charAt(i)<=90) uppercase_present = true;
            }

            if(!number_present)
            {
                if(s.charAt(i)>=48 && s.charAt(i)<=57) number_present=true;
            }
            if(!special_symbol_present)
            {
                for(int j=0;j<special_symbols.length;j++)
                {
                    if(s.charAt(i)==special_symbols[j]) {special_symbol_present=true; break;}
                }
            }
            char c = s.charAt(i);
            if((c>90 && c<65) && (c>122 && c<97) && (c>57 && c<48))
            {
                boolean flag = false;
                for(int j=0;j<special_symbols.length;j++)
                {
                    if(c==special_symbols[j]) flag=true;
                }
                if(flag==false) return false;
            }
        }
        return uppercase_present && lowercase_present && number_present && special_symbol_present;
    }

    private void validate(String email,String password,String confirm_password)
    {
        if(password.equals(confirm_password) && validate_password(password) && password.length()>6)
        {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(SignupActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                    }
                    else
                    {
                        Toast.makeText(SignupActivity.this, "Sign up failed. Try again", Toast.LENGTH_SHORT).show();
                        Log.w(TAG,"createUserWithEmailException",task.getException());
                    }
                }
            });
        }
    }

}
