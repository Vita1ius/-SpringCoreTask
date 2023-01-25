package com.epam.esm.dto.request;

import com.epam.esm.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCertificateRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private List<Tag> tags;
}
