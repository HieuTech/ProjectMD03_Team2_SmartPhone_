package org.example.projectmd3_smartphone_ecommerce.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @Column(name = "full_address")

    String fullAddress;

    String phone;
    @Column(name = "receive_name")

    String receiveName;
}
