package com.example.dummyletstour;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BucketListActivity extends AppCompatActivity {

    String[] places_selected = {};

    TextView list_places_tv; FloatingActionButton add_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucket_list_layout);

        setup_ui_views();

        if(places_selected.length==0)
            list_places_tv.setText("No place has been selected yet. When you select your dream location it will be displayed here");

    }

    private void setup_ui_views()
    {
        list_places_tv = (TextView) findViewById(R.id.list_places_textview);
        add_fab = (FloatingActionButton) findViewById(R.id.add_fab_btn);
    }


}
