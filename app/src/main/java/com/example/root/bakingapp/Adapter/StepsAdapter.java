package com.example.root.bakingapp.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.root.bakingapp.R;
import com.example.root.bakingapp.pojo.Step;

import java.util.ArrayList;

/**
 * Created by root on 12/20/17.
 */


public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.RecyclerHolder> {

    Context context;
    private ArrayList<Step> steps;
    public int[] trackers;


    public StepsAdapter(Context context, ArrayList<Step> steps, int[] trackers) {
        this.context = context;
        this.steps = steps;
        this.trackers = trackers;
    }


    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_steps, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, final int position) {

        holder.title.setText(steps.get(position).getShortDescription());
        if(trackers[position]==1)
        {
            holder.root.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent));
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.white));
        }
        else{
            holder.root.setBackgroundColor(ContextCompat.getColor(context,R.color.gray));
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView title;
        RelativeLayout root;

        RecyclerHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            root = (RelativeLayout) itemView.findViewById(R.id.root);
        }
    }


}

