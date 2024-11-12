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

    private Long idTarea;
    private String tituloTarea;
    private String descripcionTarea;
    private Date fechaVencimiento;
    private Estado estado;
    private Prioridad prioridad;
    private Date createAt;
    private Date updateAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Estado {
        private Long id;
        private String nombre;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prioridad {
        private Long id;
        private String nombre;
    }
}
