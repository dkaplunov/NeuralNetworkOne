package ru.kda.nn.functions.activate;

import ru.kda.nn.functions.Executable;
import ru.kda.nn.functions.FunctionParam;

public class Sigmoid implements Executable {
    @Override
    public double execute(FunctionParam param) {
        return 1/(1+java.lang.Math.exp(-((SigmoidParam)param).getVal()));
    }

    @Override
    public double excuteDerivative(FunctionParam param) {
        double val = ((SigmoidParam)param).getVal();
        return (1-val)*val;
    }


}
