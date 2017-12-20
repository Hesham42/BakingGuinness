package com.example.root.bakingapp.Service;


import com.example.root.bakingapp.pojo.Recipe;

import java.util.List;

import retrofit2.Response;

/**
 * Created by root on 12/19/17.
 */

public interface Comm {
    void onFailure(String message);

    void onResponse(Response<List<Recipe>> response);
}
