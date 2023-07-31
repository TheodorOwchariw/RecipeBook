// textbook code
package com.zybooks.recipebook.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.zybooks.recipebook.model.Recipe;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class  RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}
