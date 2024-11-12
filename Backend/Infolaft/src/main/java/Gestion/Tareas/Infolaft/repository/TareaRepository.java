package Gestion.Tareas.Infolaft.repository;

import Gestion.Tareas.Infolaft.dto.request.TareaRequest;
import Gestion.Tareas.Infolaft.dto.response.GestionTareasResponse;
import Gestion.Tareas.Infolaft.model.Tarea;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TareaRepository {

    @Results({
            @Result(property = "idTarea", column = "idTarea"),
            @Result(property = "tituloTarea", column = "tituloTarea"),
            @Result(property = "descripcionTarea", column = "descripcionTarea"),
            @Result(property = "fechaVencimiento", column = "fechaVencimiento"),
            @Result(property = "estado.id", column = "idEstado"),
            @Result(property = "estado.nombre", column = "estadoTarea"),
            @Result(property = "prioridad.id", column = "idPrioridad"),
            @Result(property = "prioridad.nombre", column = "prioridadTarea"),
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "updateAt", column = "update_at")
    })
    @Select("select task.id as idTarea, task.titulo as tituloTarea , task.descripcion as descripcionTarea , " +
            "task.fechaVencimiento,estado.nombre as estadoTarea, prioridad.nombre as prioridadTarea, " +
            "estado.id as idEstado ,prioridad.id as idPrioridad ,task.create_at ,task.update_at from TAREA as task " +
            "left join ESTADO as estado on task.idEstado = estado.id " +
            "left join PRIORIDAD as prioridad on task.idPrioridad = prioridad.id " +
            "WHERE estado.id != 4")
    List<GestionTareasResponse> gestionTareas();




    @Results({
            @Result(property = "idTarea", column = "idTarea"),
            @Result(property = "tituloTarea", column = "tituloTarea"),
            @Result(property = "descripcionTarea", column = "descripcionTarea"),
            @Result(property = "fechaVencimiento", column = "fechaVencimiento"),
            @Result(property = "estado.id", column = "idEstado"),
            @Result(property = "estado.nombre", column = "estadoTarea"),
            @Result(property = "prioridad.id", column = "idPrioridad"),
            @Result(property = "prioridad.nombre", column = "prioridadTarea"),
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "updateAt", column = "update_at")
    })
    @Select("select task.id as idTarea, task.titulo as tituloTarea , task.descripcion as descripcionTarea , " +
            "task.fechaVencimiento,estado.nombre estadoTarea, prioridad.nombre as prioridadTarea, " +
            "estado.id as idEstado ,prioridad.id idPrioridad ,task.create_at ,task.update_at from TAREA as task " +
            "left join ESTADO as estado on task.idEstado = estado.id " +
            "left join PRIORIDAD as prioridad on task.idPrioridad = prioridad.id " +
            "WHERE estado.id = 4")
    List<GestionTareasResponse> gestionHistoricoTareas();




    @Select("SELECT * FROM TAREA")
    List<Tarea> findAll();

    @Select("SELECT * FROM TAREA WHERE id = #{id}")
    Tarea findById(@Param("id") Integer id);

    @Insert("INSERT INTO TAREA(titulo, descripcion, idEstado, idPrioridad, fechaVencimiento, create_at, update_at) " +
            "VALUES(#{titulo}, #{descripcion}, #{idEstado}, #{idPrioridad}, #{fechaVencimiento}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(TareaRequest tarea);

    @Update("UPDATE TAREA SET titulo = #{titulo}, descripcion = #{descripcion}, " +
            "idEstado = #{idEstado}, idPrioridad = #{idPrioridad}, " +
            "fechaVencimiento = #{fechaVencimiento}, update_at = NOW() " +
            "WHERE id = #{id}")
    Integer update(TareaRequest tarea);

    @Update("UPDATE TAREA SET idEstado = '4' , update_at = CURRENT_TIMESTAMP WHERE id = #{id} ")
    Integer inactivateById(@Param("id") Integer id);

    @Update("UPDATE TAREA SET idEstado = #{idAnterior}, update_at = CURRENT_TIMESTAMP WHERE id = #{id} ")
    Integer activarById(@Param("id") Integer id,@Param("idAnterior") Integer idAnterior);


}
