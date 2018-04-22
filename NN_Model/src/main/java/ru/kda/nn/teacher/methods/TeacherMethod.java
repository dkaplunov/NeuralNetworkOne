package ru.kda.nn.teacher.methods;

import ru.kda.nn.base.NeuralNetwork;

public interface TeacherMethod {
    public void train (NeuralNetwork neuralNetwork, double [] reference);
}
