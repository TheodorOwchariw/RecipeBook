package com.zybooks.recipebook;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    //used to keep track of what the current category is so it can display the correct list
    public enum Category {Entrees, Appetizers, Desserts, Drinks}

    public static Category currentCategory;
    public static Recipe currentRecipe;
    private static RecipeRepository instance;

    private ArrayList<Recipe> entreeRecipeList;
    private ArrayList<Recipe> appetizerRecipeList;
    private ArrayList<Recipe> dessertRecipeList;
    private ArrayList<Recipe> drinkRecipeList;

    private static int entreeCount;
    private static int appetizerCount;
    private static int dessertCount;
    private static int drinkCount;

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
        currentCategory = Category.Entrees;
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
    public int getEntreeCount() {return entreeCount;}
    public int getAppetizerCount() {return appetizerCount;}
    public int getDessertCount() {return dessertCount;}
    public int getDrinkCount() {return drinkCount;}

    public void Add(Recipe recipe)
    {
        //add persistent version of this function
        // Database.Add() sort of thing
        //entreeRecipeList.add(recipe);

        switch (RecipeRepository.currentCategory) {
            case Entrees: {
                entreeRecipeList.add(recipe);
                break;
            }
            case Appetizers: {
                appetizerRecipeList.add(recipe);
                break;
            }
            case Desserts: {
                dessertRecipeList.add(recipe);
                break;
            }
            case Drinks: {
                drinkRecipeList.add(recipe);
                break;
            }
        }
    }

    public void incrementCount() {
        switch (RecipeRepository.currentCategory) {
            case Entrees: {
                entreeCount++;
                break;
            }
            case Appetizers: {
                appetizerCount++;
                break;
            }
            case Desserts: {
                dessertCount++;
                break;
            }
            case Drinks: {
                drinkCount++;
                break;
            }
        }
    }

    public void Remove(int index)
    {
        switch (RecipeRepository.currentCategory) {
            case Entrees: {
                entreeRecipeList.remove(index);
                break;
            }
            case Appetizers: {
                appetizerRecipeList.remove(index);
                break;
            }
            case Desserts: {
                dessertRecipeList.remove(index);
                break;
            }
            case Drinks: {
                drinkRecipeList.remove(index);
                break;
            }
        }
    }

}
