package ru.kda.nn.base.perceptron;

import ru.kda.nn.base.Neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Levels {
    private final List<Neuron> neurons = new ArrayList<>();

    public void addNeuron (Neuron neuron) {
        neurons.add(neuron);
    }

    public List <Neuron> getNeurons () {
        return neurons;
    }

    public void initLevel (List<Double> values) {
        final var neuronIterator = neurons.iterator();
        values.forEach(v->{
           if(neuronIterator.hasNext()) {
               neuronIterator.next().setValue(v);
           }
        });
    }

    public void calcLevel () {
        neurons.forEach(Neuron::calculateValue);
    }

    public List<Double> getLevelValues () {
        return neurons.stream().map(Neuron::getValue).collect(Collectors.toList());
    }

}
