package com.iitdelhi.calculator.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iitdelhi.calculator.Adapter.RecyclerAdapter;
import com.iitdelhi.calculator.R;
import com.iitdelhi.calculator.model.calculator;
import com.iitdelhi.calculator.model.saved_calc;

import java.util.ArrayList;
import java.util.Arrays;


public class Calculator extends Fragment {
    TextView editField;
    char[] expression;
    String dispExp;
    int len;
    TextView result,sin,cos,tan,log,ln,cbracket;
    ImageView multiply, add, subtract, backSpace;
    TextView Equal, tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv00, clear, devide,dot;

    RecyclerView past_calculation_view;
    ArrayList<saved_calc> calcs ;

    public Calculator() {
        expression = new char[50];   //to add function to increase the size of array;
        dispExp = "";
        len = 0;
        calcs = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        initializeElements(view);

        SharedPreferences sp = getActivity().getSharedPreferences("saved_calc_list",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        RecyclerAdapter adapter = new RecyclerAdapter(calcs,getActivity());
        past_calculation_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        past_calculation_view.setAdapter(adapter);

        return view;
    }

    public void calculate() {
        calculator calcy = new calculator();
        char[] charExp = Arrays.copyOf(expression, len);
        try {
            double ans = calcy.calculate(0, 2, charExp);
            result.setText(String.valueOf(ans));

        } catch (Exception e) {
            result.setText("Expression error");
        }
    }

    public void addNum(View v) {
        String cmd = v.getTag().toString().trim();
        int x;
        if(cmd.equals("C")) {
            dispExp = "";
            expression = new char[50];
            len=0;
            result.setText(null);
        }
        else if(cmd.equals("B")){ //unfinished
            if (len == 0) {
                result.setText(null);
                return;
            }
            else if (len == 1) {
                dispExp = "";
                expression = new char[50];
                len =0;
            }
            else if(expression[len-1] == '('){
                expression[len-1] ='\0';
                dispExp = dispExp.substring(0,dispExp.length()-1);
                len--;
                if(expression[len-1] == 's' ){
                    expression[len-1] = '\0';
                    len--;
                    dispExp = dispExp.substring(0,dispExp.length()-3);
                }
            }
            else {
                expression[len - 1] = '\0';
                dispExp = dispExp.substring(0,dispExp.length()-1);
                len--;
            }
            result.setText(null);
        }
        else if(cmd.equals("00")){
            expression[len] = '0';
            expression[len+1] = '0';
            len+=2;
            dispExp+=cmd;
        }
        else if((x=isFunc(cmd))!=-1){
            expression[len] = calculator.operators[x+4];
            expression[len+1] = '(';
            dispExp += calculator.FUNC_NAME[x]+"(";
            len+=2;
        }
        else{
            expression[len] = cmd.charAt(0);
            dispExp+= cmd;
            len++;
        }
        editField.setText(dispExp);
    }

    private int isFunc(String cmd) {
        int j=0;
        for(String x: calculator.FUNC_NAME){
            if(x.equals(cmd))
                return j;
            j++;
        }
        return -1;
    }



    private void initializeElements(View view) {
        editField = view.findViewById(R.id.Expression);
        result = view.findViewById(R.id.result);
        Equal = view.findViewById(R.id.equal);
        tv00 = view.findViewById(R.id.id00);
        tv0 = view.findViewById(R.id.id0);
        tv1 = view.findViewById(R.id.id1);
        tv2 = view.findViewById(R.id.id2);
        tv3 = view.findViewById(R.id.id3);
        tv4 = view.findViewById(R.id.id4);
        tv5 = view.findViewById(R.id.id5);
        tv6 = view.findViewById(R.id.id6);
        tv7 = view.findViewById(R.id.id7);
        tv8 = view.findViewById(R.id.id8);
        tv9 = view.findViewById(R.id.id9);
        add = view.findViewById(R.id.add);
        dot = view.findViewById(R.id.dot);
        sin = view.findViewById(R.id.sin);
        cos = view.findViewById(R.id.cos);
        tan = view.findViewById(R.id.tan);
        log = view.findViewById(R.id.log);
        ln = view.findViewById(R.id.ln);
        clear = view.findViewById(R.id.clear_exp);
        devide = view.findViewById(R.id.devide);
        subtract = view.findViewById(R.id.subtract);
        multiply = view.findViewById(R.id.multipy);
        cbracket = view.findViewById(R.id.cbracket);
        backSpace = view.findViewById(R.id.backspace);

        past_calculation_view = view.findViewById(R.id.past_calculation_view);
        Equal.setOnClickListener(v -> {
            if (len == 0)
                return;
            calculate();
        });

        tv0.setOnClickListener(this::addNum);
        tv1.setOnClickListener(this::addNum);
        tv2.setOnClickListener(this::addNum);
        tv3.setOnClickListener(this::addNum);
        tv4.setOnClickListener(this::addNum);
        tv5.setOnClickListener(this::addNum);
        tv6.setOnClickListener(this::addNum);
        tv7.setOnClickListener(this::addNum);
        tv8.setOnClickListener(this::addNum);
        tv9.setOnClickListener(this::addNum);
        tv00.setOnClickListener(this::addNum);
        sin.setOnClickListener(this::addNum);
        cos.setOnClickListener(this::addNum);
        tan.setOnClickListener(this::addNum);
        log.setOnClickListener(this::addNum);
        ln.setOnClickListener(this::addNum);
        sin.setOnClickListener(this::addNum);
        clear.setOnClickListener(this::addNum);
        multiply.setOnClickListener(this::addNum);
        backSpace.setOnClickListener(this::addNum);
        subtract.setOnClickListener(this::addNum);
        add.setOnClickListener(this::addNum);
        devide.setOnClickListener(this::addNum);
        cbracket.setOnClickListener(this::addNum);
        dot.setOnClickListener(this::addNum);

        backSpace.setOnLongClickListener(v -> {
            editField.setText("");
            result.setText("");
            expression = new char[50];
            dispExp = "";
            return true;
        });
    }

}
