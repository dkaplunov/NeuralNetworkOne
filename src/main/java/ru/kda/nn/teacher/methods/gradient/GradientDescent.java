package ru.kda.nn.teacher.methods.gradient;

import ru.kda.nn.base.NeuralNetwork;
import ru.kda.nn.base.Neuron;
import ru.kda.nn.base.Synapse;
import ru.kda.nn.teacher.methods.TeacherMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradientDescent implements TeacherMethod {

    private Map<Neuron, Double> nextLevelDelta;
    private final double epsilon = 0.7;
    private final double alfa = 0.3;

    private void calcBottomDelta (List<Neuron> neurons, List<Double> refs) {
        var neuronIterator = neurons.iterator();
        refs.forEach(ref->{
            if(neuronIterator.hasNext()) {
                Neuron neuron = neuronIterator.next();
                double delta = (ref - neuron.getValue())*neuron.getDerivativeValue();
                calcAndSetSinapseDeltas(delta, neuron.getSynapses());
            }
        });
    }

    private void calcAndSetSinapseDeltas(Double delta, List<Synapse> synapses) {
        synapses.forEach(synapse -> {
            //Calculate deltas for next level
            Neuron neuron = synapse.getParentNeuron();
            double coef = delta*synapse.getCoef();  //*neuron.getDerivativeValue();
            Double value = nextLevelDelta.get(synapse.getParentNeuron());
            value = value != null ? value+coef : coef;
            nextLevelDelta.put(synapse.getParentNeuron(), value);

            //change weight for synapses for one Neuron
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
    public void train(NeuralNetwork neuralNetwork, List<Double> reference) {
        int numLevels = neuralNetwork.getNumberLevels();
        nextLevelDelta = new HashMap<>();
        calcBottomDelta(neuralNetwork.getLevel(numLevels-1), reference);
        for (int i = numLevels-2; i > 0 ; i--) {
            calcMiddleDelta();
        }
    }
}
