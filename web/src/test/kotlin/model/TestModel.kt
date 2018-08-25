package model
import data.EduValue
import data.NnParams
import data.NnValues
import org.junit.Test as test
import org.junit.Before
import ru.kda.nn.functions.ErrorFunctionParamImp

class TestModel {

    var nnOperator:NnOperator = NnOperator(NnParams(arrayOf(2,4,1),"_", "_", 100000, "_"))
    val ERR_CNTRL = 0.02

    @Before fun init () {

    }


    @test fun doubleToNnValueTest () {
        val nnValue = NnValues(22)
        assert(nnOperator.toNnValue(nnOperator.toDoubleArray(nnValue)).binValue == nnValue.binValue)
    }

    @test fun executeNNTest () {
       doStuding();
       assert(nnOperator.getError(
               ErrorFunctionParamImp(
                       nnOperator.toDoubleArray(
                               nnOperator.execute(NnValues(0))),
                       nnOperator.toDoubleArray(NnValues(0))))<ERR_CNTRL)
       assert(nnOperator.getError(
               ErrorFunctionParamImp(nnOperator.toDoubleArray(
                                nnOperator.execute(NnValues(2))),
                       nnOperator.toDoubleArray(NnValues(1))))<ERR_CNTRL)
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