package ru.kda.nn.teacher.methods;

import ru.kda.nn.base.NeuralNetwork;

import java.util.List;

public interface TeacherMethod {
    void train (NeuralNetwork neuralNetwork, List<Double> reference);
}
