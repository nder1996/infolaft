import { Component } from '@angular/core';

// product.interface.ts
export interface Product {
  code: string;
  name: string;
  category: string;
  quantity: number;
}



@Component({
  selector: 'app-gestion-tareas',
  templateUrl: './gestion-tareas.component.html',
  styleUrls: ['./gestion-tareas.component.css']
})
export class GestionTareasComponent {

  products: Product[] = [];


  ngOnInit() {
    // Aquí inicializamos los datos de ejemplo
    this.products = [
      {
        code: 'P001',
        name: 'Laptop Dell XPS',
        category: 'Electronics',
        quantity: 25
      },
      {
        code: 'P002',
        name: 'iPhone 14 Pro',
        category: 'Mobile Phones',
        quantity: 50
      },
      {
        code: 'P003',
        name: 'Samsung Smart TV',
        category: 'Electronics',
        quantity: 15
      },
      {
        code: 'P004',
        name: 'AirPods Pro',
        category: 'Accessories',
        quantity: 100
      },
      {
        code: 'P005',
        name: 'Gaming Mouse',
        category: 'Accessories',
        quantity: 75
      }
    ];
  }
}
