package com.zybooks.recipebook;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    //used to keep track of what the current category is so it can display the correct list
    public enum Category {Entree, Appetizer, Dessert, Drink}

    public static Category currentCategory;
    public static Recipe currentRecipe;
    private static RecipeRepository instance;

    private ArrayList<Recipe> entreeRecipeList;
    private ArrayList<Recipe> appetizerRecipeList;
    private ArrayList<Recipe> dessertRecipeList;
    private ArrayList<Recipe> drinkRecipeList;

    public static int entreeCount;
    public static int appetizerCount;
    public static int dessertCount;
    public static int drinkCount;

    public static RecipeRepository getInstance(/*Context context*/)
    {
        if (instance == null)
        {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public RecipeRepository(/*Content context */)
    {
        entreeRecipeList = new ArrayList<>();
        appetizerRecipeList = new ArrayList<>();
        dessertRecipeList = new ArrayList<>();
        drinkRecipeList = new ArrayList<>();
        entreeCount = 0;
        appetizerCount = 0;
        dessertCount = 0;
        drinkCount = 0;
    }

    //getters
    public ArrayList<Recipe> getEntreeRecipeList()
    {
        return entreeRecipeList;
    }

    public ArrayList<Recipe> getAppetizerRecipeList()
    {
        return appetizerRecipeList;
    }

    public ArrayList<Recipe> getDessertRecipeList()
    {
        return dessertRecipeList;
    }

    public ArrayList<Recipe> getDrinkRecipeList()
    {
        return drinkRecipeList;
    }

    public void Add(Recipe recipe)
    {
        //add persistent version of this function
        // Database.Add() sort of thing
        entreeRecipeList.add(recipe);
    }

    public void Remove(int index)
    {
        entreeRecipeList.remove(index);
        entreeCount--;
    }

}
