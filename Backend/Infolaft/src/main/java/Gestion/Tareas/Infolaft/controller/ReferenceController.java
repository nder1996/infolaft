package Gestion.Tareas.Infolaft.controller;

import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.model.Estado;
import Gestion.Tareas.Infolaft.model.Prioridad;
import Gestion.Tareas.Infolaft.service.ReferenceService;
import Gestion.Tareas.Infolaft.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reference")
@Tag(name = "Referencias", description = "API para gestionar datos de referencia como prioridades y estados")
public class ReferenceController {


    @Autowired
    ReferenceService referenceService;



    @Operation(summary = "Obtener todas las prioridades")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lista de prioridades recuperada exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/getAllPrioridad")
    public ResponseEntity<ApiResponse<List<Prioridad>>> getAllInventarios() {
        ApiResponse<List<Prioridad>> response = this.referenceService.getAllPrioridad();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }

    @Operation(summary = "Obtener todos los estados")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lista de estados recuperada exitosamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/getAllEstado")
    public ResponseEntity<ApiResponse<List<Estado>>> getAllEstado() {
        ApiResponse<List<Estado>> response = this.referenceService.getAllEstado();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


}
