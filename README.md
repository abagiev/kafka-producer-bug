## How to reproduce

1) Run Kafka in docker container (e.g. `wurstmeister/kafka:2.12-2.2.1`)
2) Set `kafka.servers` to `<host:port>` of Kafka bootstrap servers in `application.properties`
3) Build and run Spring application
4) Invoke `/send` method of REST API (e.g. `curl -X POST http://localhost:8080/send`

Expected behavior:
- two messages are sent to two topics

Actual behavior:
- only one message is sent to one topic
- for another message exception is seen: `TimeoutException: Failed to update metadata after 60000 ms`
