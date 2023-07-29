package com.zybooks.recipebook;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    //used to keep track of what the current category is so it can display the correct list
    public enum Category {Entrees, Appetizers, Desserts, Drinks}


    //private static final int NUMBER_OF_THREADS = 4;
    private final RecipeDao recipeDao;
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

    public static RecipeRepository getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new RecipeRepository(context);
        }
        return instance;
    }

    public RecipeRepository(Context context)
    {
        entreeRecipeList = new ArrayList<>();
        appetizerRecipeList = new ArrayList<>();
        dessertRecipeList = new ArrayList<>();
        drinkRecipeList = new ArrayList<>();
        entreeCount = 0;
        appetizerCount = 0;
        dessertCount = 0;
        drinkCount = 0;

        RecipeDatabase database = Room.databaseBuilder(context, RecipeDatabase.class, "recipe.db")
                .allowMainThreadQueries()
                //.addCallback(databaseCallback) used for threading comment out line above when working on threading
                .build();

        recipeDao = database.recipeDao();
    }

    //getters


    /* public ArrayList<Recipe> getRecipes()
    {
        return new ArrayList<>(recipeDao.getRecipes());
    } */
    public ArrayList<Recipe> getEntreeRecipeList()
    {
        entreeRecipeList = new ArrayList<>(recipeDao.getRecipes("Entrees"));
        return entreeRecipeList;
    }

    public ArrayList<Recipe> getAppetizerRecipeList()
    {
        appetizerRecipeList = new ArrayList<>(recipeDao.getRecipes("Appetizers"));
        return appetizerRecipeList;
    }

    public ArrayList<Recipe> getDessertRecipeList()
    {
        dessertRecipeList = new ArrayList<>(recipeDao.getRecipes("Desserts"));
        return dessertRecipeList;
    }

    public ArrayList<Recipe> getDrinkRecipeList()
    {
        drinkRecipeList = new ArrayList<>(recipeDao.getRecipes("Drinks"));
        return drinkRecipeList;
    }
    public int getEntreeCount() {return entreeCount;}
    public int getAppetizerCount() {return appetizerCount;}
    public int getDessertCount() {return dessertCount;}
    public int getDrinkCount() {return drinkCount;}

    public void Add(Recipe recipe)
    {
        /*
        switch (RecipeRepository.currentCategory) {
            case Entrees: {
                recipeDao.addRecipe(recipe);
                //entreeRecipeList.add(recipe);
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
        } */
        recipeDao.addRecipe(recipe);
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

    public void Remove(Recipe recipe)
    {
//        switch (RecipeRepository.currentCategory) {
//            case Entrees: {
//                //entreeRecipeList.remove(index);
//                recipeDao.deleteRecipe(recipe);
//                break;
//            }
//            case Appetizers: {
//                //appetizerRecipeList.remove(index);
//                break;
//            }
//            case Desserts: {
//                //dessertRecipeList.remove(index);
//                break;
//            }
//            case Drinks: {
//                //drinkRecipeList.remove(index);
//                break;
//            }
//        }
        recipeDao.deleteRecipe(recipe);
    }

}
