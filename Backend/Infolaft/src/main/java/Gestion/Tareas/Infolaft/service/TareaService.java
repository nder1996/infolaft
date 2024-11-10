package Gestion.Tareas.Infolaft.service;

import Gestion.Tareas.Infolaft.dto.request.TareaRequest;
import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.model.Tarea;
import Gestion.Tareas.Infolaft.model.TareaXAuditoria;
import Gestion.Tareas.Infolaft.repository.TareaRepository;
import Gestion.Tareas.Infolaft.repository.TareaXAuditoriaRepository;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TareaService {

    @Autowired
    TareaRepository tareaRepository;

    @Autowired
    TareaXAuditoriaRepository tareaXAuditoriaRepository;

    @Autowired
    ResponseApiBuilderService responseApiBuilderService;


    @Autowired
    validationService validatorService;

    @Getter
    @Setter
    public boolean tipoOperacion=false;


    public ApiResponse<String> getAllGestionTareas() {
        try {
            List<GestionTareasResponse> gestion = this.tareaRepository.gestionTareas();
            if (gestion != null && !gestion.isEmpty()) {
                return this.responseApiBuilderService.successRespuesta(gestion, "GESTION_COTIZACION");
            } else {
                return this.responseApiBuilderService.errorRespuesta(404, "NOT_FOUND", "No hay registro de tareas");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return this.responseApiBuilderService.errorRespuesta(500, "SERVER_ERROR", "ERROR EN EL SERVIDOR");
        }
    }

    public ApiResponse<String> findAll() {
        try {
            List<Tarea> tareas = this.tareaRepository.findAll();
            if (tareas != null && !tareas.isEmpty()) {
                return responseApiBuilderService.successRespuesta(tareas, "TODAS_TAREAS");
            } else {
                return responseApiBuilderService.errorRespuesta(404, "NOT_FOUND", "No hay registros de tareas");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return responseApiBuilderService.errorRespuesta(500, "SERVER_ERROR", "Error en el servidor");
        }
    }

    public ApiResponse<String> findById(Integer id) {
        try {
            Tarea tarea = this.tareaRepository.findById(id);
            if (tarea != null) {
                return responseApiBuilderService.successRespuesta(tarea, "TAREA_ENCONTRADA");
            } else {
                return responseApiBuilderService.errorRespuesta(404, "NOT_FOUND", "No se encontró la tarea");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return responseApiBuilderService.errorRespuesta(500, "SERVER_ERROR", "Error en el servidor");
        }
    }

    public ApiResponse<String> inactivateById(Integer id) {
        try {
            int result = this.tareaRepository.inactivateById(id);
            if (result > 0) {
                return responseApiBuilderService.successRespuesta("Tarea inactivada correctamente", "TAREA_BORRADA");
            } else {
                return responseApiBuilderService.errorRespuesta(404, "NOT_FOUND", "No se encontró la tarea para Borrar");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return responseApiBuilderService.errorRespuesta(500, "SERVER_ERROR", "Error en el servidor");
        }
    }


    public ApiResponse<String> saveTask(TareaRequest tareaRequest) {
        try {
            // Validar TareaRequest
            List<String> errores = this.validatorService.validate(tareaRequest);
            if (!errores.isEmpty()) {
                return responseApiBuilderService.errorRespuesta(
                        400,
                        "VALIDATION_ERROR",
                        "Error de validación: " + String.join(", ", errores)
                );
            }

            if(tareaRequest.getId() == null){
                tareaRequest.setCreate_at(new Date());
                tareaRepository.insert(tareaRequest);
                this.tipoOperacion = false;
            }else{
                tareaRequest.setUpdate_at(new Date());
                tareaRepository.update(tareaRequest);
                this.tipoOperacion = true;
            }

            // Validar que se haya guardado correctamente
            if (tareaRequest.getId() == null) {
                return responseApiBuilderService.errorRespuesta(
                        500,
                        "SAVE_ERROR",
                        "Error al guardar la tarea en la base de datos"
                );
            }

            Integer idAuditoria = this.tareaXAuditoriaRepository.byIdAuditoria(tareaRequest.getIdEstado());

            // Registrar en AuditoriaXTarea
            TareaXAuditoria auditoriaXTarea = new TareaXAuditoria();
            auditoriaXTarea.setIdAuditoria(idAuditoria);
            auditoriaXTarea.setIdTarea(tareaRequest.getId());

            if(tipoOperacion==false){
                return responseApiBuilderService.successRespuesta(
                        String.format("La tarea %s fue creada con exito", tareaRequest.getTitulo()),
                        "TAREA_CREADA"
                );
            }else{
                return responseApiBuilderService.successRespuesta(
                        String.format("La tarea %s fue actualizada con exito", tareaRequest.getTitulo()),
                        "TAREA_CREADA"
                );
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return responseApiBuilderService.errorRespuesta(
                    500,
                    "SERVER_ERROR",
                    "Error en el servidor: " + e.getMessage()
            );
        }
    }






}



