package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Row implements Iterable{
    //The index of the row
    private int index;
    //The spaces in the row
    private ArrayList<Space> spaces;

    public int getIndex() {
        return index;
    }

    public Row(int index, Space[] spaceArray){

        spaces = new ArrayList<>();
        this.index = index;
        spaces.addAll(Arrays.asList(spaceArray));
    }


    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
