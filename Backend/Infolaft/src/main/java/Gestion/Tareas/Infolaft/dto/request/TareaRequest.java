package Gestion.Tareas.Infolaft.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  TareaRequest {

    private Integer id;

    @NotNull(message = "El título no puede ser nulo")
    @Size(max = 1000, message = "El título no puede exceder los 1000 caracteres")
    private String titulo;

    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private Integer idEstado;

    @NotNull(message = "La prioridad es obligatoria")
    private Integer idPrioridad;

    @NotNull(message = "La fecha de creación es obligatoria")
    private Date fechaVencimiento;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private Date create_at;


    private Date update_at;
}
