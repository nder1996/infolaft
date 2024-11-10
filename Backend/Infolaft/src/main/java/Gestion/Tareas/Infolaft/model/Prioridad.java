package Gestion.Tareas.Infolaft.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prioridad {

    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @NotNull(message = "El nivel es obligatorio")
    @Min(value = 1, message = "El nivel debe ser mayor o igual a 1")
    @Max(value = 99999, message = "El nivel no puede exceder 99999")
    private Integer nivel;

    @NotBlank(message = "El código de color es obligatorio")
    @Size(max = 100, message = "El código de color no puede exceder los 100 caracteres")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "El código de color debe ser un valor hexadecimal válido (ej: #FF0000)")
    private String codigoColor;

    @NotBlank(message = "El estado es obligatorio")
    @Size(min = 1, max = 1, message = "El estado debe ser un único carácter")
    @Pattern(regexp = "[A-Za-z]", message = "El estado debe ser un carácter alfabético")
    private String estado;
}
