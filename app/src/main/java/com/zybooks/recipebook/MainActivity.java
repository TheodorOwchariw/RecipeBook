package com.zybooks.recipebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zybooks.recipebook.model.Recipe;
import com.zybooks.recipebook.repo.RecipeRepository;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "com.zybooks.recipebook.string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Recipe> entreeList = RecipeRepository.getInstance(getApplicationContext()).getEntreeRecipeList();
        ArrayList<Recipe> appetizerList = RecipeRepository.getInstance(getApplicationContext()).getAppetizerRecipeList();
        ArrayList<Recipe> dessertList = RecipeRepository.getInstance(getApplicationContext()).getDessertRecipeList();
        ArrayList<Recipe> drinkList = RecipeRepository.getInstance(getApplicationContext()).getDrinkRecipeList();

        ImageView entreeImageView = findViewById(R.id.image_entreeCat);
        ImageView appetizerImageView = findViewById(R.id.image_appetizerCat);
        ImageView dessertImageButton = findViewById(R.id.image_dessertCat);
        ImageView drinkImageButton = findViewById(R.id.image_drinkCat);

        if (!entreeList.isEmpty())
        {
            entreeImageView.setImageURI(Uri.parse(entreeList.get(0).getImageUri()));
            appetizerImageView.setImageURI(Uri.parse(appetizerList.get(0).getImageUri()));
            dessertImageButton.setImageURI(Uri.parse(dessertList.get(0).getImageUri()));
            drinkImageButton.setImageURI(Uri.parse(drinkList.get(0).getImageUri()));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    /*
        ArrayList<Recipe> entreeList = RecipeRepository.getInstance(getApplicationContext()).getEntreeRecipeList();
        ArrayList<Recipe> appetizerList = RecipeRepository.getInstance(getApplicationContext()).getAppetizerRecipeList();
        ArrayList<Recipe> dessertList = RecipeRepository.getInstance(getApplicationContext()).getDessertRecipeList();
        ArrayList<Recipe> drinkList = RecipeRepository.getInstance(getApplicationContext()).getDrinkRecipeList();

        ImageView entreeImageView = findViewById(R.id.image_entreeCat);
        ImageView appetizerImageView = findViewById(R.id.image_appetizerCat);
        ImageView dessertImageButton = findViewById(R.id.image_dessertCat);
        ImageView drinkImageButton = findViewById(R.id.image_drinkCat);

        entreeImageView.setImageURI(Uri.parse(entreeList.get(0).getImageUri()));
        appetizerImageView.setImageURI(Uri.parse(appetizerList.get(0).getImageUri()));
        dessertImageButton.setImageURI(Uri.parse(dessertList.get(0).getImageUri()));
        drinkImageButton.setImageURI(Uri.parse(drinkList.get(0).getImageUri()));
        */
    }

    public void onCategoryClicked(View view)
    {
      //  RecipeRepository.getInstance(getApplicationContext()).currentCategory =
        //textbook example
        Intent intent = new Intent(this, RecipeListActivity.class);
        Button clickedButton = (Button) view;
        String category = clickedButton.getText().toString();
        intent.putExtra(EXTRA_CATEGORY, category);
        startActivity(intent);
    }

}