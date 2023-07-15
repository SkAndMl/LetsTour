package com.example.dummyletstour;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentFrom extends Fragment {

    FromFragListener ffl; EditText et;
    Button bt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.from_lang_fragment_layout,container,false);
        et = view.findViewById(R.id.from_lang_edittext);
        bt = view.findViewById(R.id.from_btn);
        bt.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ffl = (Translate)context;
    }

    public void onClick(View view)
    {
        ffl.sendMsg(et.getText().toString());
    }

}