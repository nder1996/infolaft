package Gestion.Tareas.Infolaft.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información de prioridad de una tarea")
public class Prioridad {

    @Schema(
            description = "ID único de la prioridad",
            example = "1",
            required = true
    )
    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @Schema(
            description = "Nombre de la prioridad",
            example = "Alta",
            required = true,
            maxLength = 100
    )
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @Schema(
            description = "Estado de la prioridad (un carácter alfabético)",
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
}
