export class AuditoriaModel {
    id?: number;
    idTarea?: number;
    idEstadoAnterior?: number;
    idEstadoNuevo?: number;
    accion?: string;
    create_at?: Date;
    creadoPor?: string;
  
    constructor(
      id?: number,
      idTarea?: number,
      idEstadoAnterior?: number,
      idEstadoNuevo?: number,
      accion?: string,
      create_at?: Date,
      creadoPor?: string
    ) {
      this.id = id;
      this.idTarea = idTarea;
      this.idEstadoAnterior = idEstadoAnterior;
      this.idEstadoNuevo = idEstadoNuevo;
      this.accion = accion;
      this.create_at = create_at;
      this.creadoPor = creadoPor;
    }
  }