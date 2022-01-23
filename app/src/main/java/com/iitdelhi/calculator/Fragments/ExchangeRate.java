package com.iitdelhi.calculator.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iitdelhi.calculator.Adapter.RecyclerAdapter;
import com.iitdelhi.calculator.R;
import com.iitdelhi.calculator.model.saved_calc;

import java.util.ArrayList;

public class ExchangeRate extends Fragment {


    RecyclerView past_calculation_view;
    ArrayList<saved_calc> calcs ;

    public ExchangeRate() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exchange_rate, container, false);
        init_func(view);

        calcs.add(new saved_calc("2*3-2","4"));
        calcs.add(new saved_calc("2*3-2","4"));
        calcs.add(new saved_calc("2*3-2","4"));
        RecyclerAdapter adapter = new RecyclerAdapter(calcs,this.getActivity());
        past_calculation_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        past_calculation_view.setAdapter(adapter);
        return view;

    }

    private void init_func(View view) {
        past_calculation_view = view.findViewById(R.id.fakelist);
        calcs = new ArrayList<>();

    }
}