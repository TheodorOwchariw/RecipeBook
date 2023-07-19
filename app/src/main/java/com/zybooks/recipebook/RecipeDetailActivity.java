package com.zybooks.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView textViewRecipeName = findViewById(R.id.chosen_recipe);
        textViewRecipeName.setText(RecipeRepository.currentRecipe.getRecipeName());

    }
}