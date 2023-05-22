# KAFKA

### Comando para criação de um tópico via linha de comando no windows:

> bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic loja_novo_pedido

### Comando para listagem de mensagem de um topico
> bin/windows/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic meu-topico --from-beginning

### Sobre group_id


Se temos um unico grupo para todos os serviços de um determinado tópico, todos esses "escutadores" receberão todas as mensagens. Porém, Se tivermos dois serviços com o mesmo grupo, as mensagens vão ser distribuidas, parecido com a lógica de um loadbalance.

### Comando para alterar partição de um tópico

OBS: Optamos por aumentar as partições para que, quando tivermos mais de um serviço no mesmo grupo, as mensagens sejam distribuidas.

O Spring ja faz a distribuição das partiçõs por consumer.

> bin/windows/kafka-topics.bat --bootstrap-server localhost:9092 --alter --topic loja_novo_pedido --partitions 3

### Comando para o describe do tópico

> bin/windows/kafka-topics.bat --bootstrap-server localhost:9092 --describe

### Sobre armazenamento de logs/registros

Todos os logs/registros são configurados por default em uma pasta temporaria.

Podemos mudar para um diretório onde serão armazenados de fato modificando os seguintes arquivos:

* server.properties
* zookeeper.properties

### Sobre processamento em lotes:

Usamos:
> spring.kafka.consumer.max-poll-records=1

Ao definir "spring.kafka.consumer.max-poll-records=1", você está configurando o consumidor Kafka para buscar apenas um registro por chamada de "poll". Isso significa que o consumidor irá receber um registro de cada vez e, em seguida, fará outra chamada de "poll" para buscar o próximo registro.

Essa configuração pode ser útil em cenários em que é necessário um processamento mais granular dos registros, onde cada registro requer uma lógica de processamento complexa e demorada. Ao buscar apenas um registro por vez, o consumidor pode processá-lo completamente antes de buscar o próximo, evitando sobrecarregar o processamento.

## Como criar um cluster de broker

* Criar novo properties para o servidor kafka.
* Definir o *broker.id*
* Adicionar uma propriedade chamada *default.replication.factor* que vai definir o numero de replicas
* Configurar a porta do novo broker *listeners=PLAINTEXT://:9093*
* Derrubar todos os serviços e afins.
* Limpar a pasta de logs.
* Subir tudo novamente.

OBS: A propertie que é retornada no comando de --describe *Isr*, mostra as replicas que estão atualizadas até o momento.

### Sobre Acks

Essa config faz com que o lider confirme que sincronizou com suas replicas que estão em sync.
> spring.kafka.producer.acks=all


#

## OFFSET

Podemos definir o offset para novos *serviços* ou *consumer-group* para consumir desde a primeira mensagem que ainda está armazenada em disco, ou consumir a partir da mais "recente".

* latest -> Consumir da mais recente
* earliest -> Consumir desde o inicio (Que ainda está salvo em disco)

#

## Indepotência

No Apache Kafka, a idempotência é aplicada ao produtor (producer) de mensagens. Um produtor idempotente garante que, ao enviar uma mensagem para um tópico específico, se a mesma mensagem for enviada várias vezes, apenas uma cópia dela será gravada no log de um tópico do Kafka. Isso ajuda a evitar a duplicação indesejada de mensagens.

Isso permite que os produtores reenviem mensagens com segurança em caso de falhas ou erros, sem causar efeitos colaterais indesejados. A idempotência é particularmente útil em casos em que a entrega de mensagens é crítica e duplicações não podem ser toleradas, como em sistemas financeiros ou de processamento de pedidos.
