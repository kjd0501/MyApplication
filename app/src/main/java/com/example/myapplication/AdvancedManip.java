package com.example.myapplication;

import java.util.ArrayList;

/**
 * This class defines the data structure for the advanced manipulation modes.
 */
public class AdvancedManip{
    //name of the manipulation mode
    private String mName;

    /**
     * This method is used to create an advanced manipulation mode.
     * @param name is the name of the mode
     */
    public AdvancedManip(String name) {
        mName = name;
    }

    /**
     * This method is used to retrieve the name of the current manipulation mode.
     * @return name of the manipulation mode
     */
    public String getName() {
        return mName;
    }

    /**
     * This is used to create a dummy list for manipulation modes.
     * This needs to be changed when the app is in formal use.
     * @param numManips is the number of dummy modes to be added for testing the app
     * @return the list of dummy modes created
     */
    public static ArrayList<AdvancedManip> createManipList(int numManips) {
        ArrayList<AdvancedManip> manipList = new ArrayList<AdvancedManip>();

        for (int i = 1; i <= numManips; i++) {
            manipList.add(new AdvancedManip("manip " + i ));
        }

        return manipList;
    }
}
