package ru.kda.nn.functions.error;

import ru.kda.nn.functions.ErrorFunctionParam;
import ru.kda.nn.functions.FunctionParam;

import java.util.List;

public class RootMSEParam implements ErrorFunctionParam {
    private List<Double> results;
    private List<Double> reference;

    public RootMSEParam(List<Double> results, List<Double> reference) {
        setParams(results, reference);
    }

    @Override
    public void setParams(List<Double> results, List<Double> reference) {
        this.results = results;
        this.reference = reference;
    }

    @Override
    public List<Double> getResults() {
        return this.results;
    }

    @Override
    public List<Double> getReference() {
        return this.reference;
    }
}
