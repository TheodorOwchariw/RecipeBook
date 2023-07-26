package com.zybooks.recipebook;

import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recipe {
    private String catName;
    private String recipeName;
    private String ingredients;
    //private ArrayList<String> instructions;
    private String instructions;
    private String notes;
    private ImageView picture;
    private int index;

    //constructors
    public Recipe()
    {
        catName = "";
        recipeName = "";
        ingredients = "";
        instructions = "";
        notes = "";
    }

    //accessors
    public String getCatName()
    {
        return catName;
    }

    public String getRecipeName()
    {
        return recipeName;
    }

    /*
    public ArrayList<String> getIngredients() {
        return ingredients;
    } */

    public String getIngredients()
    {
        return ingredients;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public String getNotes()
    {
        return notes;
    }
    public int getIndex() {return index; }

    //mutators
    public void setCatName(String name)
    {
        catName = name;
    }

    public void setRecipeName(String name)
    {
        recipeName = name;
    }

    public void setIngredients(String string)
    {
        ingredients = string;
    }

    public void setInstructions(String string)
    {
        instructions = string;
    }

    public void setNotes(String str)
    {
        notes = str;
    }
    public void setIndex(int i)
    {
        index = i;
    }

    @Override
    public String toString() {
        return this.recipeName;  // Assuming your Recipe class has a recipeName field
    }



}
