    package com.zybooks.recipebook;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;

    import android.app.LauncherActivity;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
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

        private final AtomicInteger mainFabFlag= new AtomicInteger(0);
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


            // Find the TextView in the layout
            TextView chosenCategory = (TextView) findViewById(R.id.chosen_category);

            // Load the category from SharedPreferences
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            String category = sharedPref.getString("chosen_category", "");



            // Set the text of the TextView to the category from SharedPreferences
            chosenCategory.setText(category);

            //RecipeRepository.currentCategory = Enum.valueOf(RecipeRepository.Category.class, category);

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

            ArrayList<Recipe> recipeList = new ArrayList<>();

            switch (category) {
                case "Entrees":
                {
                    RecipeRepository.currentCategory = RecipeRepository.Category.Entrees;
                    recipeList = RecipeRepository.getInstance(getApplicationContext()).getEntreeRecipeList();
                    RecipeRepository.currentCategory = RecipeRepository.Category.Entrees;
                    break;
                }
                case "Appetizers":
                {

                    RecipeRepository.currentCategory = RecipeRepository.Category.Appetizers;
                    recipeList = RecipeRepository.getInstance(getApplicationContext()).getAppetizerRecipeList();
                    RecipeRepository.currentCategory = RecipeRepository.Category.Appetizers;
                    break;
                }
                case "Desserts":
                {
                    RecipeRepository.currentCategory = RecipeRepository.Category.Desserts;
                    recipeList = RecipeRepository.getInstance(getApplicationContext()).getDessertRecipeList();
                    RecipeRepository.currentCategory = RecipeRepository.Category.Desserts;
                    break;
                }
                case "Drinks":
                {
                    RecipeRepository.currentCategory = RecipeRepository.Category.Drinks;
                    recipeList = RecipeRepository.getInstance(getApplicationContext()).getDrinkRecipeList();
                    RecipeRepository.currentCategory = RecipeRepository.Category.Drinks;
                    break;
                }
            }

            //ArrayList<Recipe> recipeList = RecipeRepository.getInstance(getApplicationContext()).getEntreeRecipeList();

            // Create adapter passing in the recipe list
            adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    recipeList);

            // Attach the adapter to the listview to populate items
            recipeListView.setAdapter(adapter);

            recipeListView.setOnItemClickListener((parent, view, position, id) -> {
                selectedPosition.set(position);
                //delete
                if (deleteFlag.get() == 1)
                {
                    Recipe recipeToDelete = adapter.getItem(selectedPosition.get());
                    RecipeRepository.getInstance(getApplicationContext()).Remove(recipeToDelete);
                    adapter.remove(recipeToDelete);
                    adapter.notifyDataSetChanged();
                    deleteFlag.set(0);
                }
                // update
                else if (editFlag.get() == 1) {
//                    RecipeRepository.currentRecipe = adapter.getItem(selectedPosition.get());
//                    View editFragmentView = getLayoutInflater().inflate(R.layout.fragment_recipe_edit, null);
//                    setContentView(editFragmentView);
//                    editFlag.set(0);


                    // !!!! **** !!!! 5.1.3
                    RecipeRepository.currentRecipe = adapter.getItem(selectedPosition.get());
                    RecipeEditFragment recipeEditFragment = new RecipeEditFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, recipeEditFragment); // replace the current fragment with recipeEditFragment
                    transaction.addToBackStack(null); // if you want the previous fragment to be added to the backstack
                    transaction.commit(); // commit the transaction
                    editFlag.set(0);


                }
                // read
                else
                {
                    RecipeRepository.currentRecipe = adapter.getItem(selectedPosition.get());
                    Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
                    startActivity(detailIntent);
                }
            });


            FloatingActionButton mainFab = findViewById(R.id.fab_main);
            FloatingActionButton fab1= findViewById(R.id.fab1);
            FloatingActionButton fab2= findViewById(R.id.fab2);
            FloatingActionButton fab3= findViewById(R.id.fab3);
            fab1.hide();
            fab2.hide();
            fab3.hide();

            mainFab.setOnClickListener(view -> {
                if(mainFabFlag.get()==0) {
                    fab1.show();
                    fab2.show();
                    fab3.show();
                    mainFabFlag.incrementAndGet();
                    mainFab.setImageDrawable(getDrawable(R.drawable.downarrow));
                }
                else
                {
                    fab1.hide();
                    fab2.hide();
                    fab3.hide();
                    mainFabFlag.decrementAndGet();
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

//        @Override
//        public void onBackPressed() {
//            super.onBackPressed();
//            setContentView(R.layout.activity_recipe_list);
//        }

        private void refreshRecipeList() {
                ArrayList<Recipe> updatedRecipeList = new ArrayList<>();

                switch (RecipeRepository.currentCategory) {
                    case Entrees: {
                        updatedRecipeList = RecipeRepository.getInstance(getApplicationContext()).getEntreeRecipeList();
                        break;
                    }
                    case Appetizers: {
                        updatedRecipeList = RecipeRepository.getInstance(getApplicationContext()).getAppetizerRecipeList();
                        break;
                    }
                    case Desserts: {
                        updatedRecipeList = RecipeRepository.getInstance(getApplicationContext()).getDessertRecipeList();
                        break;
                    }
                    case Drinks: {
                        updatedRecipeList = RecipeRepository.getInstance(getApplicationContext()).getDrinkRecipeList();
                        break;
                    }
                }

            ListView listView = findViewById(R.id.recipe_list);

            // Replace the adapter with a new one, instead of clearing and adding to the old one
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updatedRecipeList);
                listView.setAdapter(adapter);
            }

    }

