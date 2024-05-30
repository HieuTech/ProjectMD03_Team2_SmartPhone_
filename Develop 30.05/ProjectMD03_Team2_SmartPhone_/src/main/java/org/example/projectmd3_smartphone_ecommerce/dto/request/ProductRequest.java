package org.example.projectmd3_smartphone_ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.example.projectmd3_smartphone_ecommerce.entity.Categories;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;


@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

        @NotNull(message = "ID cannot be null")
        private Integer id;

        @NotNull(message = "Category cannot be null")
        private Integer categories;

        @NotBlank(message = "SKU cannot be blank")
        private String sku;

        @NotBlank(message = "Name cannot be blank")
        private String name;

        @NotBlank(message = "Description cannot be blank")
        private String description;

        @NotNull(message = "Stock quantity cannot be null")
        @Min(value = 0, message = "Stock quantity cannot be less than 0")
        private Integer stockQuantity;

        @NotNull(message = "Image cannot be null")
        private MultipartFile image;

        @NotNull(message = "Created date cannot be null")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date createdAt;

        @NotNull(message = "Updated date cannot be null")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date updatedAt;

        @NotNull(message = "Unit price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
        private Double unitPrice;
}


