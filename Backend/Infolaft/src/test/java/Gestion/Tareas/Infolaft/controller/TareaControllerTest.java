package Gestion.Tareas.Infolaft.controller;

import Gestion.Tareas.Infolaft.dto.request.TareaRequest;
import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.service.ResponseApiBuilderService;
import Gestion.Tareas.Infolaft.service.TareaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(TareaController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TareaControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareaService tareaService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllGestionTareas_WhenSuccess_ShouldReturnOk() throws Exception {
        // Arrange
        List<GestionTareasResponse> tareas = Arrays.asList(
                new GestionTareasResponse() // Añade aquí los datos de ejemplo
        );
        ApiResponse<List<GestionTareasResponse>> apiResponse =
                ResponseApiBuilderService.successResponse(tareas, "GESTION_TAREAS");

        when(tareaService.getAllGestionTareas()).thenReturn(apiResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllGestionTareas_WhenException_ShouldReturnServerError() throws Exception {
        // Arrange
        when(tareaService.getAllGestionTareas())
                .thenThrow(new RuntimeException("Error inesperado"));

        // Act & Assert
        mockMvc.perform(get("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void saveOrUpdateTask_WhenValidRequest_ShouldReturnOk() throws Exception {
        // Arrange
        TareaRequest request = new TareaRequest();
        request.setTitulo("Tarea Test");
        request.setDescripcion("Descripción de prueba");
        // Asegúrate de establecer todos los campos requeridos
        request.setIdEstado(1);
        request.setFechaVencimiento(new Date());
        request.setIdPrioridad(1);
        request.setCreate_at(new Date());

        // ... otros campos requeridos según tu TareaRequest

        ApiResponse<String> successResponse = new ApiResponse<>();
        successResponse.setData("Tarea guardada exitosamente");
        successResponse.setMeta(new ApiResponse.Meta("Operación Exitosa", 200));

        when(tareaService.saveUpdateTask(any(TareaRequest.class)))
                .thenReturn(successResponse);

        // Act & Assert
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/saveOrUpdateTask")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())  // Para ver el detalle del error
                .andExpect(status().isOk());
    }



    @Test
    void saveOrUpdateTask_WhenMissingRequiredFields_ShouldReturnBadRequest() throws Exception {
        // Arrange
        TareaRequest request = new TareaRequest();
        // No establecemos los campos requeridos

        // Act & Assert
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/saveOrUpdateTask")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveOrUpdateTask_WhenTitleExceedsMaxLength_ShouldReturnBadRequest() throws Exception {
        // Arrange
        TareaRequest request = new TareaRequest();
        request.setTitulo("a".repeat(1001)); // Excede el máximo de 1000 caracteres
        request.setDescripcion("Descripción de prueba");
        request.setIdEstado(1);
        request.setIdPrioridad(1);
        request.setFechaVencimiento(new Date());
        request.setCreate_at(new Date());

        // Act & Assert
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/saveOrUpdateTask")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}