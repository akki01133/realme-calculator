package com.iitdelhi.calculator.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iitdelhi.calculator.R;
import com.iitdelhi.calculator.model.calculator;

import java.util.Arrays;


public class Calculator extends Fragment {
    TextView editField;
    char[] expression;
    String dispExp;
    int len;
    int ob_cnt = 0, cb_cnt = 0;  //open bracket count and closed bracket count in the expression . checked before calculating the expression.
    boolean isRAD = false;
    calculator calcy = new calculator();


    TextView result, sin, cos, tan, log, ln,pow,sqroot,fact;
    TextView cbracket, obracket, mPI, mE,inv, rad , deg;
    ImageView multiply, add, subtract, backSpace, savedCalcButton;
    TextView Equal, tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv00, clear, devide, dot;


    public Calculator() {
        expression = new char[50];   //to add function to increase the size of array;
        dispExp = "";
        len = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        initializeElements(view);
        return view;
    }

    public void calculate() {
        char[] charExp = Arrays.copyOf(expression, len);
        try {
            if (ob_cnt < cb_cnt)
                throw new Exception();
            double ans = calcy.calculate(0, 2, charExp);
            result.setText(String.valueOf(ans));

        } catch (Exception e) {
            result.setText(R.string.error_messege);
        }
    }

    public void addNum(View v) {
        String cmd = v.getTag().toString().trim();
        int x;
        if (cmd.equals("C")) {
            dispExp = "";
            expression = new char[50];
            len = 0;
            ob_cnt = 0;
            cb_cnt = 0;
            result.setText(null);
        } else if (cmd.equals("B")) { //unfinished
            if (len == 0) {
                result.setText(null);
                return;
            } else if (len == 1) {
                dispExp = "";
                expression = new char[50];
                len = 0;
            } else if (expression[len - 1] == '(') {
                expression[len - 1] = '\0';
                ob_cnt--;
                dispExp = dispExp.substring(0, dispExp.length() - 1);
                len--;
                if (expression[len - 1] == 's') {
                    expression[len - 1] = '\0';
                    len--;
                    dispExp = dispExp.substring(0, dispExp.length() - 3);
                }
            } else {
                if (expression[len - 1] == ')')
                    cb_cnt--;
                expression[len - 1] = '\0';
                dispExp = dispExp.substring(0, dispExp.length() - 1);
                len--;
            }
            result.setText(null);
        } else if (cmd.equals("00")) {
            expression[len] = '0';
            expression[len + 1] = '0';
            len += 2;
            dispExp += cmd;
        } else if ((x = isFunc(cmd)) != -1) {
            expression[len] = calculator.operators[x + 4];
            expression[len + 1] = '(';
            ob_cnt++;
            dispExp += calculator.FUNC_NAME[x] + "(";
            len += 2;
        } else {
            if (cmd.equals("("))
                ob_cnt++;
            else if (cmd.equals(")"))
                cb_cnt++;
            expression[len] = cmd.charAt(0);
            dispExp += cmd;
            len++;
        }
        editField.setText(dispExp);
    }

    private int isFunc(String cmd) {
        int j = 0;
        for (String x : calculator.FUNC_NAME) {
            if (x.equals(cmd))
                return j;
            j++;
        }
        return -1;
    }


    private void initializeElements(@NonNull View view) {
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
        fact = view.findViewById(R.id.factorial);
        pow = view.findViewById(R.id.pow);
        sqroot = view.findViewById(R.id.sqroot);
        clear = view.findViewById(R.id.clear_exp);
        devide = view.findViewById(R.id.divide);
        subtract = view.findViewById(R.id.subtract);
        multiply = view.findViewById(R.id.multipy);
        cbracket = view.findViewById(R.id.cbracket);
        obracket = view.findViewById(R.id.obracket);
        mPI = view.findViewById(R.id.pi);
        mE = view.findViewById(R.id.euler);
        inv = view.findViewById(R.id.inv);
        rad = view.findViewById(R.id.rad);
        deg = view.findViewById(R.id.deg);
        backSpace = view.findViewById(R.id.backspace);
        savedCalcButton = view.findViewById(R.id.saved_calc_btn);

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
        fact.setOnClickListener(this::addNum);
        sin.setOnClickListener(this::addNum);
        clear.setOnClickListener(this::addNum);
        multiply.setOnClickListener(this::addNum);
        backSpace.setOnClickListener(this::addNum);
        subtract.setOnClickListener(this::addNum);
        add.setOnClickListener(this::addNum);
        devide.setOnClickListener(this::addNum);
        cbracket.setOnClickListener(this::addNum);
        obracket.setOnClickListener(this::addNum);
        mPI.setOnClickListener(this::addNum);
        mE.setOnClickListener(this::addNum);
        dot.setOnClickListener(this::addNum);

        backSpace.setOnLongClickListener(v -> {
            editField.setText("");
            result.setText("");
            expression = new char[50];
            dispExp = "";
            return true;
        });

        savedCalcButton.setOnClickListener(v->{
            Toast.makeText(getActivity().getApplicationContext(), "Saving past calculation is yet to be implemented", Toast.LENGTH_SHORT).show();
        });

        pow.setOnClickListener(v->{
            underDevDisplay();
        });

        sqroot.setOnClickListener(v->{
            underDevDisplay();
        });

        inv.setOnClickListener(v->{
            underDevDisplay();
        });

        rad.setOnClickListener(v->{
            isRAD = true;
            calcy.piFactor = calculator.PI;
            togglePiFactor();
        });

        deg.setOnClickListener(v->{
            isRAD = false;
            calcy.piFactor = 180;
            togglePiFactor();
        });


    }

    void togglePiFactor(){
        TextView active = isRAD==true?rad:deg;
        TextView inactive = isRAD==true?deg:rad;

        active.setBackground(getResources().getDrawable(R.drawable.buttonback));
        inactive.setBackground(null);

    }

    void underDevDisplay(){
        Toast.makeText(getActivity().getApplicationContext(), "the functionality is under development", Toast.LENGTH_SHORT).show();
    }

}
