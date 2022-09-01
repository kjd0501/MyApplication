package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * This class is used to declare the methods for the page activity_main6.xml.
 * In this class, a list view for the advanced manipulation modes is defined.
 */
public class MainActivity6 extends AppCompatActivity{

    //variable to declare list view for advanced manipulation modes
    private RecyclerView list;

    /**
     * This method is used to initialise the list available on the page.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the page to activity_main6
        setContentView(R.layout.activity_main6);

        //initialise variable for the list view of advanced manipulation modes
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