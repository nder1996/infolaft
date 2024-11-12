package Gestion.Tareas.Infolaft.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 1000, message = "El título no puede exceder los 1000 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "El ID del estado es obligatorio")
    @Positive(message = "El ID del estado debe ser un número positivo")
    private Integer idEstado;

    private Integer idEstadoAnterior;

    @NotNull(message = "El ID de la prioridad es obligatorio")
    @Positive(message = "El ID de la prioridad debe ser un número positivo")
    private Integer idPrioridad;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Future(message = "La fecha de vencimiento debe ser una fecha futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaVencimiento;

  //  @NotNull(message = "La fecha de creación es obligatoria")
//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_at;

   // @NotNull(message = "La fecha de actualización es obligatoria")
  //  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_at;

}
