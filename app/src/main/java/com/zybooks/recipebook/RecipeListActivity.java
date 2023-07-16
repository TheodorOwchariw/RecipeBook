    package com.zybooks.recipebook;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.TextView;

    public class RecipeListActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recipe_list);

            Intent intent = getIntent();
            String category = intent.getStringExtra(MainActivity.EXTRA_CATEGORY);

            // Find the TextView in the layout
            TextView chosenCategory = (TextView) findViewById(R.id.chosen_category);

            // Set the text of the TextView to the category
            chosenCategory.setText(category);
        }


    }