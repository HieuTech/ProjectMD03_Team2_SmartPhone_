package org.example.projectmd3_smartphone_ecommerce.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;



@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    Integer id;
    String name;
    String description;
    Boolean status;

}
