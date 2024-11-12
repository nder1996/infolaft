package Gestion.Tareas.Infolaft.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de solicitud para crear o actualizar una tarea")

public class  TareaRequest {

    @Schema(
            description = "ID de la tarea (opcional para crear, requerido para actualizar)",
            example = "1"
    )
    private Integer id;

    @Schema(
            description = "Título de la tarea",
            example = "Implementar funcionalidad de login",
            required = true,
            maxLength = 1000
    )
    @NotNull(message = "El título no puede ser nulo")
    @Size(max = 1000, message = "El título no puede exceder los 1000 caracteres")
    private String titulo;

    @Schema(
            description = "Descripción detallada de la tarea",
            example = "Desarrollar el sistema de autenticación usando JWT y Spring Security",
            maxLength = 1000
    )
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @Schema(
            description = "ID del estado de la tarea",
            example = "1",
            required = true
    )
    @NotNull(message = "El estado es obligatorio")
    private Integer idEstado;

    @Schema(
            description = "ID de la prioridad de la tarea",
            example = "1",
            required = true
    )
    @NotNull(message = "La prioridad es obligatoria")
    private Integer idPrioridad;

    @Schema(
            description = "Fecha límite para completar la tarea",
            example = "2024-12-31T23:59:59",
            required = true,
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @NotNull(message = "La fecha de creación es obligatoria")
    private Date fechaVencimiento;

    @Schema(
            description = "Fecha de creación de la tarea",
            example = "2024-11-12T10:30:00",
            required = true,
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private Date create_at;

    @Schema(
            description = "Fecha de última actualización de la tarea",
            example = "2024-11-12T15:45:00",
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    private Date update_at;
}
