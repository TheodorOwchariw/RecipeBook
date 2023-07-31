// textbook code
package com.zybooks.recipebook;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.zybooks.recipebook.Recipe;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class  RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}
