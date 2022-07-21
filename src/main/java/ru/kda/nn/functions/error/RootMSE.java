package ru.kda.nn.functions.error;

import ru.kda.nn.functions.ErrorFunctionParam;
import ru.kda.nn.functions.Executable;
import ru.kda.nn.functions.FunctionParam;

import java.util.List;
import java.util.stream.DoubleStream;

public class RootMSE implements Executable {
    @Override
    public double execute(FunctionParam param) {
        List<Double> results = ((ErrorFunctionParam)param).getResults();
        List<Double> refs = ((ErrorFunctionParam)param).getReference();
        var refsIterator = refs.iterator();

        double errorValue = results.stream().flatMapToDouble(result-> {
            if(refsIterator.hasNext()) {
                var ref = refsIterator.next();
                return DoubleStream.of((ref-result)*(ref-result));
            }
            return DoubleStream.of(0.);
        }).sum();

        return Math.sqrt(errorValue/refs.size());
    }

    @Override
    public double excuteDerivative(FunctionParam param) {
        return 0;
    }
}
