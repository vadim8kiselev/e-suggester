package com.kiselev.suggester.data.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Sets;
import com.kiselev.suggester.data.model.entity.annotation.Ranged;
import com.kiselev.suggester.data.model.entity.annotation.Unique;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(exclude = "profiles")
@JsonIgnoreProperties(value = {"profiles"})
public class Product {

    @Id
    @Unique
    @Column(name = "product_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "section")
    private String section;

    @Ranged(5000)
    @Column(name = "price")
    private Integer price;

    @Column(name = "availability")
    private Boolean availability;

    @Ranged(500)
    @Column(name = "views")
    private Integer views;

    @Ranged(value = 100, nullable = false)
    @Column(name = "popularity")
    private Integer popularity;

    @Unique
    @Column(name = "photo")
    private String photo;

    @Unique
    @Setter
    @ManyToMany
    @JoinTable(name = "profile_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<Profile> profiles = Sets.newHashSet();
}
