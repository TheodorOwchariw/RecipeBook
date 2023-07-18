package com.zybooks.recipebook;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recipe {
    private String catName;
    private String recipeName;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;
    private String notes;

    //constructors
    public Recipe()
    {
        catName = "";
        recipeName = "";
        ingredients = new ArrayList<>();
        instructions = new ArrayList<>();
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

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getInstructions()
    {
        return instructions;
    }

    public String getNotes()
    {
        return notes;
    }

    //mutators
    public void setCatName(String name)
    {
        catName = name;
    }

    public void setRecipeName(String name)
    {
        recipeName = name;
    }

    public void setIngredients(ArrayList<String> list)
    {
        ingredients = list;
    }

    public void setInstructions(ArrayList<String> list)
    {
        instructions = list;
    }

    public void setNotes(String str)
    {
        notes = str;
    }



}
