package org.example.projectmd3_smartphone_ecommerce.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    @NotNull(message = "Id cannot be null")
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

}
