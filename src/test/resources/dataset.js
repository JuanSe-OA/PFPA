db = connect( 'mongodb://root:example@localhost:27017/proyecto?authSource=admin' );

db.Moderadores.insertMany([
    {
        _id: "Moderador1",
        email: "moderador1@email.com",
        password : "moderador1"
    },
    {
        _id: "Moderador2",
        email: "moderador2@email.com",
        password : "moderador2"
    },
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