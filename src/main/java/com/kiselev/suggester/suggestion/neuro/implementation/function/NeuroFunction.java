package com.kiselev.suggester.suggestion.neuro.implementation.function;

public interface NeuroFunction<Entity> {

    String process(Entity entity);
}
