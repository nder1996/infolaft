export interface Estado {
  id?: number;
  nombre?: string;
}

export interface Prioridad {
  id?: number;
  nombre?: string;
}

export interface GestionTareasResponse {
  idTarea?: number;
  tituloTarea?: string;
  descripcionTarea?: string;
  fechaVencimiento?: Date;
  estado?: Estado;
  prioridad?: Prioridad;
  createAt?: Date;
  updateAt?: Date;
}