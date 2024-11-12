import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { enviroment } from 'src/enviroments/enviroment';
import { ApiResponse } from 'src/model/ApiResponse';
import { TareaModelRequest } from 'src/model/request/TareaModelRequest';
import { GestionTareasResponse } from 'src/model/response/GestionTareasResponse';


@Injectable({
  providedIn: 'root'
})
export class TasksService {

  public apiUrl: string = enviroment.apiRestURL

  constructor(private http: HttpClient) { }

  getTasks(): Observable<ApiResponse<GestionTareasResponse[]>> {
    return this.http.get<ApiResponse<GestionTareasResponse[]>>(`${this.apiUrl}/tasks`);
  }

  getHistoryTasks(): Observable<ApiResponse<GestionTareasResponse[]>> {
    return this.http.get<ApiResponse<GestionTareasResponse[]>>(`${this.apiUrl}/history/tasks`);
  }

  inactivateTask(id: number): Observable<ApiResponse<string>> {
    return this.http.delete<ApiResponse<string>>(`${this.apiUrl}/${id}`);
  }

  activarTarea(id: number): Observable<ApiResponse<string>> {
    return this.http.put<ApiResponse<string>>(`${this.apiUrl}/activeTask/${id}`, {});
  }


  createTask(tarea: TareaModelRequest): Observable<ApiResponse<string>> {
    return this.http.put<ApiResponse<string>>(`${this.apiUrl}/saveOrUpdateTask`, tarea);
  }



}
