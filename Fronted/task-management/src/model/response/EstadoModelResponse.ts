export class EstadoResponse {
    id?: number;
    nombre?: string;
    descripcion?: string;
    estado?: string;
    create_at?: Date;
  
    constructor(
      id?: number,
      nombre?: string,
      descripcion?: string,
      estado?: string,
      create_at?: Date
    ) {
      this.id = id;
      this.nombre = nombre;
      this.descripcion = descripcion;
      this.estado = estado;
      this.create_at = create_at;
    }
  }