package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class manipListAdapter extends
    RecyclerView.Adapter<manipListAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView name;
        public Button goToAction;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.manip_name);
            goToAction = (Button) itemView.findViewById(R.id.goToAction);
        }
    }

    // Store a member variable for the contacts
    private List<AdvancedManip> manip;

    // Pass in the contact array into the constructor
    public manipListAdapter(List<AdvancedManip> manips) {
        manip = manips;
    }


    @NonNull
    @Override
    public manipListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View manipView = inflater.inflate(R.layout.manip_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(manipView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(manipListAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        AdvancedManip contact = manip.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.name;
        textView.setText(contact.getName());
        Button button = holder.goToAction;
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return manip.size();
    }
}
