package com.zybooks.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RecipeCreateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_create);
    }

    public void onCreateClicked (View view)
    {
        Recipe recipe = new Recipe();
        EditText editRecipeName = findViewById(R.id.edit_recipe_name);
        recipe.setRecipeName(editRecipeName.getText().toString());

        EditText editRecipeIngredients = findViewById(R.id.edit_recipe_ingredients);
        recipe.setIngredients(editRecipeIngredients.getText().toString());

        EditText editRecipeInstructions = findViewById(R.id.edit_recipe_instructions);
        recipe.setInstructions(editRecipeInstructions.getText().toString());

        recipe.setCategoryName((RecipeRepository.currentCategory).toString());

        RecipeRepository.getInstance(getApplicationContext()).Add(recipe);
        RecipeRepository.getInstance(getApplicationContext()).incrementCount();
    }

}