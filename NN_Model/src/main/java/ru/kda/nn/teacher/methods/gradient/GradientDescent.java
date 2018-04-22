package ru.kda.nn.teacher.methods.gradient;

import ru.kda.nn.base.Neuron;
import ru.kda.nn.base.Synapse;
import ru.kda.nn.teacher.methods.TeacherMethod;
import ru.kda.nn.base.NeuralNetwork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradientDescent implements TeacherMethod {

    private Map<Neuron, Double> nextLevelDelta; // = new HashMap<>();
    private final double epsilon = 0.7;
    private final double alfa = 0.3;

    private void calcBottomDelta (List<Neuron> neurons, double [] refs) {
        for (int j = 0; j < refs.length && j<neurons.size(); j++) {
            Neuron neuron = neurons.get (j);
            double delta = (refs [j] - neuron.getValue())*neuron.getDerivativeValue();  //(1-neuron.getDerivativeValue())*neuron.getDerivativeValue();
            calcAndSetSinapseDeltas(delta, neuron.getSynapses());

        }
    }

    private void calcAndSetSinapseDeltas(double delta, List<Synapse> synapses) {
        synapses.forEach(synapse -> {
            //Calculate deltas for next level
            Neuron neuron = synapse.getParentNeuron();
            double coef = delta*synapse.getCoef();  //*neuron.getDerivativeValue();
            Double value = nextLevelDelta.get(synapse.getParentNeuron());
            value = value != null ? value+coef : coef;
            nextLevelDelta.put(synapse.getParentNeuron(), value);

            //change weigth for sinapses for one Neuron
            double gradient = delta*synapse.getParentNeuron().getValue();
            double deltaW = epsilon*gradient+alfa*synapse.getHistoryDalta();
            synapse.setHistoryDalta(deltaW);
            synapse.setCoef(synapse.getCoef()+deltaW);
        });
    }

    private void calcMiddleDelta () {
        Map<Neuron, Double> curLevelData = nextLevelDelta;
        nextLevelDelta = new HashMap<>();
        curLevelData.keySet().forEach(neuron -> {
            double delta = curLevelData.get(neuron)*neuron.getDerivativeValue();
            calcAndSetSinapseDeltas(delta, neuron.getSynapses());
        });
    }

    @Override
    public void train(NeuralNetwork neuralNetwork, double[] reference) {
        int numLevels = neuralNetwork.getNumberLevels();
        nextLevelDelta = new HashMap<>();
        calcBottomDelta(neuralNetwork.getLevel(numLevels-1), reference);
        for (int i = numLevels-2; i > 0 ; i--) {
            calcMiddleDelta();
        }
    }
}
