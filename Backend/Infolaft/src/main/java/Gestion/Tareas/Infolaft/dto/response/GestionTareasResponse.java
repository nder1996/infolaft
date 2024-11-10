package Gestion.Tareas.Infolaft.dto.response;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GestionTareasResponse {

    @NotNull(message = "El ID de la tarea no puede ser nulo")
    private Integer idTarea;

    @NotBlank(message = "El título de la tarea es obligatorio")
    @Size(max = 1000, message = "El título no puede exceder los 1000 caracteres")
    private String tituloTarea;

    @NotBlank(message = "La descripción de la tarea es obligatoria")
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcionTarea;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Future(message = "La fecha de vencimiento debe ser una fecha futura")
    private Date fechaVencimiento;

    @NotBlank(message = "El estado de la tarea es obligatorio")
    private String estadoTarea;

    @NotBlank(message = "La prioridad de la tarea es obligatoria")
    private String prioridadTarea;

    private String colorPrioridad;

    @NotNull(message = "El ID del estado es obligatorio")
    private Integer idEstado;

    @NotNull(message = "El ID de la prioridad es obligatorio")
    @Positive(message = "El ID de la prioridad debe ser un número positivo")
    private Integer idPrioridad;

    @NotNull(message = "La fecha de creación es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    @NotNull(message = "La fecha de actualización es obligatoria")
    private Date updateAt;
}
