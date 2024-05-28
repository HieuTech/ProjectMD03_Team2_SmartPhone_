package org.example.projectmd3_smartphone_ecommerce.dto.request;

import org.example.projectmd3_smartphone_ecommerce.validator.UniqueEmail;
import org.example.projectmd3_smartphone_ecommerce.validator.ValidEmail;
import org.example.projectmd3_smartphone_ecommerce.validator.ValidPassword;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class AuthenEditRequest {
    @NotNull(message = "Tên người dùng không được để trống")
    private String userName;
    @UniqueEmail
    @ValidEmail
    private String email;
    @ValidPassword
    private String password;
    private String repeatPassword;
    private String avatar;
    private MultipartFile userAvatar;
    private Boolean status;
    private Integer googleAccountId;
}
