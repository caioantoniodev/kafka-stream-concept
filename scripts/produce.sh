#outside container
echo '{"number": 3}' | kcat -P -b localhost:9092 -t kafka-stream-concept.odd-or-even.play.v1.dev

#inside container
echo '{"number": 3}' | ./kafka-console-producer --bootstrap-server kafka:9092 --topic kafka-stream-concept.odd-or-even.play.v1.dev