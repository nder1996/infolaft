# Sistema de Gestión de Tareas

Sistema desarrollado con Angular 16 y Spring Boot 3.2 para la gestión de tareas con historial de cambios, fechas de vencimiento, filtros avanzados y niveles de prioridad. Implementado con arquitectura de microservicios para mejorar la escalabilidad y el mantenimiento de la aplicación. La base de datos está alojada en la nube para garantizar accesibilidad y respaldo continuo, permitiendo un rendimiento óptimo y disponibilidad constante.

## 🚀 Características Principales

- CRUD completo de tareas
- Sistema de prioridades (Alta, Media, Baja)
- Control de fechas de vencimiento de tareas
- Filtros avanzados por todos los campos
- Historial de cambios con recuperación de tareas eliminadas
- Interfaz moderna con PrimeNG y Bootstrap 5.2
- Diseño responsive y amigable al usuario
- Base de datos en la nube para acceso permanente
- Pantalla de carga oscura con bloqueo de eventos durante operaciones
- Sistema de prevención de doble envío de formularios

## 💻 Tecnologías Utilizadas

### Frontend
- Angular v16
- PrimeNG (Componentes UI)
- Bootstrap v5.2
- TypeScript
- HTML5/CSS3

### Backend
- Spring Boot 3.2
- Java 17+
- MyBatis
- Base de datos MySQL/PostgreSQL en la nube

## 🛠️ Instalación

### Clonar el Repositorio
```bash
git clone https://github.com/nder1996/infolaft.git
```

### Frontend
```bash
cd frontend
npm install
ng serve
```

### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

## 🔄 Funcionalidades Actuales

### Gestión de Tareas
- Crear, editar y eliminar tareas
- Establecer fechas de vencimiento
- Sistema de prioridades (Alta, Media, Baja)
- Filtrado por fecha, prioridad, estado y otros campos
- Seguimiento del estado de las tareas
- Recuperación de tareas eliminadas
- Historial de modificaciones
- Persistencia de datos en la nube

## 🚀 Mejoras Posibles

### Gestión Avanzada
- Sistema de roles y permisos (Admin, Usuario, Supervisor)
- Autenticación con JWT
- Notificaciones de tareas próximas a vencer
- Recordatorios por correo electrónico

### Funcionalidades Adicionales
- Etiquetas personalizadas
- Sistema de comentarios en tareas
- Adjuntar archivos

### Colaboración
- Compartir tareas entre usuarios
- Chat interno
- Menciones a usuarios (@usuario)
- Integraciones con calendario

## 👥 Autor

* **Anderson Arévalo Madrid** - *Desarrollo Completo* - [nder1996](https://github.com/nder1996)

## 📝 Licencia

Este proyecto está bajo la licencia [MIT](LICENSE)
