package org.example.projectmd3_smartphone_ecommerce.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {

    Integer id;
    Integer productId;
    String productName;
    String productImg;
    Double productPrice;
    Integer quantity;

}
