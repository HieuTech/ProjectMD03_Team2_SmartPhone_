package org.example.projectmd3_smartphone_ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projectmd3_smartphone_ecommerce.entity.Address;
import org.example.projectmd3_smartphone_ecommerce.validator.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NewPasswordMatches
@CorrectCurrentPassword
public class AuthenEditRequest {
    @NotNull(message = "Tên người dùng không được để trống")
    String userName;
    @ValidEmail
    @UniqueEmailIfChanged
    String email;
    String oldPassword;
    @ValidPassword
    String password;
    String repeatPassword;
    @NotNull(message = "Địa chỉ không được để trống")
    String address;
    @ValidPhoneNumber
    @UniquePhoneIfChanged
    String phone;
    String avatar;
    String receiveName;
    private MultipartFile userAvatar;
    Integer googleAccountId;
    @Valid
    private List<Address> addresses;
}
