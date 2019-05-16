package com.kiselev.suggester.data.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class Profile {

    @Id
    @Column(name = "profile_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "online")
    private String online;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "mobile_phone", columnDefinition = "TEXT")
    private String mobilePhone;

    @Column(name = "home_phone", columnDefinition = "TEXT")
    private String homePhone;


    @Column(name = "skype", columnDefinition = "TEXT")
    private String skype;

    @Column(name = "facebook", columnDefinition = "TEXT")
    private String facebook;

    @Column(name = "twitter", columnDefinition = "TEXT")
    private String twitter;

    @Column(name = "livejournal", columnDefinition = "TEXT")
    private String livejournal;

    @Column(name = "instagram", columnDefinition = "TEXT")
    private String instagram;


    @Column(name = "status", columnDefinition = "TEXT")
    private String status;

    @Column(name = "last_seen", columnDefinition = "TEXT")
    private String lastSeen;


    @Column(name = "career", columnDefinition = "TEXT")
    private String career;

    @Column(name = "military", columnDefinition = "TEXT")
    private String military;

    @Column(name = "university", columnDefinition = "TEXT")
    private String university;

    @Column(name = "home_town", columnDefinition = "TEXT")
    private String homeTown;


    @Column(name = "photo_link", columnDefinition = "TEXT")
    private String photoLink;


    @Column(name = "relation", columnDefinition = "TEXT")
    private String relation;

    @Column(name = "relation_partner", columnDefinition = "TEXT")
    private String relationPartner;


    @Column(name = "interests", columnDefinition = "TEXT")
    private String interests;

    @Column(name = "music", columnDefinition = "TEXT")
    private String music;

    @Column(name = "activities", columnDefinition = "TEXT")
    private String activities;

    @Column(name = "movies", columnDefinition = "TEXT")
    private String movies;

    @Column(name = "tv", columnDefinition = "TEXT")
    private String tv;

    @Column(name = "books", columnDefinition = "TEXT")
    private String books;

    @Column(name = "games", columnDefinition = "TEXT")
    private String games;

    @Column(name = "schools", columnDefinition = "TEXT")
    private String schools;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @Column(name = "quotes", columnDefinition = "TEXT")
    private String quotes;


    @Column(name = "political", columnDefinition = "TEXT")
    private String political;

    @Column(name = "languages", columnDefinition = "TEXT")
    private String languages;

    @Column(name = "religion", columnDefinition = "TEXT")
    private String religion;

    @Column(name = "inspired_by", columnDefinition = "TEXT")
    private String inspiredBy;

    @Column(name = "people_main", columnDefinition = "TEXT")
    private String peopleMain;

    @Column(name = "lifeMain", columnDefinition = "TEXT")
    private String lifeMain;

    @Column(name = "smoking", columnDefinition = "TEXT")
    private String smoking;

    @Column(name = "alcohol", columnDefinition = "TEXT")
    private String alcohol;


    @Column(name = "deactivated", columnDefinition = "TEXT")
    private String deactivated;
}
