package ru.kda.nn.functions.activate;

import ru.kda.nn.functions.FunctionParam;

public class SigmoidParam implements FunctionParam {
    private double val;

    public SigmoidParam(double val) {
        this.val = val;
    }

    public double getVal() {
        return val;
    }
}
