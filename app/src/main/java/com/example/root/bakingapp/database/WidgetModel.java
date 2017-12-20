package com.example.root.bakingapp.database;

import com.example.root.bakingapp.pojo.Ingredient;

import java.util.ArrayList;

/**
 * Created by root on 12/20/17.
 */


public class WidgetModel {
    public String recipeTitle;
    public ArrayList<Ingredient> ingredients;

    public WidgetModel(String recipeTitle, ArrayList<Ingredient> ingredients) {
        this.recipeTitle = recipeTitle;
        this.ingredients = ingredients;
    }

}
