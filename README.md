# KAFKA

### Comando para criação de um tópico via linha de comando no windows:

> bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic loja_novo_pedido

### Comando para listagem de mensagem de um topico
> bin/windows/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic meu-topico --from-beginning



