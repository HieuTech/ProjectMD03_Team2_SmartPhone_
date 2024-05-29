package org.example.projectmd3_smartphone_ecommerce.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectmd3_smartphone_ecommerce.entity.Roles;

import java.util.List;

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
    List<Roles> roles;
}
