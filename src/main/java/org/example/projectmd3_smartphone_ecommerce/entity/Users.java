package org.example.projectmd3_smartphone_ecommerce.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer id;

    @Column(name = "user_name")
    String userName;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;



    @Column(name = "avatar")
    String avatar;

    @Column(name = "status")
    Boolean status;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")
    Date updatedAt;

    @Column(name = "google_account_id")
    Integer googleAccountId;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    Address address;
}

