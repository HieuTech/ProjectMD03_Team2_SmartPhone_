package org.example.projectmd3_smartphone_ecommerce.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishListResponse {
    Integer wishListId;

    Integer userId;
    Integer productId;
    String productName;
    String productImage;
    Double productPrice;

}
