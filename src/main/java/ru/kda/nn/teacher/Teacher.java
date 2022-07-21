package ru.kda.nn.teacher;

import ru.kda.nn.base.NeuralNetwork;
import ru.kda.nn.functions.Executable;
import ru.kda.nn.teacher.methods.TeacherMethod;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private final List <TranerSet> tranerSets = new ArrayList<>();
    private final NeuralNetwork neuralNetwork;
    private final int epocheCount;
    private final Executable errorFunction;
    private final TeacherMethod teacherMethod;

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
        for (int i = 0; i < epocheCount; i++) {
            tranerSets.forEach(set -> {
                neuralNetwork.executeNetwork(set.getInitValues());
                teacherMethod.train(neuralNetwork, set.getReference());
            });

        }
    }
}

