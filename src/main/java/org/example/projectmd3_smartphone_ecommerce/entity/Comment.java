package org.example.projectmd3_smartphone_ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name= "Comment")
public class Comment {
    @Id
    @Column(name = "user_id")
    private int userID;
    @Column(name = "pro_id")
    private int proID;
    @Column(name = "comment")
    private String comment;
    @Column(name = "rate")
    private double rate;
}
