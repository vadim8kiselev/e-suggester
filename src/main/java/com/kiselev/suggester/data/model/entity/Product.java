package com.kiselev.suggester.data.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class Product {

    @Id
    @Column(name = "product_id")
    private String id;

    @Column(name = "name")
    private String name;
}
