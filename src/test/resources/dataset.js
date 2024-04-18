db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );
db.Usuarios.insertMany([
    {
        _id: 'Cliente4',
        nickname: 'maria01',
        ciudad: 'Bogotá',
        fotoPerfil: 'perfil.jpg',
        email: 'maria@email.com',
        password: 'contraseña123',
        nombre: 'María',
        estado: 'INACTIVO',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Usuario'
    },
    {
        _id: 'Cliente5',
        nickname: 'juancho',
        ciudad: 'Cali',
        fotoPerfil: 'imagen.png',
        email: 'juan@example.com',
        password: 'claveJuan',
        nombre: 'Juan',
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Usuario'
    }

]);
db.Negocios.insertMany([
    {
        _id: 'Negocio2',
        nombre: 'Tienda de Ropa',
        descripcion: 'Tienda de ropa para hombres y mujeres en Bogotá',
        codigoCliente: 'Cliente2',
        ubicacion: {
            latitud: 4.624335,
            longitud: -74.063644
        },
        imagenes: ['imagen3', 'imagen4'],
        tipoNegocio: 'TIENDA',
        horarios: [
            {
                dia: 'MARTES',
                horaInicio: '10:00',
                horaFin: '18:00'
            },
            {
                dia: 'MIÉRCOLES',
                horaInicio: '10:00',
                horaFin: '18:00'
            }
        ],
        telefonos: ['9876543', '3216549'],
        estado: 'INACTIVO',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Negocio'
    },
    {
        _id: 'Negocio3',
        nombre: 'Peluquería Estilista',
        descripcion: 'Peluquería para hombres y mujeres en Medellín',
        codigoCliente: 'Cliente3',
        ubicacion: {
            latitud: 6.230833,
            longitud: -75.590553
        },
        imagenes: ['imagen5', 'imagen6'],
        tipoNegocio: 'PELUQUERIA',
        horarios: [
            {
                dia: 'JUEVES',
                horaInicio: '09:00',
                horaFin: '19:00'
            },
            {
                dia: 'VIERNES',
                horaInicio: '09:00',
                horaFin: '19:00'
            }
        ],
        telefonos: ['5555555', '6666666'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Negocio'
    },
    {
        _id: 'Negocio4',
        nombre: 'Gimnasio Fitness',
        descripcion: 'Gimnasio con clases de fitness y entrenamiento personalizado en Cali',
        codigoCliente: 'Cliente4',
        ubicacion: {
            latitud: 3.451647,
            longitud: -76.532919
        },
        imagenes: ['imagen7', 'imagen8'],
        tipoNegocio: 'GIMNASIO',
        horarios: [
            {
                dia: 'LUNES',
                horaInicio: '06:00',
                horaFin: '22:00'
            },
            {
                dia: 'SÁBADO',
                horaInicio: '08:00',
                horaFin: '18:00'
            }
        ],
        telefonos: ['1111111', '2222222'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Negocio'
    },
    {
        _id: 'Negocio5',
        nombre: 'Librería Fantasía',
        descripcion: 'Librería especializada en literatura fantástica y ciencia ficción en Barranquilla',
        codigoCliente: 'Cliente5',
        ubicacion: {
            latitud: 10.980692,
            longitud: -74.805365
        },
        imagenes: ['imagen9', 'imagen10'],
        tipoNegocio: 'LIBRERIA',
        horarios: [
            {
                dia: 'MARTES',
                horaInicio: '09:00',
                horaFin: '19:00'
            },
            {
                dia: 'JUEVES',
                horaInicio: '09:00',
                horaFin: '19:00'
            }
        ],
        telefonos: ['3333333', '4444444'],
        estado: 'ACTIVO',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Negocio'
    }
]);
db.Comentarios.insertMany([
    {
        mensaje: "Excelente sitio, muy buena atención",
        fecha: new Date(),
        codigoCliente: 'Cliente1',
        codigoNegocio: 'Negocio1',
        calificacion: 5,
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Comentario'
    },
    {
        mensaje: "Gran experiencia, ambiente acogedor",
        fecha: new Date(),
        codigoCliente: "Cliente2",
        codigoNegocio: "Negocio2",
        calificacion: 4,
        _class: "co.edu.uniquindio.proyecto.model.Documents.Comentario"
    },
    {
        mensaje: "Servicio rápido y eficiente, definitivamente regresaré",
        fecha: new Date(),
        codigoCliente: "Cliente3",
        codigoNegocio: "Negocio3",
        calificacion: 5,
        _class: "co.edu.uniquindio.proyecto.model.Documents.Comentario"
    },
    {
        mensaje: "Buena comida, precios justos",
        fecha: "2024-04-10T19:45:00Z",
        codigoCliente: "Cliente4",
        codigoNegocio: "Negocio4",
        calificacion: 4,
        _class: "co.edu.uniquindio.proyecto.model.Documents.Comentario"
    },
    {
        mensaje: "Atención al cliente deficiente, esperaba más",
        fecha: new Date(),
        codigoCliente: "Cliente5",
        codigoNegocio: "Negocio5",
        calificacion: 2,
        _class: "co.edu.uniquindio.proyecto.model.Documents.Comentario"
    }

]);
db.Moderadores.insertMany([
    {
        _id: "Moderador3",
        email: "moderador3@email.com",
        password : "moderador3"
    },
    {
        _id: "Moderador4",
        email: "moderador4@email.com",
        password : "moderador4"
    }
]);