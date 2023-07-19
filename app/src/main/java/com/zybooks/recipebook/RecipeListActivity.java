    package com.zybooks.recipebook;

    import androidx.appcompat.app.AppCompatActivity;

    import android.app.LauncherActivity;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.ListView;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.android.material.floatingactionbutton.FloatingActionButton;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.concurrent.atomic.AtomicInteger;

    public class RecipeListActivity extends AppCompatActivity {

        private final AtomicInteger fabClicked= new AtomicInteger(1);
        private final AtomicInteger deleteFlag= new AtomicInteger(0);
        private final AtomicInteger editFlag = new AtomicInteger(0);
        private final AtomicInteger selectedPosition = new AtomicInteger();
        private ListView recipeListView;
        private ArrayAdapter<Recipe> adapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recipe_list);

            recipeListView = findViewById(R.id.recipe_list);

            // Get the recipe list from your repository
            ArrayList<Recipe> recipeList = RecipeRepository.getInstance().getEntreeRecipeList();

            // Create adapter passing in the recipe list
            adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    recipeList);

            // Attach the adapter to the listview to populate items
            recipeListView.setAdapter(adapter);

            recipeListView.setOnItemClickListener((parent, view, position, id) -> {
                selectedPosition.set(position);
                if (deleteFlag.get() == 1)
                {
                    Recipe recipeToDelete = adapter.getItem(selectedPosition.get());
                    RecipeRepository.getInstance().Remove(selectedPosition.get());
                    adapter.remove(recipeToDelete);
                    adapter.notifyDataSetChanged();
                    deleteFlag.set(0);
                }
                else if (editFlag.get() == 1) {
                    RecipeRepository.currentRecipe = adapter.getItem(selectedPosition.get());
                    Intent editIntent = new Intent(this, RecipeEditActivity.class);
                    startActivity(editIntent);
                    editFlag.set(0);
                }
                else
                {
                    RecipeRepository.currentRecipe = adapter.getItem(selectedPosition.get());
                    Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
                    startActivity(detailIntent);
                }
            });

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
                if(fabClicked.get()==1) {
                    fab1.show();
                    fab2.show();
                    fab3.show();
                    fabClicked.decrementAndGet();
                    mainFab.setImageDrawable(getDrawable(R.drawable.downarrow));
                }
                else
                {
                    fab1.hide();
                    fab2.hide();
                    fab3.hide();
                    fabClicked.incrementAndGet();
                    mainFab.setImageDrawable(getDrawable(R.drawable.uparrow));
                }
            });

            //delete fab on click
            fab1.setOnClickListener(view -> {
                Toast.makeText(this, "Touch recipe to delete", Toast.LENGTH_SHORT).show();
                // code that highlights scroll area to help the user realize to select a recipe
                // code that deletes recipe
                deleteFlag.set(1);
            });

            //edit fab on click
            fab2.setOnClickListener(view -> {

                Toast.makeText(this, "Touch recipe to edit", Toast.LENGTH_SHORT).show();
                // code that highlights scroll area to help the user realize to select a recipe
                editFlag.set(1);
            });

            //add fab on click
            fab3.setOnClickListener(view -> {
                Intent addIntent = new Intent(this, RecipeCreateActivity.class);
                startActivity(addIntent);
            });
        }
        @Override
        protected void onResume() {
            super.onResume();
            refreshRecipeList();
        }

        private void refreshRecipeList() {
            // Get the updated recipe list from your repository
//            switch(/* most recent recipe added */)
//            {
//
//            }
            ArrayList<Recipe> updatedRecipeList = new ArrayList<>(RecipeRepository.getInstance().getEntreeRecipeList());

            RecipeRepository.getInstance();
            // Clear the existing list in the adapter and add the new list
            adapter.clear();
            adapter.addAll(updatedRecipeList);

            // Notify the adapter that the data set has changed
            adapter.notifyDataSetChanged();
        }

    }

