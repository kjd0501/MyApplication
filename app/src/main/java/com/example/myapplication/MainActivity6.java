package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity{

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        list = (RecyclerView)findViewById(R.id.manipList);

        // Initialize contacts
        ArrayList manips = AdvancedManip.createManipList(20);
        // Create adapter passing in the sample user data
        manipListAdapter adapter = new manipListAdapter(manips);
        // Attach the adapter to the recyclerview to populate items
        list.setAdapter(adapter);
        // Set layout manager to position the items
        list.setLayoutManager(new LinearLayoutManager(this));
    }
}