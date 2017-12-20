package com.example.root.bakingapp.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.bakingapp.Adapter.IngredientsAdapter;
import com.example.root.bakingapp.Adapter.StepsAdapter;
import com.example.root.bakingapp.R;
import com.example.root.bakingapp.Utilites.FragmentOneListener;
import com.example.root.bakingapp.Utilites.RecyclerTouchListener;
import com.example.root.bakingapp.pojo.Ingredient;
import com.example.root.bakingapp.pojo.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepFragment extends android.app.Fragment {
    FragmentOneListener listener;

    @BindView(R.id.stepsList)
    RecyclerView recycler;
    @BindView(R.id.ingredientList)
    RecyclerView ingredientList;

    ArrayList<Step> steps;
    ArrayList<Ingredient> ingredients;

    int[]trackers;
    int index;

    boolean tablet;
    LinearLayoutManager ingredientsManager,stepsManager;

    int p1, p2;

    public void setFragmentListener(FragmentOneListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.step_fragment, container, false);

        ButterKnife.bind(this, root);

        ingredientsManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        stepsManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        if(savedInstanceState ==null)
        {
            Bundle extra = getArguments();
            ingredients = extra.getParcelableArrayList(getResources().getString(R.string.ingredients));
            tablet=extra.getBoolean(getResources().getString(R.string.tablet),false);
            steps = extra.getParcelableArrayList(getResources().getString(R.string.steps));

            index=0;
        }
        else
        {
            ingredients = savedInstanceState.getParcelableArrayList(getResources().getString(R.string.ingredients));
            tablet=savedInstanceState.getBoolean(getResources().getString(R.string.tablet),false);
            steps = savedInstanceState.getParcelableArrayList(getResources().getString(R.string.steps));
            index=savedInstanceState.getInt(getResources().getString(R.string.position));

            p1 =savedInstanceState.getInt(getResources().getString(R.string.p1));
            p2 =savedInstanceState.getInt(getResources().getString(R.string.p2));
        }
        trackers=new int[steps.size()];
        if(tablet)
        {
            trackers[index]=1;
        }


        ingredientList.setLayoutManager(ingredientsManager);
        ingredientList.setAdapter(new IngredientsAdapter(getActivity(), ingredients));
        if(p1 !=0)
        {
            ingredientList.getLayoutManager().scrollToPosition(p1);
        }


        recycler.setLayoutManager(stepsManager);
        recycler.setAdapter(new StepsAdapter(getActivity(), steps,trackers));
        if(p2 !=0)
        {
            recycler.getLayoutManager().scrollToPosition(p2);
        }

        recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recycler, new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        listener.setStep(position, steps);
                        updateView(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        if(tablet){
            updateView(index);
            listener.setStep(index, steps);
        }

        return root;
    }


    public void updateView(int index)
    {
        this.index=index;
        if(!tablet)
        {
            return;
        }
        trackers=new int[steps.size()];
        try{
            trackers[index]=1;
            ((StepsAdapter)recycler.getAdapter()).trackers=trackers;
            recycler.getAdapter().notifyDataSetChanged();
            recycler.scrollToPosition(index);
        }catch (ArrayIndexOutOfBoundsException E)
        {

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getResources().getString(R.string.ingredients),ingredients);
        outState.putParcelableArrayList(getResources().getString(R.string.steps),steps);
        outState.putBoolean(getResources().getString(R.string.tablet),tablet);

        outState.putInt(getResources().getString(R.string.position),index);

        outState.putInt(getResources().getString(R.string.p1),((LinearLayoutManager)ingredientList.getLayoutManager()).findFirstCompletelyVisibleItemPosition());
        outState.putInt(getResources().getString(R.string.p2),((LinearLayoutManager)recycler.getLayoutManager()).findFirstCompletelyVisibleItemPosition());


    }
}


