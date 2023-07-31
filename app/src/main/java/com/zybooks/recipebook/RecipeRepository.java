package com.zybooks.recipebook;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RecipeRepository {

    //used to keep track of what the current category is so it can display the correct list
    public enum Category {Entrees, Appetizers, Desserts, Drinks}
    private static final int NUMBER_OF_THREADS = 4;

    //Zybooks 7.2.2
    private static final ExecutorService mDatabaseExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private final RecipeDao recipeDao;
    public static Category currentCategory;
    public static Recipe currentRecipe;
    private static RecipeRepository instance;

//    private ArrayList<Recipe> entreeRecipeList;
//    private ArrayList<Recipe> appetizerRecipeList;
//    private ArrayList<Recipe> dessertRecipeList;
//    private ArrayList<Recipe> drinkRecipeList;

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
//        entreeRecipeList = new ArrayList<>();
//        appetizerRecipeList = new ArrayList<>();
//        dessertRecipeList = new ArrayList<>();
//        drinkRecipeList = new ArrayList<>();

        //Zybooks 7.2.3
        RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };

        RecipeDatabase database = Room.databaseBuilder(context, RecipeDatabase.class, "recipe.db")
                .allowMainThreadQueries()
                .addCallback(databaseCallback) //used for threading comment out line above when working on threading
                .build();

        recipeDao = database.recipeDao();
    }

    //getters
    public ArrayList<Recipe> getEntreeRecipeList()
    {
        //entreeRecipeList = new ArrayList<>(recipeDao.getRecipes("Entrees"));
        //return entreeRecipeList;
        return new ArrayList<>(recipeDao.getRecipes("Entrees"));
    }

    public ArrayList<Recipe> getAppetizerRecipeList()
    {
        //appetizerRecipeList = new ArrayList<>(recipeDao.getRecipes("Appetizers"));
        return new ArrayList<>(recipeDao.getRecipes("Appetizers"));
        //return appetizerRecipeList;
    }

    public ArrayList<Recipe> getDessertRecipeList()
    {
        return new ArrayList<>(recipeDao.getRecipes("Desserts"));
        //return dessertRecipeList;
    }

    public ArrayList<Recipe> getDrinkRecipeList()
    {
        return new ArrayList<>(recipeDao.getRecipes("Drinks"));
        //return drinkRecipeList;
    }


    //adding, updating, or deleting anything from database runs on background thread
    public void Add(Recipe recipe)
    {
        mDatabaseExecutor.execute(() -> recipeDao.addRecipe(recipe));
    }

    public void Update(Recipe recipe)
    {
        mDatabaseExecutor.execute(() -> recipeDao.updateRecipe(recipe));
    }
    public void Remove(Recipe recipe)
    {
        mDatabaseExecutor.execute(() -> recipeDao.deleteRecipe(recipe));
    }

}
