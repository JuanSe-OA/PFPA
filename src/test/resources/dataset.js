db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );
db.Moderadores.insertMany([
    {
        _id: 'Moderador4',
        email: 'moderador4@email.com',
        password: 'moderador4',
        _class: 'co.edu.uniquindio.proyecto.model.Documents.Moderador'
    }
]);