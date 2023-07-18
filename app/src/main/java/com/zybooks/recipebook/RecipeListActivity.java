    package com.zybooks.recipebook;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.android.material.floatingactionbutton.FloatingActionButton;

    import java.util.concurrent.atomic.AtomicInteger;

    public class RecipeListActivity extends AppCompatActivity {

        private final AtomicInteger clicked= new AtomicInteger(1);
        // atomic integer for onEditClicked
        // atomic integer for onDeleteClicked

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recipe_list);

            // Find the TextView in the layout
            TextView chosenCategory = (TextView) findViewById(R.id.chosen_category);

            // Load the category from SharedPreferences
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            String category = sharedPref.getString("chosen_category", "");

            // Set the text of the TextView to the category from SharedPreferences
            chosenCategory.setText(category);

            Intent intent = getIntent();
            String newCategory = intent.getStringExtra(MainActivity.EXTRA_CATEGORY);

            // Check if a new category was received from MainActivity
            // ** CHATGPT **
            if (newCategory != null && !newCategory.equals("")) {
                // Update the category with new value from MainActivity
                category = newCategory;

                // Update the TextView
                chosenCategory.setText(category);

                // Save the new category in SharedPreferences
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("chosen_category", category);
                editor.apply();
            }

            FloatingActionButton mainFab = findViewById(R.id.fab_main);
            FloatingActionButton fab1= findViewById(R.id.fab1);
            FloatingActionButton fab2= findViewById(R.id.fab2);
            FloatingActionButton fab3= findViewById(R.id.fab3);
            fab1.hide();
            fab2.hide();
            fab3.hide();

            mainFab.setOnClickListener(view -> {
                if(clicked.get()==1) {
                    fab1.show();
                    fab2.show();
                    fab3.show();
                    clicked.decrementAndGet();
                }
                else
                {
                    fab1.hide();
                    fab2.hide();
                    fab3.hide();
                    clicked.incrementAndGet();
                }
            });

            //delete fab on click
            fab1.setOnClickListener(view -> {
                Toast.makeText(this, "Touch recipe to delete", Toast.LENGTH_SHORT).show();
                // code that highlights scroll area to help the user realize to select a recipe
                // code that deletes recipe

            });

            //edit fab on click
            fab2.setOnClickListener(view -> {

                Toast.makeText(this, "Touch recipe to edit", Toast.LENGTH_SHORT).show();
                // code that highlights scroll area to help the user realize to select a recipe\

                Intent addIntent = new Intent(this, RecipeEditActivity.class);
                startActivity(addIntent);

            });

            //add fab on click
            fab3.setOnClickListener(view -> {
                Intent addIntent = new Intent(this, RecipeCreateActivity.class);
                startActivity(addIntent);
            });
        }
    }