package com.kiselev.suggester.view.rest.endpoint;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
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

    @GetMapping("vk/id")
    public List<Product> suggestFromVKById(@RequestParam("id") String id) {
        Profile profile = profileDiscoverer.discover(id);
        return suggestionService.suggest(profile);
    }

    @GetMapping("vk/link")
    public List<Product> suggestFromVkByLink(@RequestParam("link") String link) {
        Profile profile = profileDiscoverer.discover(link);
        return suggestionService.suggest(profile);
    }
}
