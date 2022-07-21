package ru.kda.nn.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.kda.nn.base.perceptron.PerceptronNetwork;
import ru.kda.nn.functions.activate.Sigmoid;
import ru.kda.nn.functions.error.RootMSE;
import ru.kda.nn.functions.error.RootMSEParam;
import ru.kda.nn.teacher.Teacher;
import ru.kda.nn.teacher.TranerSet;
import ru.kda.nn.teacher.methods.gradient.GradientDescent;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class PerceptronNetworkXorTest {
    private final Logger log = Logger.getLogger("NN. Test");
    private PerceptronNetwork nn;
    private List<Double> initialData;
    private List<Double> refernceData;

    private final static double ERR_CNTRL = 0.02;

    @Before
    public void setUp() {
        nn = new PerceptronNetwork(new int [] {2, 4, 1}, new Sigmoid());
        Teacher teacher = new Teacher(nn, new GradientDescent(), 100000, new RootMSE());
        teacher.addTranerSet(new TranerSet(List.of(1., 0.), List.of(1.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 1.), List.of(1.)));
        teacher.addTranerSet(new TranerSet(List.of(1., 1.), List.of(0.)));
        teacher.addTranerSet(new TranerSet(List.of(0., 0.), List.of(0.)));
        teacher.teach();

//        log.info(nn.getJSON());
    }

    @Test
    public void test00() {
        initialData = List.of(0., 0.);
        refernceData = List.of(0.);
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void test01() {
        initialData = List.of(0., 1.);
        refernceData = List.of(1.);
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void test10() {
        initialData = List.of(1., 0.);
        refernceData = List.of(1.);
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void test11() {
        initialData = List.of(1., 1.);
        refernceData = List.of(0.);
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void testFail() {
        initialData = List.of(1., 1.);
        refernceData = List.of(1.);
        double err = executeNN();
        Assert.assertTrue(err>1-ERR_CNTRL);
    }


    private double executeNN () {
        List<Double> res = nn.executeNetwork(initialData);
        double err = new RootMSE().execute(new RootMSEParam(res, refernceData));

        log.info(res.toString()+", Error: "+err);

        return err;
    }

}