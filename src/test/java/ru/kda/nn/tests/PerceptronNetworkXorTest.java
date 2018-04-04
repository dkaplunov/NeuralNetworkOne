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
import java.util.logging.Logger;

public class PerceptronNetworkXorTest {
    private final Logger log = Logger.getLogger("NN. Test");
    private PerceptronNetwork nn;
    private double initialData [];
    private double refernceData [];
    private Teacher teacher;

    private final static double ERR_CNTRL = 0.02;

    @Before
    public void setUp() throws Exception {
        nn = new PerceptronNetwork(new int [] {2, 4, 1}, new Sigmoid());
        teacher = new Teacher(nn, new GradientDescent(), 100000, new RootMSE());
        teacher.addTranerSet(new TranerSet(new double [] {1, 0}, new double [] {1}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 1}, new double [] {1}));
        teacher.addTranerSet(new TranerSet(new double [] {1, 1}, new double [] {0}));
        teacher.addTranerSet(new TranerSet(new double [] {0, 0}, new double [] {0}));
        teacher.teach();

//        log.info(nn.getJSON());
    }

    @Test
    public void test00() {
        initialData = new double [] {0, 0};
        refernceData = new double [] {0};
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void test01() {
        initialData = new double [] {0, 1};
        refernceData = new double [] {1};
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void test10() {
        initialData = new double [] {1, 0};
        refernceData = new double [] {1};
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void test11() {
        initialData = new double [] {1, 1};
        refernceData = new double [] {0};
        double err = executeNN();
        Assert.assertTrue(err<ERR_CNTRL);
    }

    @Test
    public void testFail() {
        initialData = new double [] {1, 1};
        refernceData = new double [] {1};
        double err = executeNN();
        Assert.assertTrue(err>1-ERR_CNTRL);
    }


    private double executeNN () {
        double [] res = nn.executeNetwork(initialData);
        double err = new RootMSE().execute(new RootMSEParam(res, refernceData));

        log.info(Arrays.toString(res)+", Error: "+err);

        return err;
    }

}