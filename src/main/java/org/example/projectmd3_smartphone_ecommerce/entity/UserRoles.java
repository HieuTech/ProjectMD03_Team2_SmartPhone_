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
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_roles_id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

}