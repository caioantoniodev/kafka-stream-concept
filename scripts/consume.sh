./kafka-console-consumer.sh \
--topic EVEN_TOPIC \
--bootstrap-server localhost:9092 | jq

#inside container
./kafka-console-consumer --bootstrap-server kafka:29092 --topic PLAY_ODD_OR_EVEN_TOPIC --from-beginning
./kafka-console-consumer --bootstrap-server kafka:29092 --topic ODD_TOPIC --from-beginning
./kafka-console-consumer --bootstrap-server kafka:29092 --topic EVEN_TOPIC --from-beginning
