import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { firstValueFrom } from 'rxjs';
import { TareaModelRequest } from 'src/model/request/TareaModelRequest';
import { EstadoResponse } from 'src/model/response/EstadoModelResponse';
import { GestionTareasResponse } from 'src/model/response/GestionTareasResponse';
import { PrioridadResponse } from 'src/model/response/PrioridadModelResponse';
import { ReferenceDataService } from 'src/service/reference-data.service';
import { TasksService } from 'src/service/tasks.service';


interface Prioridad {
  id: number;
  nombrePrioridad: string;
}
@Component({
  selector: 'app-gestion-tareas',
  templateUrl: './gestion-tareas.component.html',
  styleUrls: ['./gestion-tareas.component.css']
})
export class GestionTareasComponent implements OnInit, AfterViewInit {

  constructor(private tareaService: TasksService, private cdr: ChangeDetectorRef,
    private messageService: MessageService, private referenceDataService: ReferenceDataService, private fb: FormBuilder,
    private router: Router) {
    this.taskForm = this.fb.group({
      idTarea: [],
      titulo: ['', [Validators.required]],
      descripcion: ['', [Validators.required]],
      fechaVencimiento: [null, [Validators.required]],
      prioridad: [null, [Validators.required]],
      estado: [null, [Validators.required]]
    });
  }

  public listTask: GestionTareasResponse[] = []
  public listHistoryTask: GestionTareasResponse[] = [];
  public listPrioridades: PrioridadResponse[] = [];
  public listEstados: EstadoResponse[] = [];
  public idEliminar: number = 0;

  public actionEditarCrud: boolean = false;
  public taskForm!: FormGroup;
  public showHistoryTask: boolean = false;
  public showTask: boolean = false;
  public showDelete: boolean = false;



  getSeverity(estado: number): string {
    switch (estado) {
      case 1: // Pendiente
        return 'warning';   // Amarillo para tareas no iniciadas
      case 2: // En Progreso
        return 'info';      // Azul para tareas en ejecución
      case 3: // Completado
        return 'success';   // Verde para tareas finalizadas
      case 4: // Cancelado
        return 'danger';    // Rojo para tareas canceladas
      case 5: // Inactivo
        return 'secondary'; // Gris para tareas suspendidas
      default:
        return 'info';
    }
  }



  async ngOnInit() {
    console.log('3. NgOnInit - Después del constructor');
    await this.getTasks();
  }

  ngAfterViewInit(): void {
    console.log('4. NgAfterViewInit - Después de inicializar la vista');
    this.cdr.detectChanges();

  }

  reloadPage() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });

  }


  onSubmit() {
    if (this.taskForm.valid) {
      const task: TareaModelRequest = {
        id: this.taskForm.get('idTarea')?.value || null,
        titulo: this.taskForm.get('titulo')?.value,
        descripcion: this.taskForm.get('descripcion')?.value,
        idEstado: this.taskForm.get('estado')?.value,
        idPrioridad: this.taskForm.get('prioridad')?.value,
        fechaVencimiento: new Date(this.taskForm.get('fechaVencimiento')?.value),
        create_at: new Date(),
        update_at: new Date()
      };
      this.createTask(task);
      if (this.actionEditarCrud) {
        // Lógica de edición
      } else {
        // Lógica de creación
      }
    } else {
      // Marcar todos los campos como touched para mostrar los errores
      Object.keys(this.taskForm.controls).forEach(key => {
        const control = this.taskForm.get(key);
        control?.markAsTouched();
      });
    }
  }


  async cargarModalAgregar() {
    this.taskForm.reset();
    await this.getAllPrioridades();
    await this.getAllEstados();
    this.actionEditarCrud = false;
    this.showTask = true;
    this.cdr.detectChanges();
  }

  selectedPrioridad: PrioridadResponse = new PrioridadResponse();
  selectedEstado: EstadoResponse = new EstadoResponse();
  

  async cargarModalEditar(task: GestionTareasResponse) {
    if (task) {
      await this.getAllPrioridades();
      await this.getAllEstados();
      this.selectedPrioridad = task.prioridad ?? new PrioridadResponse();
      this.selectedEstado = task.estado ?? new EstadoResponse();
      console.log('modal editar : ' + JSON.stringify(this.selectedPrioridad))
      this.taskForm.patchValue({
        idTarea: task.idTarea,
        titulo: task.tituloTarea,
        descripcion: task.descripcionTarea,
        fechaVencimiento: task.fechaVencimiento ? new Date(task.fechaVencimiento) : null,
        estado: task.estado,
        prioridad: task.prioridad,
      });
      console.log("datos de form : " + JSON.stringify(this.taskForm.value))
    }
    this.actionEditarCrud = true;
    this.showTask = true;

    this.cdr.detectChanges();
  }


  async dialogHistoryTask() {
    await this.getHistoyTasks();
  }

  onCancel() {
    this.showTask = false;
    this.taskForm.reset();
  }


  async createTask(task: TareaModelRequest): Promise<void> {
    try {
      const response = await firstValueFrom(this.tareaService.createTask(task));
  
      if (response.meta.statusCode !== 200) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: response.meta.message || 'Error al crear la tarea'
        });
        return;
      }
  
      this.messageService.add({
        severity: 'success',
        summary: 'Éxito',
        detail: 'Tarea creada exitosamente'
      });
  
      // Reset del formulario o estado si es necesario
      this.taskForm.reset();
      // Recargar datos o actualizar vista
      this.reloadPage();
  
    } catch (error) {
      console.error('Error al crear la tarea:', error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Error al crear la tarea'
      });
    }
  }


  async activarTarea(idTarea: number): Promise<void> {
    try {
      const response = await firstValueFrom(this.tareaService.activarTarea(idTarea));

      if (response.meta.statusCode !== 200) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: response.meta.message || 'Error desconocido'
        });
        return;
      }

      this.messageService.add({
        severity: 'success',
        summary: 'Éxito',
        detail: 'Tarea Activada'
      });

      this.showHistoryTask = false;
      this.reloadPage();

    } catch (error) {
      console.error('Error al activar la tarea:', error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Error al activar la tarea'
      });
    }
  }


  async deleteTask(id: number): Promise<void> {
    try {
      const response = await firstValueFrom(this.tareaService.inactivateTask(id));
      if (response.error) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: response.error.description || 'Error desconocido'
        });
        return;
      }

      this.messageService.add({
        severity: 'success',
        summary: 'Éxito',
        detail: 'Tarea Borrada'
      });


      this.showDelete = false;
      this.reloadPage()


    } catch (error) {
      console.error('Error al inactivar la tarea:', error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Error al inactivar la tarea'
      });
    }
  }


  async getTasks(): Promise<void> {
    try {
      const response = await firstValueFrom(this.tareaService.getTasks());
      if (response.error) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: response.error.description || 'Error desconocido'
        });
        return;
      }

      if (!response.data?.length) {
        this.messageService.add({
          severity: 'warn',
          summary: 'Advertencia',
          detail: 'No se encontraron tareas'
        });
        this.listTask = [];
        return;
      }
      this.listTask = response.data;
    } catch (error) {
      console.error('Error al obtener tareas:', error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Error al cargar las tareas'
      });
      this.listTask = [];
    }
  }

  async getHistoyTasks(): Promise<void> {
    try {
      const response = await firstValueFrom(this.tareaService.getHistoryTasks());
      if (response.error) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: response.error.description || 'Error desconocido'
        });
        return;
      }

      if (!response.data?.length) {
        this.messageService.add({
          severity: 'warn',
          summary: 'Advertencia',
          detail: 'No se encontraron tareas'
        });
        this.listHistoryTask = [];
        return;
      }

      this.listHistoryTask = response.data;

    } catch (error) {
      console.error('Error al obtener tareas:', error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Error al cargar las tareas'
      });
      this.listHistoryTask = [];
    }
  }


  async getAllPrioridades(): Promise<void> {
    try {
      const response = await firstValueFrom(this.referenceDataService.getAllPrioridades());
      if (response.error) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: response.error.description || 'Error desconocido'
        });
        return;
      }

      if (!response.data?.length) {
        this.messageService.add({
          severity: 'warn',
          summary: 'Advertencia',
          detail: 'No se encontraron prioridades'
        });
        this.listPrioridades = [];
        return;
      }
      this.listPrioridades = response.data;
      // console.log("prioridades : " + JSON.stringify(this.listPrioridades))
    } catch (error) {
      console.error('Error al obtener prioridades:', error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Error al cargar las prioridades'
      });
      this.listPrioridades = [];
    }
  }

  async getAllEstados(): Promise<void> {
    try {
      const response = await firstValueFrom(this.referenceDataService.getAllEstados());
      if (response.error) {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: response.error.description || 'Error desconocido'
        });
        return;
      }

      if (!response.data?.length) {
        this.messageService.add({
          severity: 'warn',
          summary: 'Advertencia',
          detail: 'No se encontraron estados'
        });
        this.listEstados = [];
        return;
      }
      this.listEstados = response.data;
      //console.log("estados : " + JSON.stringify(this.listEstados))
    } catch (error) {
      console.error('Error al obtener estados:', error);
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Error al cargar los estados'
      });
      this.listEstados = [];
    }
  }


}
