package com.kiselev.suggester.suggestion.neuro.implementation;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.model.entity.enums.Section;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.suggestion.neuro.implementation.function.implementation.ProfileNeuroFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class NeuroSuggester {

    @Autowired
    private ProfileNeuroFunction profileNeuroFunction;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DAO dao;

    public List<Product> suggest(Profile profile) {
        String function = profileNeuroFunction.process(profile);

        Integer sectionCode = restTemplate.getForObject("url", Integer.class, function); // TODO: connect to Python

        String section = Section.byCode(sectionCode);

        return dao.productsBySection(section);
    }
}
