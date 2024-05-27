package org.example.projectmd3_smartphone_ecommerce.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Categories categories;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;
}
