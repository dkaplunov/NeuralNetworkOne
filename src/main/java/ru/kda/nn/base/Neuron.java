package ru.kda.nn.base;

import ru.kda.nn.functions.Executable;
import ru.kda.nn.functions.activate.SigmoidParam;

import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private double value;
    private final Executable func;
    private final List <Synapse> synapses = new ArrayList<>();


    public Neuron(Executable func, List<Neuron> prevLevelNeurons) {
        this.value = 0;
        this.func = func;

        prevLevelNeurons.forEach( item -> synapses.add(new Synapse(item)));

    }

    public double getValue () {
        return value;
    }

    public List<Synapse> getSynapses() {
        return synapses;
    }

    public void calculateValue () {
        value = 0;
        synapses.forEach(item -> {value += item.calcNewVal();});
        value = func.execute(new SigmoidParam(value));

    }

    public double getDerivativeValue () {
        return func.excuteDerivative(new SigmoidParam(value));
    }

    public void setValue(double value) {
        this.value = value;
    }
}

