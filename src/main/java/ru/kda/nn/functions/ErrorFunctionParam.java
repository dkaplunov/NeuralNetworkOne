package ru.kda.nn.functions;

import java.util.List;

public interface ErrorFunctionParam extends FunctionParam {
    void setParams(List<Double> results, List<Double> reference);
    List<Double> getResults();
    List<Double> getReference();

}
