package com.ecommerce.ecommerce.order.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequest {
    @NotEmpty
    private List<Long> cartItemIds;
}
