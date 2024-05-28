package org.example.projectmd3_smartphone_ecommerce.dto.request;

import org.example.projectmd3_smartphone_ecommerce.validator.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class AuthenEditRequest {
    @NotNull(message = "Tên người dùng không được để trống")
    String userName;
    @UniqueEmail
    @ValidEmail
    String email;
    @ValidPassword
    String newPassword;
    String repeatNewPassword;
    @NotNull(message = "Địa chỉ không được để trống")

    String fullAddress;
    @ValidPhoneNumber
    @UniquePhoneNumber
    String phone;

    String receiveName;
    private MultipartFile userAvatar;
    Integer googleAccountId;
}
