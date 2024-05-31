package org.example.projectmd3_smartphone_ecommerce.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Integer id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Categories categories;
    @JoinColumn(name = "sku")
    String sku;
    @JoinColumn(name = "name")
    String name;
    @JoinColumn(name = "description")
    String description;
    @Column(name = "stock_quantity")
    Integer stockQuantity;
    String image;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")
    Date updatedAt;
    @Column(name = "unit_price")
    Double unitPrice;
    @Column(name = "rate")
    Double rate;

}
