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
@Table(name = "vouchers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vouchers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    Integer id;

    String code;

    @Column(name = "discount_percentage")
    Integer discountPercent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    Date endDate;

    @Column(name = "used_count")
    Integer usedCount;
    Integer amount;
    String status;
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")
    Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;
}
