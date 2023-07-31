// textbook code
package com.zybooks.recipebook.repo;

import androidx.room.*;
import com.zybooks.recipebook.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM Recipe WHERE category_name = :category ORDER BY name COLLATE NOCASE")
    List<Recipe> getRecipes(String category);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addRecipe(Recipe recipe);
    @Update
    void updateRecipe(Recipe recipe);
    @Delete
    void deleteRecipe(Recipe recipe);
}
