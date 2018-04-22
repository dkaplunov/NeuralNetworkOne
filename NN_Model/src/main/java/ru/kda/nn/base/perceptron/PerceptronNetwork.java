package ru.kda.nn.base.perceptron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kda.nn.base.Neuron;
import ru.kda.nn.base.NeuralNetwork;
import ru.kda.nn.functions.Executable;

import java.util.ArrayList;
import java.util.List;

public class PerceptronNetwork implements NeuralNetwork {
    private List <Levels> levels = new ArrayList<>();

    public PerceptronNetwork(int [] levelsSizes, Executable func) {
        Levels prevLevel = new Levels();
        for (int levelsSize : levelsSizes) {
            Levels level = new Levels();
            for (int i=0; i<levelsSize; i++) {
                level.addNeuron(new Neuron(func, prevLevel.getNeurons()));
            }
            prevLevel = level;
            this.levels.add(level);
        }
    }

    @Override
    public double [] executeNetwork (double [] values) {
        Levels prevLevel = levels.get(0);
        prevLevel.initLevel(values);
        for (int i=1; i<levels.size(); i++) {
            levels.get(i).calcLevel();
        }
        return levels.get(levels.size()-1).getLevelValues();
    }

    @Override
    public List<Neuron> getLevel(int numLevel) {
        return levels.get (numLevel).getNeurons ();
    }

    @Override
    public int getNumberLevels() {
        return levels.size();
    }

    @Override
    public String getJSON() {
        ObjectMapper om = new ObjectMapper();
        String s = "";
        try {
            s = om.writeValueAsString(levels);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
}

