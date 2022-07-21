package ru.kda.nn.base;

public class Synapse {
    private double coef = Math.random();
    private final Neuron parentNeuron;
    private double historyDalta = 0;

    public Synapse(Neuron parentNeuron) {
        this.parentNeuron = parentNeuron;
    }

    public void setCoef (double newValOfCoef) {
        coef = newValOfCoef;
    }

    public double calcNewVal () {
        return coef* parentNeuron.getValue();
    }

    public double getCoef() {
        return coef;
    }

    public double getHistoryDalta() {
        return historyDalta;
    }

    public void setHistoryDalta(double historyDalta) {
        this.historyDalta = historyDalta;
    }

    public Neuron getParentNeuron() {
        return parentNeuron;
    }
}
