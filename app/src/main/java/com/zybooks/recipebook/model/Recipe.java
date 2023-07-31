package com.zybooks.recipebook.model;

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
    private long id;

    @ColumnInfo(name = "name")
    private String recipeName;

    @ColumnInfo(name = "ingredients")
    private String ingredients;

    @ColumnInfo(name = "instructions")
    private String instructions;

    @ColumnInfo(name = "category_name")
    private String categoryName;

    /*
    @ColumnInfo
    private ImageView picture;
    */

    //constructors
    public Recipe()
    {
        categoryName = "";
        recipeName = "";
        ingredients = "";
        instructions = "";
    }

    //accessors
    public long getId() { return id; }
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
    public String getCategoryName() { return categoryName ; }

    //mutators
    public void setId(int id) {
        this.id = id;
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

    public void setCategoryName(String string) { categoryName = string; }

    @Override
    public String toString() {
        return this.recipeName;
    }
}
