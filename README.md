# KAFKA

### Comando para criação de um tópico via linha de comando no windows:

> bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic loja_novo_pedido

### Comando para listagem de mensagem de um topico
> bin/windows/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic meu-topico --from-beginning

### Sobre group_id


Se temos um unico grupo para todos os serviços de um determinado tópico, todos esses "escutadores" receberão todas as mensagens. Porém, Se tivermos dois serviços com o mesmo grupo, as mensagens vão ser distribuidas, parecido com a lógica de um loadbalance.



