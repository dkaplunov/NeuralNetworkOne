package ru.kda.nn.teacher;

import java.util.List;

public class TranerSet {
    private final List<Double> initValues;
    private final List<Double> reference;

    public TranerSet(List<Double> initValues, List<Double> reference) {
        this.initValues = initValues;
        this.reference = reference;
    }

    public List<Double> getInitValues() {
        return initValues;
    }

    public List<Double> getReference() {
        return reference;
    }
}
