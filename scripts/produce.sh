echo '{"number": 3}' | ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic PLAY_ODD_OR_EVEN_TOPIC

#inside container
echo '{"number": 3}' | ./kafka-console-producer --bootstrap-server kafka:9092 --topic PLAY_ODD_OR_EVEN_TOPIC