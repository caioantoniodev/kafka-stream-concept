#outside container
echo '{"number": 3}' | kcat -P -b localhost:9092 -t PLAY_ODD_OR_EVEN_TOPIC

#inside container
echo '{"number": 3}' | ./kafka-console-producer --bootstrap-server kafka:9092 --topic PLAY_ODD_OR_EVEN_TOPIC