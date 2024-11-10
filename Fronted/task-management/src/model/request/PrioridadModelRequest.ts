export class PrioridadModel {
    id?: number;
    nombre?: string;
    nivel?: number;
    codigoColor?: string;
    estado?: string;
  
    constructor(
      id?: number,
      nombre?: string,
      nivel?: number,
      codigoColor?: string,
      estado?: string
    ) {
      this.id = id;
      this.nombre = nombre;
      this.nivel = nivel;
      this.codigoColor = codigoColor;
      this.estado = estado;
    }
  }