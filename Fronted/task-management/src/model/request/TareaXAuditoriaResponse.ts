export class TareaXAuditoriaModel {
    id?: number;
    idAuditoria?: number;
    idTarea?: number;
  
    constructor(
      id?: number,
      idAuditoria?: number,
      idTarea?: number
    ) {
      this.id = id;
      this.idAuditoria = idAuditoria;
      this.idTarea = idTarea;
    }
  }