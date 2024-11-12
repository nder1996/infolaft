package Gestion.Tareas.Infolaft.repository;

import Gestion.Tareas.Infolaft.model.Auditoria;
import Gestion.Tareas.Infolaft.model.TareaXAuditoria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TareaXAuditoriaRepository {


    @Insert("INSERT INTO AUDITORIA(accion, create_at, creadoPor, idEstado,idPrioridad ) " +
            "VALUES(#{accion}, NOW(), #{creadoPor}, #{idEstado}, #{idPrioridad})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAuditoria(Auditoria auditoria);


    @Insert("INSERT INTO TAREA_AUDITORIA(idTarea, idAuditoria) " +
            "VALUES(#{idTarea}, #{idAuditoria})")
    Integer insertTareaXAuditoria(TareaXAuditoria tareaXAuditoria);


    @Select("select auditoria.id from AUDITORIA as auditoria where auditoria.idEstado = #{idEstado}")
    Integer byIdAuditoria(@Param("idEstado") Integer idEstado);

    @Select("select * from AUDITORIA as auditoria where auditoria.id = #{idTarea}")
    Auditoria byIdAuditoriaXTarea(@Param("idTarea") Integer idTarea);

    @Select("SELECT * FROM TAREA_AUDITORIA as audiXTarea where audiXTarea.idTarea = #{idTarea} ORDER BY id DESC LIMIT 2")
    List<TareaXAuditoria> byIdAuditoriXUltimoTarea(@Param("idTarea") Integer idTarea);

}
