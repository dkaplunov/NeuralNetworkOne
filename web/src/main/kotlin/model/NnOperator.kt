package model

import ru.kda.nn.base.perceptron.PerceptronNetwork
import ru.kda.nn.functions.activate.Sigmoid
import ru.kda.nn.functions.error.RootMSE
import ru.kda.nn.teacher.Teacher
import ru.kda.nn.teacher.methods.gradient.GradientDescent

class NnOperator (params: NnParams) {

    val nn = PerceptronNetwork(
            params.levels,
            when (params.activFunName) {
                    "S" -> Sigmoid()
                    else -> Sigmoid()
            })

    val teacher = Teacher (
            nn,
            when (params.teacher) {
                "GD" -> GradientDescent()
                else -> GradientDescent()
            },
            params.numEpoche,
            when (params.errFun) {
                "RMSE" -> RootMSE()
                else -> RootMSE()
            })

    fun teachNN (eduValues: List <eduValue>) {

    }

    fun execute (params:NnValues):NnValues {
        return toNnValue(nn.executeNetwork(toDoubleArray(params)));
    }

    fun toDoubleArray (inputValue:NnValues): DoubleArray {
        val values:MutableList<Double> = ArrayList<Double>()
        var value = inputValue.binValue
        while (value > 0) {
            values.add((value%2).toDouble())
            value = value/2
        }
        return values.toDoubleArray();
    }

    fun toNnValue (arr:DoubleArray):NnValues {
        var result:Double = 0.0;
        arr.reversedArray().forEach {
            result = result*2+it
        }
        return NnValues(result.toInt())
    }
}

data class NnParams (val levels:IntArray, val activFunName:String, val teacher:String, val numEpoche:Int, val errFun:String);
data class NnValues (var binValue:Int)
data class eduValue (val binInitValue:Int, val binAnsverValue:Int)
