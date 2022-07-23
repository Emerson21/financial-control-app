rs.initiate( { _id: "rs0", members: [ { _id: 0, host: "localhost:27017" }] } )

db = db.getSiblingDB('financial')
db.createUser(
    {
        user: 'admin',
        pwd: 'admin',
        roles: [{role: 'readWrite', db: 'financial'}]
    }
)
db.createCollection('eventos_transacoes')
db.createCollection('outbox')

db = db.getSiblingDB('centralbank')
db.createUser(
    {
        user: 'admin',
        pwd: 'admin',
        roles: [{role: 'readWrite', db: 'centralbank'}]
    }
)
db.createCollection('eventos_transacoes')
db.createCollection('outbox')
