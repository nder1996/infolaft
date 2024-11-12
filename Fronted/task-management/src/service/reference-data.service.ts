import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { enviroment } from 'src/enviroments/enviroment';
import { ApiResponse } from 'src/model/ApiResponse';
import { EstadoResponse } from 'src/model/response/EstadoModelResponse';
import { PrioridadResponse } from 'src/model/response/PrioridadModelResponse';

@Injectable({
  providedIn: 'root'
})
export class ReferenceDataService {

  public apiUrl: string = enviroment.apiRestURL + '/reference'

  constructor(private http: HttpClient) { }

  getAllPrioridades(): Observable<ApiResponse<PrioridadResponse[]>> {
    return this.http.get<ApiResponse<PrioridadResponse[]>>(`${this.apiUrl}/getAllPrioridad`);
  }

  getAllEstados(): Observable<ApiResponse<EstadoResponse[]>> {
    return this.http.get<ApiResponse<EstadoResponse[]>>(`${this.apiUrl}/getAllEstado`);
  }

}
