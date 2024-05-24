package org.example.projectmd3_smartphone_ecommerce.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "shopping_carts")
public class ShoppingCarts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    Integer id;

    @Column(name = "order_quantity")

    Integer orderQuantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;
}
