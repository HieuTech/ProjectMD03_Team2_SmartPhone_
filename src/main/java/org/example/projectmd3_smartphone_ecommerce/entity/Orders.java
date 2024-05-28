package org.example.projectmd3_smartphone_ecommerce.entity;



import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @Column(name = "serial_number")

    String serialNumber;

    @Column(name = "total_price")

    Double totalPrice;
    String status;
    String note;
    @Column(name = "receive_address")

    String receiveAddress;
    @Column(name = "receive_phone")

    String receivePhone;
    @Column(name = "receive_name")

    String receiveName;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")

    Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")

    Date updatedAt;

}
