package com.example.root.bakingapp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.root.bakingapp.Adapter.RecipesAdapter;
import com.example.root.bakingapp.R;
import com.example.root.bakingapp.Service.Client;
import com.example.root.bakingapp.Service.Comm;
import com.example.root.bakingapp.Utilites.RecyclerTouchListener;
import com.example.root.bakingapp.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements Comm {

    ArrayList<Recipe> recipes;
    @BindView(R.id.recipesList)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressbar;
    @BindView(R.id.reload)
    Button reload;

    int position=0;

    private CountingIdlingResource mIdlingResource = new CountingIdlingResource("Loading_Data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mIdlingResource.increment();
        if (savedInstanceState == null) {
            mIdlingResource.increment();
            Client.getRecipes(this);
        }


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, recyclerView, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent details = new Intent(MainActivity.this, RecipeDetailsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(getResources().getString(R.string.steps),
                        (ArrayList<? extends Parcelable>) recipes.get(position).getSteps());
                bundle.putParcelableArrayList(getResources().getString(R.string.ingredients),
                        (ArrayList<? extends Parcelable>) recipes.get(position).getIngredients());
                bundle.putString(getResources().getString(R.string.recipe_name), recipes.get(position).getName());
                details.putExtra(getResources().getString(R.string.bundle), bundle);

                startActivity(details);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


    @Override
    public void onFailure(String message) {
        progressbar.setVisibility(GONE);
        reload.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, "No internet connection !", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(Response<List<Recipe>> response) {

        recipes = (ArrayList<Recipe>) response.body();

        reload.setVisibility(GONE);
        progressbar.setVisibility(GONE);

        renderRecyclerView();


    }

    public void renderRecyclerView() {
        if (recyclerView.getTag().equals("tablet")) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            LinearLayoutManager gridLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        recyclerView.setAdapter(new RecipesAdapter(MainActivity.this, recipes));
        recyclerView.getLayoutManager().scrollToPosition(position);
        mIdlingResource.decrement();

    }

    public void reload(View view) {
        progressbar.setVisibility(View.VISIBLE);
        reload.setVisibility(GONE);
        Client.getRecipes(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(getResources().getString(R.string.recipes), recipes);
        if (recyclerView.getTag().equals("tablet")) {
            outState.putInt(getResources().getString(R.string.position), ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition());
        } else {
            outState.putInt(getResources().getString(R.string.position), ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        recipes = savedInstanceState.getParcelableArrayList(getResources().getString(R.string.recipes));
        position = savedInstanceState.getInt(getResources().getString(R.string.position));
        renderRecyclerView();
        progressbar.setVisibility(GONE);
    }


    @VisibleForTesting
    @NonNull
    public CountingIdlingResource getIdlingResource() {
        return mIdlingResource;
    }

}
