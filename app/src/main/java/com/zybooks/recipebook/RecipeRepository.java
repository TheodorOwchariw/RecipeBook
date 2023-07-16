package com.zybooks.recipebook;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {


    private static RecipeRepository instance;
    private List<Recipe> mRecipeList;

    public RecipeRepository getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new RecipeRepository(context);
        }
        return instance;
    }

    private RecipeRepository(Context context)
    {
        mRecipeList = new ArrayList<>();

    }


}
