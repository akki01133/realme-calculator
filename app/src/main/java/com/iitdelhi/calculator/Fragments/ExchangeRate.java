package com.iitdelhi.calculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iitdelhi.calculator.R;

import java.util.ArrayList;

public class ExchangeRate extends Fragment {



    public ExchangeRate() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exchange_rate, container, false);
        init_func(view);
        return view;

    }

    private void init_func(View view) {

    }
}
