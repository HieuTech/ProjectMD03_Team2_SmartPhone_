package org.example.projectmd3_smartphone_ecommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectmd3_smartphone_ecommerce.entity.EnumOrders;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;


@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrdersRequest {


    Users users;
    String serialNumber;
    Double totalPrice;
    EnumOrders status;
    String note;

    String receiveAddress;

    String receivePhone;

    String receiveName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date updatedAt;
}
