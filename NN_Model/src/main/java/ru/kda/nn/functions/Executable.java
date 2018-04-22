package ru.kda.nn.functions;

public interface Executable {
    double execute(FunctionParam param);
    double excuteDerivative (FunctionParam param);
}
