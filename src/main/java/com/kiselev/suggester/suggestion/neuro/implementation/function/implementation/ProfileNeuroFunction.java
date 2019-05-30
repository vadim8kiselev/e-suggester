package com.kiselev.suggester.suggestion.neuro.implementation.function.implementation;

import com.kiselev.suggester.suggestion.neuro.implementation.function.NeuroFunction;
import com.kiselev.suggester.suggestion.neuro.implementation.function.mapper.ProfileMapper;
import com.kiselev.suggester.suggestion.neuro.implementation.function.utils.GraphBuilder;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

public class ProfileNeuroFunction implements NeuroFunction {

    @Override
    public Tensor<Float> process(String key) {
        try (Graph graph = new Graph()) {
            GraphBuilder graphBuilder = new GraphBuilder(graph);

            final Output<Float> output =
                    graphBuilder.constant("input", ProfileMapper.nameToIndex(key));

            try (Session session = new Session(graph)) {
                return session.runner()
                        .fetch(output.op().name())
                        .run().get(0).expect(Float.class);
            }
        }
    }
}
