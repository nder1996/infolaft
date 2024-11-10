export class TareaModel {
    id?: number;
    titulo?: string;
    descripcion?: string;
    idEstado?: number;
    idPrioridad?: number;
    fechaVencimiento?: Date;
    create_at?: Date;
    update_at?: Date;
  
    constructor(
      id?: number,
      titulo?: string,
      descripcion?: string,
      idEstado?: number,
      idPrioridad?: number,
      fechaVencimiento?: Date,
      create_at?: Date,
      update_at?: Date
    ) {
      this.id = id;
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.idEstado = idEstado;
      this.idPrioridad = idPrioridad;
      this.fechaVencimiento = fechaVencimiento;
      this.create_at = create_at;
      this.update_at = update_at;
    }
  }