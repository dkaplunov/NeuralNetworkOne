package ru.kda.nn.functions;

public interface ErrorFunctionParam extends FunctionParam {
    public void setParams(double[] results, double[] reference);
    public double[] getResults();
    public double[] getReference();

}
