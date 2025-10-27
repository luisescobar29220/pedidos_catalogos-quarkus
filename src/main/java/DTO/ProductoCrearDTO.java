package DTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoCrearDTO(
        @NotBlank @Size(max = 50) String sku,
        @NotBlank String nombre,
        @Min(0) int stock,
        @Min(0) int stockMinimo,
        @NotNull @Positive BigDecimal precio,
        @NotBlank @Size(min = 3, max = 3) String moneda) {
}
