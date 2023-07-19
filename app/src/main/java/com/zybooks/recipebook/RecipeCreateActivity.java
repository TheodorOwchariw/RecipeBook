package com.zybooks.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RecipeCreateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_create);
    }

    public void onCreateClicked (View view)
    {
        Recipe recipe = new Recipe();
        recipe.setIndex(RecipeRepository.entreeCount);
        recipe.setRecipeName("Chips " + RecipeRepository.entreeCount);
        RecipeRepository.getInstance().Add(recipe);
        RecipeRepository.entreeCount++;
    }

}