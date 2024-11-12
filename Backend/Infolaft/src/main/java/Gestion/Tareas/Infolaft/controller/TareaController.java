package Gestion.Tareas.Infolaft.controller;

import Gestion.Tareas.Infolaft.dto.request.TareaRequest;
import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Tareas", description = "API para la gestión de tareas")
public class TareaController {

    @Autowired
    TareaService tareaService;


    @Operation(summary = "Obtener todas las tareas")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lista de tareas recuperada exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/tasks")
    public ResponseEntity<ApiResponse<List<GestionTareasResponse>>> getAllGestionTareas() {
        ApiResponse<List<GestionTareasResponse>> response = this.tareaService.getAllGestionTareas();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


    @Operation(summary = "Obtener historial de tareas")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Historial de tareas recuperado exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/history/tasks")
    public ResponseEntity<ApiResponse<List<GestionTareasResponse>>> getAllHistoryGestionTareas() {
        ApiResponse<List<GestionTareasResponse>> response = this.tareaService.gestionHistoricoTareas();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


    @Operation(summary = "Guardar o actualizar una tarea")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Tarea guardada o actualizada exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos de la tarea inválidos"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Datos de la tarea a guardar o actualizar",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TareaRequest.class)
            )
    )
    @PutMapping("/saveOrUpdateTask")
    public ResponseEntity<ApiResponse<String>> saveOrUpdateTask(@Valid @RequestBody TareaRequest tareaRequest) {
        return new ResponseEntity<>(this.tareaService.saveUpdateTask(tareaRequest), HttpStatus.OK);
    }


    @Operation(summary = "Actualizar una tarea por ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Tarea actualizada exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos de la tarea inválidos"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Tarea no encontrada"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @io.swagger.v3.oas.annotations.Parameters({
            @io.swagger.v3.oas.annotations.Parameter(
                    name = "id",
                    description = "ID de la tarea a actualizar",
                    required = true,
                    example = "1"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Datos actualizados de la tarea",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TareaRequest.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateTask(
            @PathVariable Integer id,
            @Valid @RequestBody TareaRequest tareaRequest
    ) {
        tareaRequest.setId(id);
        ApiResponse<String> response = this.tareaService.saveUpdateTask(tareaRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "Inactivar una tarea por ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Tarea inactivada exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Tarea no encontrada"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @io.swagger.v3.oas.annotations.Parameter(
            name = "id",
            description = "ID de la tarea a inactivar",
            required = true,
            example = "1"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> inactivateTask(@PathVariable Integer id) {
        ApiResponse<String> response  = this.tareaService.inactivateById(id);
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


    @Operation(summary = "Activar una tarea por ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Tarea activada exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Tarea no encontrada"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @io.swagger.v3.oas.annotations.Parameter(
            name = "id",
            description = "ID de la tarea a activar",
            required = true,
            example = "1"
    )
    @PutMapping("/activeTask/{id}")
    public ResponseEntity<ApiResponse<String>> activeTaskById(@PathVariable Integer id) {
        ApiResponse<String> response  = this.tareaService.activeTaskById(id);
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


}
