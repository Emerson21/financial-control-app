docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic transferencia_aprovada
docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic transferencia_solicitada
docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic transferencia_recebida
docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic transferencia_reprovada
docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic cqrs_relatorio_transferencia_interna
docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic cqrs_relatorio_transferencia_externa
docker exec -it kafka kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic cqrs_relatorio_transferencia_recusada






