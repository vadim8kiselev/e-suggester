package com.kiselev.suggester.suggestion.neuro;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.suggestion.Suggester;
import com.kiselev.suggester.suggestion.neuro.function.NeuroFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NeuroAgent implements Suggester {

    @Autowired
    private NeuroFunction<Profile> profileFunction;

    @Autowired
    private DAO dao;

    @Override
    public List<Product> suggest(Profile profile) {
        String function = profileFunction.process(profile);

        return dao.products();
    }
}
