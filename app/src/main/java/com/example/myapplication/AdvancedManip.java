package com.example.myapplication;

import java.util.ArrayList;

public class AdvancedManip{
    private String mName;

    public AdvancedManip(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    private static int lastContactId = 0;

    public static ArrayList<AdvancedManip> createManipList(int numManips) {
        ArrayList<AdvancedManip> manipList = new ArrayList<AdvancedManip>();

        for (int i = 1; i <= numManips; i++) {
            manipList.add(new AdvancedManip("manip " + i ));
        }

        return manipList;
    }
}
