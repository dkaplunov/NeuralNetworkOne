package ru.kda.nn.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.kda.nn.functions.activate.Sigmoid;
import ru.kda.nn.base.perceptron.PerceptronNetwork;
import ru.kda.nn.functions.error.RootMSE;
import ru.kda.nn.functions.error.RootMSEParam;
import ru.kda.nn.teacher.Teacher;
import ru.kda.nn.teacher.TranerSet;
import ru.kda.nn.teacher.methods.gradient.GradientDescent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class PerceptronNetworkTest {
    private final Logger log = Logger.getLogger("NN. Test");
    private PerceptronNetwork nn;
    private List<Double> initialData;
    private List<Double> refernceData;

    @Before
    public void setUp() throws Exception {

        nn = new PerceptronNetwork(new int [] {6, 11, 11, 5}, new Sigmoid());

        Teacher teacher = new Teacher(nn, new GradientDescent(), 50000, new RootMSE());
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 0., 0., 0., 1.), List.of(1., 0., 0., 0., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 0., 0., 1., 0.), List.of(0., 1., 0., 0., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 0., 0., 1., 1.), List.of(0., 0., 1., 0., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 0., 1., 0., 0.), List.of(0., 1., 0., 0., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 0., 1., 0., 1.), List.of(0., 0., 0., 1., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 0., 1., 1., 0.), List.of(0., 1., 1., 0., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 0., 1., 1., 1.), List.of(0., 0., 0., 0., 1.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 1., 0., 0., 0.), List.of(0., 1., 0., 0., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 1., 0., 0., 1.), List.of(0., 0., 1., 0., 0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0., 1., 1., 1., 1.), List.of(0., 0., 1., 1., 0.)));

        teacher.teach();

   }

    @Test
    public void executeNetworkTwoResults() {
        initialData = List.of(0., 0., 1., 1., 1., 1.);
        refernceData = List.of(0., 0., 1., 1., 0.);
        Double err = executeNN();
        Assert.assertTrue(err<0.01);
    }

    @Test
    public void executeNetworkOneResults() {
        initialData = List.of(0., 0., 0., 1., 0., 0.);
        refernceData = List.of(0., 1., 0., 0., 0.);
        Double err = executeNN();
        Assert.assertTrue(err<0.01);
    }

    @Test
    public void executeNetworkFail() {
        initialData = List.of(0., 0., 1., 0., 0., 1.);
        refernceData = List.of(1., 1., 1., 1., 1.);
        Double err = executeNN();
        Assert.assertTrue(err>0.5);
    }

    private Double executeNN () {
        var res = nn.executeNetwork(initialData);

        Double err = new RootMSE().execute(new RootMSEParam(res, refernceData));
        log.info(res.toString()+", Error: "+err);

        return err;
    }

}