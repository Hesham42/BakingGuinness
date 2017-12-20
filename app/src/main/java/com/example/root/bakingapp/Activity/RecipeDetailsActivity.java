package com.example.root.bakingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.root.bakingapp.Fragment.StepFragment;
import com.example.root.bakingapp.Fragment.VedioFragment;
import com.example.root.bakingapp.R;
import com.example.root.bakingapp.Utilites.FragmentOneListener;
import com.example.root.bakingapp.pojo.Step;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity implements FragmentOneListener {

    FrameLayout frameLayout;
    boolean Tablet;
    private ArrayList<Step> steps;

    String name;

    VedioFragment vedioFragment;

    StepFragment stepFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        frameLayout = (FrameLayout) findViewById(R.id.fragmentTwo);
        Tablet = true;
        Bundle extras = getIntent().getBundleExtra(getResources().getString(R.string.bundle));
        name = extras.getString(getResources().getString(R.string.recipe_name));
        getSupportActionBar().setTitle(name);
        steps = extras.getParcelableArrayList(getResources().getString(R.string.steps));
        extras.putBoolean(getResources().getString(R.string.tablet), (frameLayout != null));

        if (savedInstanceState == null) {
            stepFragment = new StepFragment();
            stepFragment.setFragmentListener(this);
            stepFragment.setArguments(extras);
            getFragmentManager().beginTransaction().add(R.id.fragmentOne, stepFragment).commit();
            //checking if screen size greater than 600dp
            if (frameLayout == null) {
                Tablet = false;
            } else {
                this.setStep(0, steps);
            }
        } else {
            stepFragment = (StepFragment) getFragmentManager().getFragment(savedInstanceState,"main");
            stepFragment.setFragmentListener(this);


            if (!stepFragment.isAdded())
                getFragmentManager().beginTransaction().add(R.id.fragmentOne, stepFragment).commit();

            if(vedioFragment !=null)
            {
                vedioFragment = (VedioFragment) getFragmentManager().getFragment(savedInstanceState,"detail");
                getFragmentManager().beginTransaction().replace(R.id.fragmentTwo, vedioFragment).commit();
            }
        }




    }

    @Override
    public void setStep(int index, ArrayList<Step> steps) {
        if (!Tablet) {
            Intent intent = new Intent(this, StepDetailsActivity.class);
            intent.putExtra(getResources().getString(R.string.steps), steps);
            intent.putExtra(getResources().getString(R.string.current), index);
            intent.putExtra(getResources().getString(R.string.name), name);
            startActivity(intent);
        } else {
            vedioFragment = new VedioFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(getResources().getString(R.string.steps), steps);
            vedioFragment.setFragmentListener(this);
            bundle.putInt(getResources().getString(R.string.current), index);
            bundle.putBoolean(getResources().getString(R.string.tablet), true);
            vedioFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.fragmentTwo, vedioFragment).commit();
        }
    }

    @Override
    public void setCurrent(int index) {
        if (Tablet) {
            stepFragment.updateView(index);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, getResources().getString(R.string.main), stepFragment);

        if (Tablet && frameLayout !=null)
        {
            try{
                getFragmentManager().putFragment(outState, getResources().getString(R.string.detail), vedioFragment);
            }catch (NullPointerException e) {}

        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (frameLayout == null) {
            Tablet = false;
        }
    }
}
