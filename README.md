PFPA  (Proyecto Final Programación Avanzada)- Sistema de Gestión de Negocios Geolocalizados
PFPA es una aplicación web diseñada para centralizar y visualizar establecimientos comerciales en un entorno cartográfico interactivo. La plataforma permite a los usuarios dar de alta negocios, asociarlos a ubicaciones precisas y gestionar la información comercial de manera eficiente.

Funcionalidades Principales
Registro de Negocios: Interfaz para la creación y administración de perfiles comerciales con datos detallados.

Visualización en Mapa: Renderizado dinámico de los establecimientos registrados utilizando coordenadas geográficas.

Gestión de Marcadores: Control total sobre los puntos de interés, permitiendo actualizar ubicaciones y detalles del comercio.

Búsqueda Geoespacial: Localización de negocios basada en la proximidad del usuario o áreas específicas.

Persistencia de Datos: Almacenamiento seguro de la información comercial y sus coordenadas en una base de datos no relacional.

Stack Tecnológico
Frontend: React, TypeScript, Tailwind CSS.

Motor de Mapas: [Especificar motor: Leaflet / Mapbox / Google Maps].

Backend: Node.js, Express.

Base de Datos: MongoDB con soporte para índices geoespaciales (GeoJSON).

Gestión de Estado: Redux Toolkit.

Instalación
Clonar el repositorio: git clone https://github.com/JuanSe-OA/PFPA.git

Instalar dependencias: Ejecutar npm install en las carpetas correspondientes.

Configuración: Configurar las claves de API del servicio de mapas y la URI de conexión a la base de datos en un archivo .env.

Ejecución: Iniciar el entorno de desarrollo con npm run dev.
