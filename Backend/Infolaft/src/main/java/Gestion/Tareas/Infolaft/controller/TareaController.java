package Gestion.Tareas.Infolaft.controller;

import Gestion.Tareas.Infolaft.dto.request.TareaRequest;
import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TareaController {

    @Autowired
    TareaService tareaService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<String>> getAllGestionTareas() {
        ApiResponse<String> response = this.tareaService.getAllGestionTareas();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponse<String>> createTasks(@Valid @RequestBody TareaRequest tareaRequest) {
        return new ResponseEntity<>(this.tareaService.saveTask(tareaRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateTask(
            @PathVariable Integer id,
            @Valid @RequestBody TareaRequest tareaRequest
    ) {
        tareaRequest.setId(id);
        ApiResponse<String> response = this.tareaService.saveTask(tareaRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> inactivateTask(@PathVariable Integer id) {
        ApiResponse<String> response = this.tareaService.inactivateById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<ApiResponse<String>> inactivateTask(@PathVariable Integer id) {
        ApiResponse<String> response = this.tareaService.inactivateById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
