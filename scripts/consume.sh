#outside container
kcat -C -b localhost:9092 -t kafka-stream-concept.odd-or-event.play.v1.dev -e | jq
kcat -C -b localhost:9092 -t kafka-stream-concept.odd.number.v1.dev -e | jq
kcat -C -b localhost:9092 -t kafka-stream-concept.even.number.v1.dev -e | jq

#inside container
./kafka-console-consumer --bootstrap-server kafka:29092 --topic kafka-stream-concept.odd-or-even.play.v1.dev --from-beginning
./kafka-console-consumer --bootstrap-server kafka:29092 --topic kafka-stream-concept.odd.number.v1.dev --from-beginning
./kafka-console-consumer --bootstrap-server kafka:29092 --topic kafka-stream-concept.even.number.v1.dev --from-beginning
