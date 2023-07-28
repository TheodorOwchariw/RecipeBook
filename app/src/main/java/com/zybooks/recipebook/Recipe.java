package com.zybooks.recipebook;

import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String recipeName;

    @ColumnInfo(name = "ingredients")
    private String ingredients;

    @ColumnInfo(name = "instructions")
    private String instructions;


    /*  private String notes;
    @ColumnInfo
    private ImageView picture;
    */
    private int index;

    //constructors
    public Recipe()
    {
        //catName = "";
        recipeName = "";
        ingredients = "";
        instructions = "";
        //notes = "";

    }

    //accessors
    public int getId() {return id;}
    public String getRecipeName()
    {
        return recipeName;
    }
    public String getIngredients()
    {
        return ingredients;
    }

    public String getInstructions()
    {
        return instructions;
    }

//    //public String getNotes()
//    {
//        return notes;
//    }
    public int getIndex() {return index; }

    //mutators

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

//    //public void setNotes(String str)
//    {
//        notes = str;
//    }
    public void setIndex(int i)
    {
        index = i;
    }

    @Override
    public String toString() {
        return this.recipeName;  // Assuming your Recipe class has a recipeName field
    }
}
