package ru.kda.nn.base;

import java.util.List;

public interface NeuralNetwork {

    List<Double> executeNetwork (List<Double> values);
    List<Neuron> getLevel (int numLevel);
    int getNumberLevels ();

    String getJSON ();

}
