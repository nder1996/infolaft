package Gestion.Tareas.Infolaft.service;

import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.model.Estado;
import Gestion.Tareas.Infolaft.model.Prioridad;
import Gestion.Tareas.Infolaft.repository.ReferenceRepository;
import Gestion.Tareas.Infolaft.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceService {


    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    ResponseApiBuilderService responseApiBuilderService;

    @Autowired
    validationService validatorService;


    public ApiResponse<List<Prioridad>> getAllPrioridad() {
        try {
            List<Prioridad> prioridad = this.referenceRepository.getAllPrioridad();
            // Validamos cada objeto de la lista
            if (prioridad != null && !prioridad.isEmpty()) {
                for (Prioridad prio : prioridad) {
                    List<String> errors = validatorService.validate(prio);
                    if (!errors.isEmpty()) {
                        return ResponseApiBuilderService.errorResponse(
                                400,
                                "VALIDATION_ERROR",
                                "Error de validación: " + String.join(", ", errors)
                        );
                    }
                }
                return ResponseApiBuilderService.successResponse(prioridad, "PRIORIDAD");
            } else {
                return ResponseApiBuilderService.errorResponse(200, "NO_CONTENT", "No hay registro disponibles");

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseApiBuilderService.errorResponse(
                    500,
                    "SERVER_ERROR",
                    "ERROR EN EL SERVIDOR"
            );
        }
    }

    public ApiResponse<List<Estado>> getAllEstado() {
        try {
            List<Estado> estados = this.referenceRepository.getAllEstado();

            // Validamos que la lista no sea nula y tenga elementos
            if (estados != null && !estados.isEmpty()) {
                // Validamos cada objeto Estado de la lista
                for (Estado estado : estados) {
                    List<String> errors = validatorService.validate(estado);
                    if (!errors.isEmpty()) {
                        return ResponseApiBuilderService.errorResponse(
                                400,
                                "VALIDATION_ERROR",
                                "Error de validación: " + String.join(", ", errors)
                        );
                    }
                }
                return ResponseApiBuilderService.successResponse(estados, "ESTADO");
            } else {
                return ResponseApiBuilderService.errorResponse(200, "NO_CONTENT", "No hay registro disponibles");

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseApiBuilderService.errorResponse(
                    500,
                    "SERVER_ERROR",
                    "ERROR EN EL SERVIDOR"
            );
        }
    }



}
