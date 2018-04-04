package ru.kda.nn.teacher;

import ru.kda.nn.base.NeuralNetwork;
import ru.kda.nn.functions.Executable;
import ru.kda.nn.teacher.methods.TeacherMethod;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private List <TranerSet> tranerSets = new ArrayList<>();
    private NeuralNetwork neuralNetwork;
    private int epocheCount;
    private Executable errorFunction;
    private TeacherMethod teacherMethod;

    public Teacher(NeuralNetwork neuralNetwork, TeacherMethod teacherMethod, int epocheCount, Executable errorFunction) {
        this.neuralNetwork = neuralNetwork;
        this.epocheCount = epocheCount;
        this.errorFunction = errorFunction;
        this.teacherMethod = teacherMethod;
    }

    public void addTranerSet (TranerSet tranerSet) {
        tranerSets.add(tranerSet);
    }

    public void teach () {
        int a = 1;
        for (int i = 0; i < epocheCount; i++) {
            tranerSets.forEach(set -> {
                neuralNetwork.executeNetwork(set.getInitValues());
                teacherMethod.train(neuralNetwork, set.getReference());
            });

        }
    }
}

