package org.example.projectmd3_smartphone_ecommerce.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenResponse {
    Integer userId;
    Integer cartQuantity;
    String userName;
    String avatar;
    String email;
}
