package DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AgregarLineaDTO(
        @NotNull Long productoId,
        @Min(1) int cantidad
) {
}
