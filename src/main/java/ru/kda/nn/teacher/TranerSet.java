package ru.kda.nn.teacher;

public class TranerSet {
    private double initValues[];
    private double reference [];

    public TranerSet(double[] initValues, double[] reference) {
        this.initValues = initValues;
        this.reference = reference;
    }

    public double[] getInitValues() {
        return initValues;
    }

    public double[] getReference() {
        return reference;
    }
}
