package com.zybooks.recipebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "com.zybooks.lightsout.string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onCategoryClicked(View view)
    {
        Intent intent = new Intent(this, RecipeListActivity.class);
        Button clickedButton = (Button) view;
        String category = clickedButton.getText().toString();
        intent.putExtra(EXTRA_CATEGORY, category);
        startActivity(intent);
    }
}