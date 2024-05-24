package org.example.projectmd3_smartphone_ecommerce.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wish_lists")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_list_id")
    Integer id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;

    String password;
}
