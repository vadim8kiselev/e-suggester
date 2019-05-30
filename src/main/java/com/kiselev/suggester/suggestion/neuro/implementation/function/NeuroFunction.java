package com.kiselev.suggester.suggestion.neuro.implementation.function;

import org.tensorflow.Tensor;

public interface NeuroFunction {

    Tensor<Float> process(String key);
}
