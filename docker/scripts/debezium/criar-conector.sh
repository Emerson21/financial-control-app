curl -X POST http://localhost:8083/connectors/ -H 'content-type: application/json' \
-d '{
      "name": "transferencia-solicitada-connector",
      "config": {
        "connector.class": "io.debezium.connector.mongodb.MongoDbConnector",
        "tasks.max": "1",
        "mongodb.hosts": "mongo:27017",
        "mongodb.user": "admin",
        "mongodb.password": "admin",
        "mongodb.name": "transferencia-solicitada-connector",
        "database.history.kafka.bootstrap.servers": "kafka:29092",
        "database.include.list": "transferencias",
        "collection.include.list": "transferencias.solicitada",
        "include.schema.changes": "false",
        "key.converter": "org.apache.kafka.connect.json.JsonConverter",
        "key.converter.schemas.enable": "false",
        "value.converter": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter.schemas.enable": "false",
        "value.converter.delegate.converter.type": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter.delegate.converter.type.schemas.enable": "false",
        "transforms": "outbox",
        "transforms.outbox.type": "io.debezium.connector.mongodb.transforms.outbox.MongoEventRouter",
        "transforms.outbox.collection.expand.json.payload": "true"
      }
    }'