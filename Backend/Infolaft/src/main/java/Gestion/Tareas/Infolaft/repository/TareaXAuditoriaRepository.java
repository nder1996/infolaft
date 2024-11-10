package Gestion.Tareas.Infolaft.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TareaXAuditoriaRepository {



    @Insert("INSERT INTO TAREA_AUDITORIA(idTarea, idAuditoria) " +
            "VALUES('CREAR_TAREA', 'SISTEMA' , NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertTareaXAuditoria(@Param("idTarea") Integer idTarea ,@Param("idAuditoria") Integer idAuditoria );


    @Select("select auditoria.id from AUDITORIA as auditoria where auditoria.idEstado = #{idEstado}")
    Integer byIdAuditoria(@Param("idEstado") Integer idEstado);



}
