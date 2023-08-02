package com.zybooks.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zybooks.recipebook.repo.RecipeRepository;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView textViewRecipeName = findViewById(R.id.chosen_recipe);
        textViewRecipeName.setText(RecipeRepository.currentRecipe.getRecipeName());


        TextView textViewIngredients = findViewById(R.id.chosen_ingredients);
        textViewIngredients.setText(RecipeRepository.currentRecipe.getIngredients());

        TextView textViewInstructions = findViewById(R.id.chosen_instructions);
        textViewInstructions.setText(RecipeRepository.currentRecipe.getInstructions());

        ImageView imageViewRecipe = findViewById(R.id.image_recipe);
        imageViewRecipe.setImageURI(Uri.parse(RecipeRepository.currentRecipe.getImageUri()));

    }
}