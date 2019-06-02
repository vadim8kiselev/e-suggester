package com.kiselev.suggester.data.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Sets;
import com.kiselev.suggester.data.model.entity.annotation.Important;
import com.kiselev.suggester.data.model.entity.annotation.Listed;
import com.kiselev.suggester.data.model.entity.annotation.Unique;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(exclude = "products")
@JsonIgnoreProperties(value = {"products"})
public class Profile {

    @Id
    @Unique
    @Column(name = "profile_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Important
    @Column(name = "sex")
    private String sex;

    @Column(name = "online")
    private String online;

    @Important
    @Column(name = "birthday")
    private String birthday;

    @Important
    @Column(name = "city")
    private String city;

    @Important
    @Column(name = "country")
    private String country;

    @Unique
    @Column(name = "mobile_phone", columnDefinition = "TEXT")
    private String mobilePhone;

    @Unique
    @Column(name = "home_phone", columnDefinition = "TEXT")
    private String homePhone;


    @Column(name = "status", columnDefinition = "TEXT")
    private String status;

    @Column(name = "home_town", columnDefinition = "TEXT")
    private String homeTown;

    @Unique
    @Column(name = "photo_link", columnDefinition = "TEXT")
    private String photoLink;

    @Important
    @Column(name = "relation", columnDefinition = "TEXT")
    private String relation;

    @Listed
    @Column(name = "interests", columnDefinition = "TEXT")
    private String interests;

    @Listed
    @Column(name = "music", columnDefinition = "TEXT")
    private String music;

    @Listed
    @Column(name = "activities", columnDefinition = "TEXT")
    private String activities;

    @Listed
    @Column(name = "movies", columnDefinition = "TEXT")
    private String movies;

    @Listed
    @Column(name = "tv", columnDefinition = "TEXT")
    private String tv;

    @Listed
    @Column(name = "books", columnDefinition = "TEXT")
    private String books;

    @Listed
    @Column(name = "games", columnDefinition = "TEXT")
    private String games;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @Column(name = "quotes", columnDefinition = "TEXT")
    private String quotes;

    @Important
    @Column(name = "political", columnDefinition = "TEXT")
    private String political;

    @Column(name = "languages", columnDefinition = "TEXT")
    private String languages;

    @Column(name = "religion", columnDefinition = "TEXT")
    private String religion;

    @Column(name = "inspired_by", columnDefinition = "TEXT")
    private String inspiredBy;

    @Important
    @Column(name = "people_main", columnDefinition = "TEXT")
    private String peopleMain;

    @Important
    @Column(name = "lifeMain", columnDefinition = "TEXT")
    private String lifeMain;

    @Important
    @Column(name = "smoking", columnDefinition = "TEXT")
    private String smoking;

    @Important
    @Column(name = "alcohol", columnDefinition = "TEXT")
    private String alcohol;

    @Column(name = "closed")
    private Boolean closed;

    @Column(name = "deactivated")
    private Boolean deactivated;

    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "profile_product",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = Sets.newHashSet();
}
