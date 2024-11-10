export class GestionTasksRequest {
    id?: number;
    titulo?: string;
    descripcion?: string;
    estado?: string;
    prioridad?: string;
    fechaVencimiento?: Date;
    create_at?: Date;
    update_at?: Date;
  
    constructor(
      id?: number,
      titulo?: string,
      descripcion?: string,
      estado?: string,
      prioridad?: string,
      fechaVencimiento?: Date,
      create_at?: Date,
      update_at?: Date
    ) {
      this.id = id;
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.estado = estado;
      this.prioridad = prioridad;
      this.fechaVencimiento = fechaVencimiento;
      this.create_at = create_at;
      this.update_at = update_at;
    }
  }