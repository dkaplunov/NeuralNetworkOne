package ru.kda.nn.functions.error;

import ru.kda.nn.functions.ErrorFunctionParam;
import ru.kda.nn.functions.Executable;
import ru.kda.nn.functions.FunctionParam;

public class RootMSE implements Executable {
    @Override
    public double execute(FunctionParam param) {
        double results [] = ((ErrorFunctionParam)param).getResults();
        double refs [] = ((ErrorFunctionParam)param).getReference();
        double errorValue = 0;
        for (int i=0; i<results.length && i<refs.length; i++) {
            errorValue += (refs[i]-results[i])*(refs[i]-results[i]);
        }
        return Math.sqrt(errorValue/refs.length);
    }

    @Override
    public double excuteDerivative(FunctionParam param) {
        return 0;
    }
}
