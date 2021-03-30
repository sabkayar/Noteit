package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AddNotes extends Fragment {
    EditText editText;
    Button cancelButton;
    Button okButton;
    DbManager dbManager;
    String notes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notes, container, false);
        editText = view.findViewById(R.id.add_note);

        cancelButton = view.findViewById(R.id.cancel_note);
        okButton = view.findViewById(R.id.ok_note);

        dbManager = new DbManager(getActivity());
        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFragment();

            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes = editText.getText().toString();
                dbManager.insert(notes);
                addFragment();
            }
        });

    }

    public void addFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        transaction.replace(R.id.frag_set,mainFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}