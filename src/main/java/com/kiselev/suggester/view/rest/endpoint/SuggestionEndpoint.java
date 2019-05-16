package com.kiselev.suggester.view.rest.endpoint;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.service.suggestion.SuggestionService;
import com.kiselev.suggester.service.discoverer.ProfileDiscoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuggestionEndpoint {

    @Autowired
    private ProfileDiscoverer profileDiscoverer;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private DAO dao;

    @GetMapping("social/network")
    public List<Product> socialNetwork(@RequestParam("link") String link) {
        Profile profile = profileDiscoverer.discover(link);
        dao.profile(profile);
        return suggestionService.suggest(profile);
    }

    @GetMapping("database")
    public List<Product> database(@RequestParam("id") String id) {
        Profile profile = dao.profile(id);
        return suggestionService.suggest(profile);
    }
}
