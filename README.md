Pastas producer e consumer são conceitos introdutórios do início do curso.

Pasta springkafka contém producer e consumer e docker compose com
kafdrop, rest-proxy, schema-registry, broker, zookeeper. Para acessar localhost:9000

Pasta springkafka-consumer e springkafka-producer rodam separados e docker compose com
landoop. Para acessar localhost:3030

1) importar no Postman o arquivo: rest proxy.postman_collection.json

2) abrir um terminal dentro da pasta do projeto onde contém o arquivo docker-compose.yml
- docker-compose up

3) Ao abrir os projetos rodar
- comando do Maven: maven compile