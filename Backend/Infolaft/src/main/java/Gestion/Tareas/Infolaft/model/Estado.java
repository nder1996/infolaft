package Gestion.Tareas.Infolaft.model;


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
public class Estado {

    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    @Size(min = 1, max = 1, message = "El estado debe ser un único carácter")
    @Pattern(regexp = "[A-Za-z]", message = "El estado debe ser un carácter alfabético")
    private String estado;

    @NotNull(message = "La fecha de creación es obligatoria")
    private Date create_at;
}
