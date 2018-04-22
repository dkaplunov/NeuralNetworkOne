package ru.kda.nn.base;

import java.util.List;

public interface NeuralNetwork {

    public double [] executeNetwork (double [] values);
    public List<Neuron> getLevel (int numLevel);
    public int getNumberLevels ();

    public String getJSON ();

}
