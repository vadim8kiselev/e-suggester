package com.kiselev.suggester.suggestion.neuro.implementation;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.suggestion.neuro.implementation.function.NeuroFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.PriorityQueue;

public class NeuroSuggester {

    @Autowired
    private NeuroFunction profileFunction;

    @Autowired
    private DAO dao;

    private byte[] graphDef = new byte[]{};

    public List<Product> suggest(Profile profile) {
        List<Product> products = Lists.newArrayList();

        List<String> interests = Lists.newArrayList(profile.getInterests().split(" "));

        for (String interest : interests) {
            float[] tensorFlowOutput = execute(interest);
            String type = classify(tensorFlowOutput);
            products.addAll(dao.products(type));
        }

        return products;
    }

    private float[] execute(String type) {
        try (Graph graph = new Graph()) {
            graph.importGraphDef(graphDef);

            try (Session s = new Session(graph);

                 Tensor<Float> result = s.runner()
                         .feed("input", profileFunction.process(type))
                         .fetch("output")
                         .run().get(0).expect(Float.class)) {
                float[] outputTensor = new float[result.intValue()]; // shape[3]
                FloatBuffer floatBuffer = FloatBuffer.wrap(outputTensor);
                result.writeTo(floatBuffer);

                return outputTensor;
            }
        }
    }

    private String classify(float[] tensorFlowOutput) {
        final int SIZE = 13;
        final int NUMBER_OF_BOUNDING_BOX = 5;
        final int MAX_RECOGNIZED_CLASSES = 24;

        int numClass = (int) (tensorFlowOutput.length / (Math.pow(SIZE, 2) * NUMBER_OF_BOUNDING_BOX) - 5);
        PriorityQueue<String> priorityQueue = new PriorityQueue(MAX_RECOGNIZED_CLASSES, (o1, o2) -> 0);

        int offset = 0;
        for (int cy = 0; cy < SIZE; cy++) {        // SIZE * SIZE cells
            for (int cx = 0; cx < SIZE; cx++) {
                for (int b = 0; b < NUMBER_OF_BOUNDING_BOX; b++) {   // 5 bounding boxes per each cell
                    //boundingBoxPerCell[cx][cy][b] = getModel(tensorFlowOutput, cx, cy, b, numClass, offset);
                    //calculateTopPredictions(boundingBoxPerCell[cx][cy][b], priorityQueue, labels);
                    offset = offset + numClass + 5;
                }
            }
        }

        return "Sport";
    }
}
