#outside container
kcat -C -b localhost:9092 -t PLAY_ODD_OR_EVEN_TOPIC -e | jq
kcat -C -b localhost:9092 -t ODD_TOPIC -e | jq
kcat -C -b localhost:9092 -t EVEN_TOPIC -e | jq

#inside container
./kafka-console-consumer --bootstrap-server kafka:29092 --topic PLAY_ODD_OR_EVEN_TOPIC --from-beginning
./kafka-console-consumer --bootstrap-server kafka:29092 --topic ODD_TOPIC --from-beginning
./kafka-console-consumer --bootstrap-server kafka:29092 --topic EVEN_TOPIC --from-beginning
