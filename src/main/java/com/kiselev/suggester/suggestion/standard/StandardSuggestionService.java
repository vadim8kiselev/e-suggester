package com.kiselev.suggester.suggestion.standard;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.service.data.DataService;
import com.kiselev.suggester.suggestion.Suggester;
import com.kiselev.suggester.suggestion.standard.implementation.collaboration.CollaborationSuggester;
import com.kiselev.suggester.suggestion.standard.implementation.content.ContentBasedSuggester;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StandardSuggestionService implements Suggester {

    @Autowired
    private ContentBasedSuggester contentBasedSuggester;

    @Autowired
    private CollaborationSuggester collaborationSuggester;

    @Autowired
    private DataService dataService;

    @Override
    public List<Product> suggest(Profile profile) {
        if (profile != null) {
            if (CollectionUtils.isNotEmpty(profile.getProducts())) {
                System.out.println("[Content based] suggestion process for profile with id: " + profile.getId());
                return contentBasedSuggester.suggest(profile);
            } else {
                System.out.println("[Collaborative] suggestion process for profile with id: " + profile.getId());
                return collaborationSuggester.suggest(profile);
            }
        } else {
            System.out.println("   [Failure]    suggestion process did not start for inactive profile");
            return dataService.retrieveRandomRecommendaton();
        }
    }
}
