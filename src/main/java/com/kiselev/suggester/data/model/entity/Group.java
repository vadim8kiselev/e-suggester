package com.kiselev.suggester.data.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Vadim Kiselev
 * @date: 09.02.2018
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class Group {

    @Id
    @Column(name = "group_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;


    @Column(name = "type")
    private String type;

    @Column(name = "verified")
    private String verified;

    @Column(name = "is_closed")
    private Boolean closed;

    @Column(name = "age_limits")
    private String ageLimits;

    @Column(name = "subscribers_count")
    private String subscribersCount;


    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;


    @Column(name = "photo_link")
    private String photoLink;


    @Column(name = "links")
    private String links;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "site")
    private String site;


    @Column(name = "market_enabled")
    private Boolean marketEnabled;

    @Column(name = "deactivated")
    private String deactivated;

    public Integer getOwnerId() {
        return -Integer.parseInt(id);
    }
}


