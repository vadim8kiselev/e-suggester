package com.kiselev.suggester.view.rest.endpoint;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.service.discoverer.ProfileDiscoverer;
import com.kiselev.suggester.suggestion.Suggester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuggestionEndpoint {

    @Autowired
    private ProfileDiscoverer profileDiscoverer;

    @Autowired
    private Suggester suggestionService;

    @Autowired
    private DAO dao;

    @GetMapping("social/network")
    public List<Product> socialNetwork(@RequestParam("link") String link) {
        Profile profile = profileDiscoverer.discover(link);
        return suggestionService.suggest(profile);
    }

    @GetMapping("database")
    public List<Product> database(@RequestParam("id") String id) {
        Profile profile = dao.profile(id);
        return suggestionService.suggest(profile);
    }
}
