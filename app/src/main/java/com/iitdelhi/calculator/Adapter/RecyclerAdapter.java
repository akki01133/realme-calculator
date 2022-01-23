package com.iitdelhi.calculator.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iitdelhi.calculator.R;
import com.iitdelhi.calculator.model.saved_calc;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {
    ArrayList<saved_calc> saved_calcs ;
    Activity activity;

    public RecyclerAdapter(ArrayList<saved_calc> saved_calcs, Activity activity) {
        this.saved_calcs = saved_calcs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.sample_recycle_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        saved_calc calc = saved_calcs.get(position);
        holder.expn.setText(calc.getExpression());
        holder.rslt.setText(calc.getResult());
    }

    @Override
    public int getItemCount() {
        return saved_calcs.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView expn,rslt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            expn = itemView.findViewById(R.id.saved_expn);
            rslt = itemView.findViewById(R.id.saved_rslt);
        }
    }
}
