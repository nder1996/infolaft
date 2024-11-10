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


    public ApiResponse<String> getAllPrioridad() {
        try {
            List<Prioridad> gestion = this.referenceRepository.getAllPrioridad();
            // Validamos cada objeto de la lista
            if (gestion != null && !gestion.isEmpty()) {
                for (Prioridad prioridad : gestion) {
                    List<String> errors = validatorService.validate(prioridad);
                    if (!errors.isEmpty()) {
                        return this.responseApiBuilderService.errorRespuesta(
                                400,
                                "VALIDATION_ERROR",
                                "Error de validación: " + String.join(", ", errors)
                        );
                    }
                }
                return this.responseApiBuilderService.successRespuesta(gestion, "PRIORIDAD");
            } else {
                return this.responseApiBuilderService.errorRespuesta(
                        404,
                        "NOT_FOUND",
                        "No hay registro de tareas"
                );
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return this.responseApiBuilderService.errorRespuesta(
                    500,
                    "SERVER_ERROR",
                    "ERROR EN EL SERVIDOR"
            );
        }
    }

    public ApiResponse<String> getAllEstado(){
        try {
            List<Estado> gestion = this.referenceRepository.getAllEstado();
            if(gestion!=null && !gestion.isEmpty()){
                return this.responseApiBuilderService.successRespuesta(gestion, "ESTADO");
            }else{
                return this.responseApiBuilderService.errorRespuesta(404,"NOT_FOUND","No hay registro de tareas");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return this.responseApiBuilderService.errorRespuesta(500,"SERVER_ERROR","ERROR EN EL SERVIDOR");
        }
    }
}
