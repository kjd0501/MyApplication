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

/**
 * This class is used as an adapter for a view holder in the recylcler view.
 */
public class manipListAdapter extends
    RecyclerView.Adapter<manipListAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // variables for view holder in a single row
        public TextView name;//name of the manipulation mode
        public Button goToAction;//button used to access mode

        /**
         * This is used to create a view holder for a single row.
         * @param itemView is the custom view
         */
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.manip_name);
            goToAction = (Button) itemView.findViewById(R.id.goToAction);
        }
    }

    // Store a member variable for the manipulator mode
    private List<AdvancedManip> manip;

    // Pass in the manipulator mode array into the constructor
    public manipListAdapter(List<AdvancedManip> manips) {
        manip = manips;
    }

    /**
     * This is used to inflate a custom view holder.
     */
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

    /**
     * This is used to define the placeholder for the name and action in the custom view
     * holder.
     */
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
