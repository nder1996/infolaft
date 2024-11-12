package Gestion.Tareas.Infolaft.service;

import Gestion.Tareas.Infolaft.dto.request.TareaRequest;
import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.model.Auditoria;
import Gestion.Tareas.Infolaft.model.Tarea;
import Gestion.Tareas.Infolaft.model.TareaXAuditoria;
import Gestion.Tareas.Infolaft.repository.TareaRepository;
import Gestion.Tareas.Infolaft.repository.TareaXAuditoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
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




    public ApiResponse<List<GestionTareasResponse>> getAllGestionTareas() {
        try {
            List<GestionTareasResponse> gestion = this.tareaRepository.gestionTareas();
            if (gestion != null && !gestion.isEmpty()) {
                return ResponseApiBuilderService.successResponse(gestion, "GESTION_TAREAS");
            } else {
                return ResponseApiBuilderService.errorResponse(200, "NO_CONTENT", "No hay registro disponibles");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "ERROR EN EL SERVIDOR");
        }
    }

    public ApiResponse<List<GestionTareasResponse>> gestionHistoricoTareas() {
        try {
            List<GestionTareasResponse> gestion = this.tareaRepository.gestionHistoricoTareas();
            if (gestion != null && !gestion.isEmpty()) {
                return ResponseApiBuilderService.successResponse(gestion, "GESTION_TAREAS");
            } else {
                return ResponseApiBuilderService.errorResponse(200, "NO_CONTENT", "No hay registro disponibles");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "ERROR EN EL SERVIDOR");
        }
    }





    public ApiResponse<List<Tarea>> findAll() {
        try {
            List<Tarea> tareas = this.tareaRepository.findAll();
            if (tareas != null && !tareas.isEmpty()) {
                return ResponseApiBuilderService.successResponse(tareas, "TODAS_TAREAS");
            } else {
                return ResponseApiBuilderService.errorResponse(200, "NO_CONTENT", "No hay registro disponibles");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "Error en el servidor");
        }
    }

    public ApiResponse<Tarea> findById(Integer id) {
        try {

            Tarea tarea = this.tareaRepository.findById(id);
            if (tarea != null) {
                return ResponseApiBuilderService.successResponse(tarea, "TAREA_ENCONTRADA");
            } else {
                return ResponseApiBuilderService.errorResponse(200, "NO_CONTENT", "No hay registro disponibles");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "Error en el servidor");
        }
    }

    public ApiResponse<String> inactivateById(Integer id) {
        try {
            Tarea tarea = this.tareaRepository.findById(id);
            int result = this.tareaRepository.inactivateById(id);
            if (result > 0) {
                this.saveAuditoria(tarea.getId(),4,tarea.getIdPrioridad(),"TAREA_BORRADA");
                return ResponseApiBuilderService.successResponse("Tarea borrada correctamente", "TAREA_BORRADA");
            } else {
                return ResponseApiBuilderService.errorResponse(204, "NO_CONTENT", "No hay registro disponibles");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "Error en el servidor");
        }
    }


    public ApiResponse<String> activeTaskById(Integer id) {
        try {
            // Validar que el ID no sea nulo
            if (id == null) {
                return ResponseApiBuilderService.errorResponse(400, "BAD_REQUEST", "El ID de la tarea es requerido");
            }

            // Obtener la última tarea por auditoría
            List<TareaXAuditoria> audiXTarea = tareaXAuditoriaRepository.byIdAuditoriXUltimoTarea(id);

            // Validar que existan registros
            if (audiXTarea == null || audiXTarea.isEmpty()) {
                return ResponseApiBuilderService.errorResponse(404, "NOT_FOUND", "No se encontró la tarea asociada a la auditoría");
            }

            // Obtener el estado de la tarea
            Integer idEstado = null;

            // Obtener la tarea actual
            TareaXAuditoria currentTask;
            if (audiXTarea.size() > 1) {
                currentTask = audiXTarea.get(1);
            } else {
                currentTask = audiXTarea.get(0);
            }

            if (currentTask != null && currentTask.getIdTarea() != null) {
                // Buscar la tarea directamente por ID
                Optional<Auditoria> auditoriaOptional = Optional.ofNullable(tareaXAuditoriaRepository.byIdAuditoriaXTarea(currentTask.getIdAuditoria()));

                if (auditoriaOptional.isPresent()) {
                    idEstado = auditoriaOptional.get().getIdEstado();
                } else {
                    return ResponseApiBuilderService.errorResponse(404, "NOT_FOUND", "No se encontró la tarea en el repositorio");
                }
            } else {
                return ResponseApiBuilderService.errorResponse(400, "BAD_REQUEST", "La tarea no tiene un ID válido");
            }

            // Activar la tarea con el estado encontrado
            int result = tareaRepository.activarById(id, idEstado);
            Tarea taskResult = tareaRepository.findById(id);

            if (result > 0) {
                this.saveAuditoria(taskResult.getId(),taskResult.getIdEstado(),taskResult.getIdPrioridad(),"TAREA RECUPERADA");
                return ResponseApiBuilderService.successResponse("Tarea recuperada exitosamente", "TAREA_RECUPERADA");
            } else {
                return ResponseApiBuilderService.errorResponse(404, "NOT_FOUND", "No se pudo activar la tarea");
            }

        } catch (Exception e) {
            log.error("Error al activar la tarea: " + e.getMessage(), e);
            return ResponseApiBuilderService.errorResponse(500, "SERVER_ERROR", "Error en el servidor: " + e.getMessage());
        }
    }





    public ApiResponse<String> saveUpdateTask(TareaRequest tareaRequest) {
        try {
            // Validar request
            List<String> errores = this.validatorService.validate(tareaRequest);
            if (!errores.isEmpty()) {
                return ResponseApiBuilderService.errorResponse(
                        400,
                        "VALIDATION_ERROR",
                        "Error de validación: " + String.join(", ", errores)
                );
            }

            // Guardar o actualizar tarea

            String responseMessage;
            String responseCode;

            if (tareaRequest.getId() == null) {
                tareaRequest.setCreate_at(new Date());
                tareaRepository.insert(tareaRequest);
                responseMessage = String.format("La tarea %s fue creada con éxito", tareaRequest.getTitulo());
                responseCode = "TAREA_CREADA";
            } else {
                tareaRequest.setUpdate_at(new Date());
                tareaRepository.update(tareaRequest);
                responseMessage = String.format("La tarea %s fue actualizada con éxito", tareaRequest.getTitulo());
                responseCode = "TAREA_ACTUALIZADA";
            }

            // Validar guardado
            if (tareaRequest.getId() == null) {
                return ResponseApiBuilderService.errorResponse(
                        500,
                        "SAVE_ERROR",
                        "Error al guardar la tarea en la base de datos"
                );
            }

            this.saveAuditoria(tareaRequest.getId(),tareaRequest.getIdEstado(),tareaRequest.getIdPrioridad(),responseCode);

            // Retornar respuesta exitosa
            return ResponseApiBuilderService.successResponse(
                    responseMessage,
                    responseCode
            );

        } catch (Exception e) {
            log.error("Error al procesar la tarea: ", e);
            return ResponseApiBuilderService.errorResponse(
                    500,
                    "SERVER_ERROR",
                    "Error en el servidor: " + e.getMessage()
            );
        }
    }

    public void saveAuditoria(Integer idTarea,Integer idEstado,Integer idPrioridad, String tipoAuditoria){
        try {
            Auditoria auditoria = new Auditoria(null,idEstado,idPrioridad,tipoAuditoria,new Date(),"SISTEMA");
            this.tareaXAuditoriaRepository.insertAuditoria(auditoria);

            ///RELACION ENTRE AUDITORIA X TAREA
            TareaXAuditoria tareaXAuditoria = new TareaXAuditoria(null,auditoria.getId(),idTarea );
            this.tareaXAuditoriaRepository.insertTareaXAuditoria(tareaXAuditoria);
            ///
            System.out.println("log auditoria Se guardo");
        } catch (Exception e) {
            log.error("Error al guardar la auditoria: ", e);
        }

    }




}



