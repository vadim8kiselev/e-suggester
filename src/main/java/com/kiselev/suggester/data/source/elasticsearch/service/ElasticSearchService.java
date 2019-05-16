package com.kiselev.suggester.data.source.elasticsearch.service;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.elasticsearch.ElasticSearch;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

public class ElasticSearchService implements ElasticSearch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

    @Autowired
    private RestHighLevelClient client;

    @Value("${elasticsearch.bulk.max-size}")
    private Integer BULK_MAX_SIZE;

    @Override
    public void publishProfiles(List<Profile> profiles) {
        try {
            List<List<Profile>> lists = Lists.partition(profiles, BULK_MAX_SIZE);

            for (List<Profile> list : lists) {
                BulkRequest bulkRequest = new BulkRequest();

                for (Profile profile : list) {
                    IndexRequest indexRequest = new IndexRequest("profiles", "doc", String.valueOf(profile.getId()))
                            .source(XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("profile_id", profile.getId())
                                    .field("profile_first_name", profile.getFirstName())
                                    .field("profile_last_name", profile.getLastName())
                                    .field("profile_screen_name", profile.getScreenName())
                                    .field("profile_sex", profile.getSex())
                                    .field("profile_online", profile.getOnline())
                                    .field("profile_birthday", profile.getBirthday())
                                    .field("profile_city", profile.getCity())
                                    .field("profile_country", profile.getCountry())
                                    .field("profile_mobile_phone", profile.getMobilePhone())
                                    .field("profile_home_phone", profile.getHomePhone())
                                    .field("profile_skype", profile.getSkype())
                                    .field("profile_facebook", profile.getFacebook())
                                    .field("profile_twitter", profile.getTwitter())
                                    .field("profile_livejournal", profile.getLivejournal())
                                    .field("profile_instagram", profile.getInstagram())
                                    .field("profile_status", profile.getStatus())
                                    .field("profile_last_seen", profile.getLastSeen())
                                    .field("profile_career", profile.getCareer())
                                    .field("profile_military", profile.getMilitary())
                                    .field("profile_university", profile.getUniversity())
                                    .field("profile_home_town", profile.getHomeTown())
                                    .field("profile_photo_link", profile.getPhotoLink())
                                    .field("profile_relation", profile.getRelation())
                                    .field("profile_relation_partner", profile.getRelationPartner())
                                    .field("profile_interests", profile.getInterests())
                                    .field("profile_music", profile.getMusic())
                                    .field("profile_activities", profile.getActivities())
                                    .field("profile_movies", profile.getMovies())
                                    .field("profile_tv", profile.getTv())
                                    .field("profile_books", profile.getBooks())
                                    .field("profile_games", profile.getGames())
                                    .field("profile_schools", profile.getSchools())
                                    .field("profile_about", profile.getAbout())
                                    .field("profile_quotes", profile.getQuotes())
                                    .field("profile_political", profile.getPolitical())
                                    .field("profile_languages", profile.getLanguages())
                                    .field("profile_religion", profile.getReligion())
                                    .field("profile_inspired_by", profile.getInspiredBy())
                                    .field("profile_people_main", profile.getPeopleMain())
                                    .field("profile_life_main", profile.getLifeMain())
                                    .field("profile_smoking", profile.getSmoking())
                                    .field("profile_alcohol", profile.getAlcohol())
                                    .field("profile_deactivated", profile.getDeactivated())
                                    .endObject());

                    bulkRequest.add(indexRequest);
                }

                if (!bulkRequest.requests().isEmpty()) {
                    client.bulk(bulkRequest);
                }
            }
            LOGGER.info("[ELASTIC SEARCH] User data posted to statistics service");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
