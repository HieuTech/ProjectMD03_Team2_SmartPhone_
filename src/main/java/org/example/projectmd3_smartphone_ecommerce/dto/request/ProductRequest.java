package org.example.projectmd3_smartphone_ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;


@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
        Integer id;
        Integer categories;
        String sku;
        String name;
        String description;
        Integer stockQuantity;
        MultipartFile image;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date createdAt;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date updatedAt;
        Double unitPrice;
    }


