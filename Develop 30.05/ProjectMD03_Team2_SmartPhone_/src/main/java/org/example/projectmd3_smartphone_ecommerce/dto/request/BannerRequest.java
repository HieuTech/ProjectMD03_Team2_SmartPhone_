package org.example.projectmd3_smartphone_ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BannerRequest {
    Integer id;
    @NotNull(message = "Không được để trống!")
    String src;
    @NotNull(message = "Không được để trống!")
    String alt;
}
