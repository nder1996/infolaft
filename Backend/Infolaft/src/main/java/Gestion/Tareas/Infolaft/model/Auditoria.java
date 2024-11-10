package Gestion.Tareas.Infolaft.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {

    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotNull(message = "El ID-ESTADO no puede ser nulo")
    private Integer idEstado;

    @Size(max = 100, message = "La acción no puede exceder los 100 caracteres")
    private String accion;

    @NotNull(message = "La fecha de creación es obligatoria")
    private Date create_at;

    @NotBlank(message = "El campo creadoPor es obligatorio")
    @Size(max = 1000, message = "El campo creadoPor no puede exceder los 1000 caracteres")
    private String creadoPor; // Cambiado a String según la definición de la base de datos


}

