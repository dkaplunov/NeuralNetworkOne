package model

import data.EduValue
import data.NnParams
import data.NnValues
import ru.kda.nn.base.perceptron.PerceptronNetwork
import ru.kda.nn.functions.ErrorFunctionParam
import ru.kda.nn.functions.activate.Sigmoid
import ru.kda.nn.functions.error.RootMSE
import ru.kda.nn.teacher.Teacher
import ru.kda.nn.teacher.TranerSet
import ru.kda.nn.teacher.methods.gradient.GradientDescent
import kotlin.math.roundToLong

class NnOperator (params: NnParams) {

    val nn = PerceptronNetwork(
            params.levels.toIntArray(),
            when (params.activFunName) {
                    "S" -> Sigmoid()
                    else -> Sigmoid()
            })

    val errorFun = when (params.errFun) {
        "RMSE" -> RootMSE()
        else -> RootMSE()
    }

    val teacher = Teacher (
            nn,
            when (params.teacher) {
                "GD" -> GradientDescent()
                else -> GradientDescent()
            },
            params.numEpoche,
            errorFun
    )


    fun teachNN (eduValues: List <EduValue>) {
        eduValues.forEach {
            teacher.addTranerSet(TranerSet(toDoubleArray(it.binInitValue), toDoubleArray(it.binAnsverValue)))
        }
        teacher.teach()
    }

    fun execute (params:NnValues):NnValues {
        return toNnValue(nn.executeNetwork(toDoubleArray(params)));
    }

    fun toDoubleArray (inputValue:NnValues): DoubleArray = toDoubleArray(inputValue.binValue)

    fun toDoubleArray (inputValue:Int): DoubleArray {
        val values:MutableList<Double> = ArrayList<Double>()
        var value = inputValue
        while (value > 0) {
            values.add((value%2).toDouble())
            value = value/2
        }
        if (values.size == 0) values.add(0.0)
        return values.toDoubleArray().reversedArray();
    }

    fun toNnValue (arr:DoubleArray):NnValues {
        var result:Double = 0.0;
        arr.forEach {
            result = result*2+it
        }
        return NnValues(result.roundToLong().toInt())
    }

    fun getError(errorFunctionParam:ErrorFunctionParam):Double {
        return errorFun.execute(errorFunctionParam)
    }

}


