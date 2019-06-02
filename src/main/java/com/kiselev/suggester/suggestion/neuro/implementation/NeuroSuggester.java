package com.kiselev.suggester.suggestion.neuro.implementation;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.model.entity.enums.Section;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.suggestion.neuro.implementation.function.NeuroFunction;
import com.kiselev.suggester.suggestion.neuro.implementation.function.implementation.ProfileNeuroFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NeuroSuggester {

    @Autowired
    private NeuroFunction<Profile> profileNeuroFunction;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DAO dao;

    @Value("${neuro.global.limit:1000}")
    private Integer globalLimit;

    @Value("${neuro.limit:0}")
    private Integer limit;

    public List<Product> suggest(Profile profile) {
        String function = profileNeuroFunction.process(profile);

        String sectionCode = restTemplate.getForObject(
                "http://localhost:8888/predict?function={function}",
                String.class,
                Collections.singletonMap("function", function));

        String section = Section.byCode(Integer.parseInt(sectionCode));

        return dao.productsBySection(section).stream()
                .limit(limit > 0 ? Math.min(limit, globalLimit) : globalLimit)
                .collect(Collectors.toList());
    }
}
