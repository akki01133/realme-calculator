package com.iitdelhi.calculator.model;

public class saved_calc {
    String Expression;
    String Result;

    public saved_calc() {
    }

    public saved_calc(String expression, String result) {
        Expression = expression;
        Result = result;
    }

    public String getExpression() {
        return Expression;
    }

    public void setExpression(String expression) {
        Expression = expression;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
