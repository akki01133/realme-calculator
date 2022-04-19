package com.iitdelhi.calculator.model;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class calculator {
    //implement map for operators storage.
    public static char[] operators = {'÷','×','+','-','s','c','t','l','n','!','√',};
    public static final String[] FUNC_NAME ={"sin","cos","tan","log","ln"};
    public static final double PI = 3.141592;
    static final double e = 2.718281;
    public double piFactor = 180;
    public double calculate(double ans, int operate ,@NonNull char[] charExp) throws Exception {
        StringBuilder num= new StringBuilder();
        int expLen = charExp.length;
        for(int j = 0;j<expLen;j++ ) {
            char x = charExp[j];
            if(isDigit(x) || x=='.') {
                num.append(x);
            }
            else if(x =='π' || x == 'e'){
                num = constantHandler(num,x);
            }
            else if(x == '!') {
                num = factCalc(num);
            }
            else if(x == '(') {
                j++;
                String bracketString = bracketExtractor(charExp, j);
                j += bracketString.length();
                if (j < expLen && charExp[j] == ')') {
                    j++;
                }
                char[] subExpArray = bracketString.toCharArray();
                num = new StringBuilder(funcCalc(subExpArray, -1));
            }
            else {
                int temp = isOperator(x);
                if(temp == -1)
                    throw new Exception();
                //if it is operator then continue
                if(temp>3) {
                    String subExp;
                    j+=2;
                    subExp = bracketExtractor(charExp, j);
                    j+=subExp.trim().length();
                    char[] subExpArray = subExp.toCharArray();
                    num = new StringBuilder(funcCalc(subExpArray, temp ));
                }
                else if(isPrior(temp,operate)) {
                    char[] subCharExp = Arrays.copyOfRange(charExp, j+1, charExp.length);
                    num = new StringBuilder(String.valueOf(calculate(doubleOf(num.toString()), temp, subCharExp)));
                    break;
                }
                else {
                    ans = calculatePair(ans,doubleOf(num.toString()),operate);
                    num = new StringBuilder();
                    operate = temp;
                }
            }
        }
        ans = calculatePair(ans, doubleOf(num.toString()), operate);
        return ans;
    }

    private StringBuilder constantHandler(StringBuilder num, char x) {
        double temp = num.length() == 0 ? 0.0 : Double.parseDouble(num.toString());
        double constant = x=='e'? e: PI;
        if(temp != 0.0){
            temp *= constant;
        }
        else{
            temp=constant;
        }
        return new StringBuilder(String.valueOf(temp));
    }

    private String bracketExtractor(char[] charExp,int j) throws Exception {
        StringBuilder subExp = new StringBuilder();
        while(true) {
            if(j==charExp.length || charExp[j]==')')
                break;
            subExp.append(charExp[j]);
            j++;
        }
        if(subExp.length() == 0)
            throw new Exception();
        return subExp.toString();
    }


    private StringBuilder factCalc(StringBuilder num) throws Exception {
        if(num.length() == 0)
            throw new Exception();

        double temp = Double.parseDouble(num.toString());
        if(temp%1 != 0 || (int)temp<0 ||(int)temp>=20){throw new Exception();}
        double ans= factorial((int)temp);
        return new StringBuilder(String.valueOf(ans));
    }

    private int factorial(int n) {

        if(n == 0 || n == 1)
            return 1;
        else return n*factorial(n-1);
    }

    private String funcCalc(char[] subExpArray, int temp ) {
        double answer =0;
        try {
            double argumentVal = calculate(0, 2, subExpArray);
            switch (temp) {
                case -1: answer = argumentVal; //solve a simple bracket
                    break;
                case 4: answer = Math.sin(argumentVal* (PI /piFactor));
                    break;
                case 5: answer = Math.cos(argumentVal* (PI /piFactor));
                    break;
                case 6: answer = Math.tan(argumentVal* (PI /piFactor));
                    break;
                case 7: answer = Math.log10(argumentVal);
                    break;
                case 8: answer = Math.log(argumentVal);
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) { e.printStackTrace(); }

        return String.valueOf(answer);
    }

    private static double doubleOf(String num) {
        return Double.parseDouble(num);
    }

    private static boolean isPrior(int temp, int operate) {
        return temp<operate;
    }

    private static double calculatePair(double ans, double num, int temp) throws Exception {
        double newAns = 0;
        if(temp ==0) {
            newAns = ans/num;
            if(num == 0)
                throw new Exception();
        }
        else if(temp == 1)
            newAns = ans*num;
        else if(temp == 2)
            newAns = ans+num;
        else if(temp == 3)
            newAns = ans-num;
        return newAns;
    }

    private static boolean isDigit(char x) {
        return (x-'0'>=0 && x-'0'<=9);
    }

    private static int isOperator(char ch) {
        for(int i=0;i<operators.length;i++)
            if(operators[i] == ch) {
                return i;
            }
        return -1;
    }

}
