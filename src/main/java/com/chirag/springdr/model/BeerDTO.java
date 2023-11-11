package com.chirag.springdr.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BeerDTO {
    private UUID id;
    private Integer version;
    @NotNull(message = "Cannot be null bro") @NotBlank (message = "Cannot be blank bro")
    private String beerName;
    @NotNull
    private BeerStyle beerStyle;
    @NotNull @NotBlank
    private String upc;
    private Integer quantityOnHand;
    @NotNull
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
