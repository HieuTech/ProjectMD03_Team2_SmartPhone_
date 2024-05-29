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
    @NotNull(message = "Tên người dùng không được để trống")
    String userName;
    @UniqueEmail
    @ValidEmail
    String email;
    @ValidPassword
    String password;
    String repeatPassword;
    @NotNull(message = "Địa chỉ không được để trống")

    String fullAddress;
    @ValidPhoneNumber
    @UniquePhoneNumber
    String phone;

    String receiveName;
    private MultipartFile userAvatar;
    Integer googleAccountId;
}
