package com.example.root.bakingapp.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.root.bakingapp.Fragment.VedioFragment;
import com.example.root.bakingapp.R;


public class StepDetailsActivity extends AppCompatActivity {

    VedioFragment stepFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        if(savedInstanceState == null)
        {
            Bundle bundle = getIntent().getExtras();

            if(bundle.containsKey(getResources().getString(R.string.name)))
            {
                getSupportActionBar().setTitle(bundle.getString(getResources().getString(R.string.name))+getResources().getString(R.string.steps));
            }
            bundle.putBoolean(getResources().getString(R.string.tablet),false);

            stepFragment = new VedioFragment();
            stepFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .add(R.id.detailsFragment, stepFragment)
                    .commit();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getFragmentManager().putFragment(outState,getResources().getString(R.string.fragment), stepFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        stepFragment = (VedioFragment) getFragmentManager().getFragment(savedInstanceState,getResources().getString(R.string.fragment));
        if(stepFragment.isAdded())
        {
            return;
        }
        getFragmentManager().beginTransaction()
                .add(R.id.detailsFragment, stepFragment)
                .commit();
    }

}
