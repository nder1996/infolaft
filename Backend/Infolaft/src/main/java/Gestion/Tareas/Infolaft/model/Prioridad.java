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



    @NotBlank(message = "El estado es obligatorio")
    @Size(min = 1, max = 1, message = "El estado debe ser un único carácter")
    @Pattern(regexp = "[A-Za-z]", message = "El estado debe ser un carácter alfabético")
    private String estado;
}
