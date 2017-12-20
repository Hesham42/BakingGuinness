package com.example.root.bakingapp.Utilites;


import com.example.root.bakingapp.pojo.Step;

import java.util.ArrayList;

public interface FragmentOneListener {
    void setStep(int index, ArrayList<Step> steps);
    void setCurrent(int index);
}
