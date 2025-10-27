package DTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record PedidoResponseDTO(
        UUID id,
        OffsetDateTime fecha,
        BigDecimal total,
        int cantidadLineas
) {
}
