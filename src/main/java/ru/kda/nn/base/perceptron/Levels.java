package ru.kda.nn.base.perceptron;

import ru.kda.nn.base.Neuron;

import java.util.ArrayList;
import java.util.List;

public class Levels {
    private List<Neuron> neurons = new ArrayList<>();

    public void addNeuron (Neuron neuron) {
        neurons.add(neuron);
    }

    public List <Neuron> getNeurons () {
        return neurons;
    }

    public void initLevel (double [] values) {
        for (int i = 0; i<values.length && i<neurons.size(); i++) {
            neurons.get(i).setValue (values[i]);
        }
    }

    public void calcLevel () {
        neurons.forEach(item -> item.calculateValue());
    }

    public double [] getLevelValues () {
        double [] res = new double[neurons.size()];
        for (int i = 0; i<res.length; i++) {
            res[i] = neurons.get(i).getValue();
        }
        return res;
    }

}
