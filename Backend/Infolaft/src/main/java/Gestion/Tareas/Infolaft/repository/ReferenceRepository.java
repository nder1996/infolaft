package Gestion.Tareas.Infolaft.repository;

import Gestion.Tareas.Infolaft.model.Estado;
import Gestion.Tareas.Infolaft.model.Prioridad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReferenceRepository {

    @Select("select * from PRIORIDAD where estado = 'A' ")
    List<Prioridad> getAllPrioridad();


    @Select("select * from ESTADO where estado = 'A' ")
    List<Estado> getAllEstado();

}
