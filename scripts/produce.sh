echo '{"message": "Hello World"}' | ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic INBOUND_EVENT

#inside container
echo '{"message": "Hello World"}' | ./kafka-console-producer --bootstrap-server kafka:9092 --topic INBOUND_EVENT