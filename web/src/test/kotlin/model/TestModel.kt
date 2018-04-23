package model
import org.junit.Test as test
import org.junit.Before

class TestModel {

    var nnOperator:NnOperator = NnOperator(NnParams(intArrayOf(2,4,1),"_", "_", 100000, "_"))

    @Before fun init () {

    }


    @test fun doubleToNnValueTest () {
        val nnValue = NnValues(22)
        assert(nnOperator.toNnValue(nnOperator.toDoubleArray(nnValue)).binValue == nnValue.binValue)
    }

    @test fun executeNNTest () {
        doStuding();
        nnOperator.execute(NnValues(0))
    }

    fun doStuding () {
        val studingData = arrayOf (
                EduValue(0,0),
                EduValue(1,1),
                EduValue(2,1),
                EduValue(3,0))
        nnOperator.teachNN(studingData.asList())
    }
}