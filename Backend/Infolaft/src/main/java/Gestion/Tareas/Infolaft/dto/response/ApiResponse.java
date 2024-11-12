package Gestion.Tareas.Infolaft.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Estructura de respuesta API genérica")
public class ApiResponse<T> {


    @Schema(description = "Metadatos de la respuesta")
    private Meta meta;

    @Schema(description = "Datos de la respuesta")
    private T data;

    @Schema(description = "Detalles del error si existe")
    private ErrorDetails error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Metadatos de la respuesta")
    public static class Meta {
        @Schema(description = "Mensaje de la operación", example = "Operación Exitosa")
        private String message;

        @Schema(description = "Código de estado HTTP", example = "200")
        private Integer statusCode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Detalles del error")
    public static class ErrorDetails {
        @Schema(description = "Código del error", example = "NOT_FOUND")
        private String code;

        @Schema(description = "Descripción del error", example = "Recurso no encontrado")
        private String description;
    }
}
