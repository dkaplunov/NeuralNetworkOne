package model
import org.junit.Test as test
import org.junit.Assert

class TestModel {

    var nnOperator:NnOperator = NnOperator(NnParams(intArrayOf(2,3,1),"_", "_", 100000, "_"))

    @test fun doubleToNnValueTest () {
        val nnValue:NnValues = NnValues(22)
        assert(nnOperator.toNnValue(nnOperator.toDoubleArray(nnValue)).binValue == nnValue.binValue)
    }
}