./kafka-console-consumer.sh \
--topic PARKING_LOT \
--bootstrap-server localhost:9092 | jq

#inside container
./kafka-console-consumer --bootstrap-server kafka:29092 --topic INBOUND_EVENT --from-beginning
./kafka-console-consumer --bootstrap-server kafka:29092 --topic PARKING_LOT --from-beginning
