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
import java.util.logging.Logger;

public class PerceptronNetworkTest {
    private final Logger log = Logger.getLogger("NN. Test");
    private PerceptronNetwork nn;
    private double initialData [];
    private double refernceData [];

    @Before
    public void setUp() throws Exception {

        nn = new PerceptronNetwork(new int [] {6, 11, 11, 5}, new Sigmoid());

        Teacher teacher = new Teacher(nn, new GradientDescent(), 50000, new RootMSE());
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 0, 0, 0, 1}, new double [] {1, 0, 0, 0, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 0, 0, 1, 0}, new double [] {0, 1, 0, 0, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 0, 0, 1, 1}, new double [] {0, 0, 1, 0, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 0, 1, 0, 0}, new double [] {0, 1, 0, 0, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 0, 1, 0, 1}, new double [] {0, 0, 0, 1, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 0, 1, 1, 0}, new double [] {0, 1, 1, 0, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 0, 1, 1, 1}, new double [] {0, 0, 0, 0, 1}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 1, 0, 0, 0}, new double [] {0, 1, 0, 0, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 1, 0, 0, 1}, new double [] {0, 0, 1, 0, 0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0, 1, 1, 1, 1}, new double [] {0, 0, 1, 1, 0}));

        teacher.teach();
//        log.info(ru.kda.nn.getJSON());

   }

    @Test
    public void executeNetworkTwoResults() {
        initialData = new double [] {0, 0, 1, 1, 1, 1};
        refernceData = new double [] {0, 0, 1, 1, 0};
        double err = executeNN();
        Assert.assertTrue(err<0.01);
    }

    @Test
    public void executeNetworkOneResults() {
        initialData = new double [] {0, 0, 0, 1, 0, 0};
        refernceData = new double [] {0, 1, 0, 0, 0};
        double err = executeNN();
        Assert.assertTrue(err<0.01);
    }

    @Test
    public void executeNetworkFail() {
        initialData = new double [] {0, 0, 1, 0, 0, 1};
        refernceData = new double [] {1, 1, 1, 1, 1};
        double err = executeNN();
        Assert.assertTrue(err>0.5);
    }

    private double executeNN () {
        double [] res = nn.executeNetwork(initialData);

        double err = new RootMSE().execute(new RootMSEParam(res, refernceData));
        log.info(Arrays.toString(res)+", Error: "+err);

        return err;
    }

}