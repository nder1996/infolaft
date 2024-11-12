package Gestion.Tareas.Infolaft.controller;

import Gestion.Tareas.Infolaft.dto.request.TareaRequest;
import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Tareas", description = "API para la gestión de tareas")
public class TareaController {

    @Autowired
    TareaService tareaService;


    /*@Operation(
            summary = "Obtener todas las tareas",
            description = "Retorna la lista de todas las tareas gestionadas"
    )
    @ApiResponses({
            @ApiResponses(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiResponse.class)
                            )
                    }
            ),
            @ApiResponses(
                    responseCode = "404",
                    description = "No se encontraron tareas",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiResponse.class)
                            )
                    }
            ),
            @ApiResponses(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiResponse.class)
                            )
                    }
            )
    })*/
    @GetMapping("/tasks")
    public ResponseEntity<ApiResponse<List<GestionTareasResponse>>> getAllGestionTareas() {
        ApiResponse<List<GestionTareasResponse>> response = this.tareaService.getAllGestionTareas();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }

    @GetMapping("/history/tasks")
    public ResponseEntity<ApiResponse<List<GestionTareasResponse>>> getAllHistoryGestionTareas() {
        ApiResponse<List<GestionTareasResponse>> response = this.tareaService.gestionHistoricoTareas();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


    @PutMapping("/saveOrUpdateTask")
    public ResponseEntity<ApiResponse<String>> saveOrUpdateTask(@Valid @RequestBody TareaRequest tareaRequest) {
        return new ResponseEntity<>(this.tareaService.saveUpdateTask(tareaRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateTask(
            @PathVariable Integer id,
            @Valid @RequestBody TareaRequest tareaRequest
    ) {
        tareaRequest.setId(id);
        ApiResponse<String> response = this.tareaService.saveUpdateTask(tareaRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> inactivateTask(@PathVariable Integer id) {
        ApiResponse<String> response  = this.tareaService.inactivateById(id);
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }

    @PutMapping("/activeTask/{id}")
    public ResponseEntity<ApiResponse<String>> activeTaskById(@PathVariable Integer id) {
        ApiResponse<String> response  = this.tareaService.activeTaskById(id);
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


}
