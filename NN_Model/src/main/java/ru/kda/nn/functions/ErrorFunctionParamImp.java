package ru.kda.nn.functions;

public class ErrorFunctionParamImp implements ErrorFunctionParam {
    private double results [];
    private double reference [];

    public ErrorFunctionParamImp(double[] results, double[] reference) {
        setParams(results, reference);
    }

    @Override
    public void setParams(double[] results, double[] reference) {
        this.results = results;
        this.reference = reference;
    }

    @Override
    public double[] getResults() {
        return this.results;
    }

    @Override
    public double[] getReference() {
        return this.reference;
    }
}
