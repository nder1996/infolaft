package Gestion.Tareas.Infolaft.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información del estado de una tarea")
public class Estado {

    @Schema(
            description = "ID único del estado",
            example = "1",
            required = true
    )
    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @Schema(
            description = "Nombre del estado",
            example = "En Progreso",
            required = true,
            maxLength = 100
    )
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @Schema(
            description = "Descripción detallada del estado",
            example = "Estado que indica que la tarea está siendo trabajada activamente",
            required = true,
            maxLength = 1000
    )
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @Schema(
            description = "Estado activo/inactivo (un carácter alfabético)",
            example = "A",
            required = true,
            pattern = "[A-Za-z]",
            minLength = 1,
            maxLength = 1
    )
    @NotBlank(message = "El estado es obligatorio")
    @Size(min = 1, max = 1, message = "El estado debe ser un único carácter")
    @Pattern(regexp = "[A-Za-z]", message = "El estado debe ser un carácter alfabético")
    private String estado;

    @Schema(
            description = "Fecha de creación del estado",
            example = "2024-11-12T10:30:00",
            required = true,
            pattern = "yyyy-MM-dd'T'HH:mm:ss"
    )
    @NotNull(message = "La fecha de creación es obligatoria")
    private Date create_at;
}
