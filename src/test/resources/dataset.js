db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );
db.Usuarios.insertMany([
    {
        _id: 'Cliente4',
        nombreusuario: 'maria01',
        ciudad: 'Bogotá',
        fotoPerfil: 'perfil.jpg',
        email: 'maria@email.com',
        password: 'contraseña123',
        nombre: 'María',
        estado: 'INACTIVO',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Usuario'
    },


]);
db.Negocios.insertMany([
    {
        _id: ObjectId('661ffd9dd090bb1e457e559f'),
        nombre: 'Luiggi\'s Pizza',
        descripcion: 'descripcion',
        codigoUsuario: 'Cliente4',
        direccion: 'Direccion',
        telefonos: [
            '3120985665'
        ],
        tipoNegocio: 'RESTAURANTE',
        imagenes: [
            'Imagen.PNG',
            'Imagen2.PNG'
        ],
        estadoRegistro: 'ACTIVO',
        horarios: [
            {
                dia: 'Lunes',
                horaInicio: ISODate('2024-04-17T14:00:00.000Z'),
                horaFin: ISODate('2024-04-18T03:00:00.000Z')
            },
            {
                dia: 'Jueves',
                horaInicio: ISODate('2024-04-17T15:00:00.000Z'),
                horaFin: ISODate('2024-04-18T03:00:00.000Z')
            },
            {
                dia: 'Sabado',
                horaInicio: ISODate('2024-04-17T15:00:00.000Z'),
                horaFin: ISODate('2024-04-18T04:00:00.000Z')
            }
        ],
        ubicacion: {
            longitud: 4.098886,
            latitud: -88.09234
        },
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Negocio'
    },
]);
db.Comentarios.insertMany([
    {
        _id: ObjectId('661f5655365454180f50734c'),
        mensaje: 'Jamás vuelvo ahí',
        codigoUsuario: 'Cliente1',
        codigoNegocio: 'negocio1',
        calificacion: 1,
        fecha: ISODate('2024-04-16T05:00:00.000Z'),
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Comentario'
    }
]);