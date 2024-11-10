import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrincipalComponent } from './pages/principal/principal.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { GestionTareasComponent } from './components/gestion-tareas/gestion-tareas.component';

// Opción 1: Rutas planas
const routes: Routes = [
  { path: '', redirectTo: 'principal', pathMatch: 'full' },
  { path: 'principal', component: PrincipalComponent },
  { path: 'tareas', component: GestionTareasComponent },
  { 
    path: 'dashboard', 
    component: DashboardComponent,
    children: [
      { path: 'tasks', component: GestionTareasComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
