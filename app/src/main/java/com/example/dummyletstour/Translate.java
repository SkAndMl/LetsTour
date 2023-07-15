package com.example.dummyletstour;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Translate extends AppCompatActivity implements FromFragListener{

    FragmentManager fragmentManager=getSupportFragmentManager(); FragmentTransaction fragmentTransaction;
    FragmentFrom fragmentFrom=new FragmentFrom(); FragmentTo fragmentTo=new FragmentTo();

    TextView report_textview;

    int LANG_CODE=0;

    String to_lang_choices[] = {"Tamil","Telugu","Hindi",
                                "Bengali","Kannada","French","German",
                                "Spanish","Japanese"};
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate_layout);

        spinner = (Spinner)findViewById(R.id.to_lang_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,to_lang_choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        report_textview = (TextView)findViewById(R.id.report_translate_textview);

        report_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Translate.this,
                        "We are currently working on giving you better translation.Sorry for the inconvenience",
                        Toast.LENGTH_LONG).show();
            }
        });

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_from,fragmentFrom);
        fragmentTransaction.add(R.id.container_to,fragmentTo);
        fragmentTransaction.commit();
    }

    public void sendMsg(String msg)
    {
        String selected = spinner.getSelectedItem().toString();
        for(int i=0;i<to_lang_choices.length;i++)
        {
            if(to_lang_choices[i].equals(selected))
            {
                LANG_CODE = i; break;
            }
        }
        fragmentTo.set_translation(msg,LANG_CODE);
    }
}
