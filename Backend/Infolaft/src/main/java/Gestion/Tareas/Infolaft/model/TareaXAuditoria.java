package Gestion.Tareas.Infolaft.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaXAuditoria {

    @NotNull(message = "El ID no puede ser nulo")
    private Integer id;

    @NotNull(message = "El ID de auditoría es obligatorio")
    @Positive(message = "El ID de auditoría debe ser un número positivo")
    private Integer idAuditoria;

    @NotNull(message = "El ID de tarea es obligatorio")
    @Positive(message = "El ID de tarea debe ser un número positivo")
    private Integer idTarea;
}
