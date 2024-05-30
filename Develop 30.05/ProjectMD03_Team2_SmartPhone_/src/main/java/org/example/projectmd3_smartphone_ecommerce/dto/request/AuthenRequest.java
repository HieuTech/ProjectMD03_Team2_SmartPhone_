package org.example.projectmd3_smartphone_ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectmd3_smartphone_ecommerce.validator.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@PasswordMatches
public class AuthenRequest {
    @NotNull(message = "This field can not be empty.")
    String userName;
    @UniqueEmail
    @ValidEmail
    String email;
    @ValidPassword
    String password;
    String repeatPassword;
    @NotNull(message = "This field can not be empty.")

    String fullAddress;
    @ValidPhoneNumber
    @UniquePhoneNumber
    String phone;

    private MultipartFile userAvatar;
    Integer googleAccountId;
}
