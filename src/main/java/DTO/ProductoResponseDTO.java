package DTO;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        Long id,
        String sku,
        String nombre,
        int stock,
        int stockMinimo,
        BigDecimal precio,
        String moneda
) {
}
