package DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CarritoResponseDTO(
        UUID id,
        List<LineaDTO> lineas,
        BigDecimal total
) {
    public record LineaDTO(
            Long productoId,
            String sku,
            String nombre,
            int cantidad,
            BigDecimal precioUnitario,
            BigDecimal subtotal
    ) {}
}
