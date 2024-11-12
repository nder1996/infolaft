package Gestion.Tareas.Infolaft.service;

import Gestion.Tareas.Infolaft.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ResponseApiBuilderService {



    public static <T> ApiResponse<T> successResponse(T data, String key) {
        ApiResponse<T> response = new ApiResponse<>();
        try {
            response.setMeta(new ApiResponse.Meta("Operación Exitosa", 200));
            response.setData(data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return response;
    }


    public static <T> ApiResponse<T> errorResponse(Integer codeHttp, String codeName, String codeDescription) {
        ApiResponse<T> response = new ApiResponse<>();
        try {
            response.setData(null);
            response.setMeta(new ApiResponse.Meta("Solicitud Incorrecta", codeHttp));
            response.setError(new ApiResponse.ErrorDetails(codeName, codeDescription));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return response;
    }
}
