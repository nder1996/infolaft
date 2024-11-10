package Gestion.Tareas.Infolaft.controller;

import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.service.ReferenceService;
import Gestion.Tareas.Infolaft.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reference")
public class ReferenceController {


    @Autowired
    ReferenceService referenceService;


    @GetMapping("/getAllPrioridad")
    public ResponseEntity<ApiResponse<String>> getAllInventarios() {
        return new ResponseEntity<>(this.referenceService.getAllPrioridad(), HttpStatus.OK);
    }



    @GetMapping("/getAllEstado")
    public ResponseEntity<ApiResponse<String>> getAllEstado() {
        return new ResponseEntity<>(this.referenceService.getAllEstado(), HttpStatus.OK);
    }


}
