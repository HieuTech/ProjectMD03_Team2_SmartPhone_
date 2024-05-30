package org.example.projectmd3_smartphone_ecommerce.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest    {

    Integer id;

    Integer orderQuantity;

    Integer userId;

    Integer productId;
}
