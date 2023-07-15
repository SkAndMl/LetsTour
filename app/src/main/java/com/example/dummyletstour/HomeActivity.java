package com.example.dummyletstour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {


    FloatingActionButton bl_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);

        bl_fab = (FloatingActionButton) findViewById(R.id.move_to_bl_fab);

        bl_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,BucketListActivity.class));
            }
        });

    }

    public void move_to_translate(View view)
    {
        Intent i = new Intent(this,Translate.class);
        startActivity(i);
    }
}