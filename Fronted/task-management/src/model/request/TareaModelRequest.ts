export interface TareaModelRequest {
    id: number;
    titulo: string;
    descripcion: string;
    idEstado: number;
    idPrioridad: number;
    fechaVencimiento: Date;
    create_at?: Date;
    update_at?: Date;
  }
