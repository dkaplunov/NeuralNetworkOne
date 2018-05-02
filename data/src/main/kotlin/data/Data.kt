package data

data class NnParams (var levels:Array<Int>, var activFunName:String, var teacher:String, var numEpoche:Int, var errFun:String) {
    constructor() : this(arrayOf(0), "_", "_", 0, "_") {}
}

data class NnValues (val binValue:Int) {
        constructor() : this(0) {}
}

data class EduValue (val binInitValue:Int, val binAnsverValue:Int) {
    constructor() : this (0, 0)
}