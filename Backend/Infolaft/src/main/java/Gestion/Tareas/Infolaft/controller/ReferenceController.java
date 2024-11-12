package Gestion.Tareas.Infolaft.controller;

import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.model.Estado;
import Gestion.Tareas.Infolaft.model.Prioridad;
import Gestion.Tareas.Infolaft.service.ReferenceService;
import Gestion.Tareas.Infolaft.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reference")
public class ReferenceController {


    @Autowired
    ReferenceService referenceService;


    @GetMapping("/getAllPrioridad")
    public ResponseEntity<ApiResponse<List<Prioridad>>> getAllInventarios() {
        ApiResponse<List<Prioridad>> response = this.referenceService.getAllPrioridad();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }



    @GetMapping("/getAllEstado")
    public ResponseEntity<ApiResponse<List<Estado>>> getAllEstado() {
        ApiResponse<List<Estado>> response = this.referenceService.getAllEstado();
        return ResponseEntity.status(response.getMeta().getStatusCode()).body(response);
    }


}
