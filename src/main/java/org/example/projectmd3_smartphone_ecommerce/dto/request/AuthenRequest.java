package org.example.projectmd3_smartphone_ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projectmd3_smartphone_ecommerce.validator.PasswordMatches;
import org.example.projectmd3_smartphone_ecommerce.validator.UniqueEmail;
import org.example.projectmd3_smartphone_ecommerce.validator.ValidEmail;
import org.example.projectmd3_smartphone_ecommerce.validator.ValidPassword;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    private MultipartFile userAvatar;
    Integer googleAccountId;
}
