package Gestion.Tareas.Infolaft.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de gestión de tareas")
public class GestionTareasResponse {

    @Schema(description = "ID único de la tarea", example = "1")
    private Long idTarea;

    @Schema(description = "Título de la tarea", example = "Desarrollar funcionalidad de login")
    private String tituloTarea;

    @Schema(description = "Descripción detallada de la tarea",
            example = "Implementar la autenticación de usuarios usando JWT")
    private String descripcionTarea;

    @Schema(description = "Fecha límite para completar la tarea",
            example = "2024-12-31",
            pattern = "yyyy-MM-dd")
    private Date fechaVencimiento;

    @Schema(description = "Estado actual de la tarea")
    private Estado estado;

    @Schema(description = "Nivel de prioridad de la tarea")
    private Prioridad prioridad;

    @Schema(description = "Fecha de creación de la tarea",
            example = "2024-11-11",
            pattern = "yyyy-MM-dd")
    private Date createAt;

    @Schema(description = "Fecha de última actualización de la tarea",
            example = "2024-11-11",
            pattern = "yyyy-MM-dd")
    private Date updateAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Información del estado de la tarea")
    public static class Estado {
        @Schema(description = "ID del estado", example = "1")
        private Long id;

        @Schema(description = "Nombre del estado",
                example = "En Progreso",
                allowableValues = {"Pendiente", "En Progreso", "Completada", "Cancelada"})
        private String nombre;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Información de la prioridad de la tarea")
    public static class Prioridad {
        @Schema(description = "ID de la prioridad", example = "1")
        private Long id;

        @Schema(description = "Nivel de prioridad",
                example = "Alta",
                allowableValues = {"Baja", "Media", "Alta", "Urgente"})
        private String nombre;
    }
}
