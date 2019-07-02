package ru.abagiev.kafka.producer.bug;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Mono<String> send() {
        final String TOPIC1 = "Topic1";
        final String TOPIC2 = "Topic2";
        final String MESSAGE1 = "Message1";
        final String MESSAGE2 = "Message2";

        return sendToTopic(TOPIC1, MESSAGE1)
                .flatMap(x -> sendToTopic(TOPIC2, MESSAGE2));
    }

    private Mono<String> sendToTopic(String topic, String message) {
        return Mono.create(sink -> {
            log.info("Sending: " + message);

            kafkaTemplate.send(topic, message)
                    .addCallback(
                            ok -> {
                                log.info("Successfully sent: " + message);
                                sink.success(message);
                            },
                            err -> {
                                log.error("Failed to send: " + message, err);
                                sink.error(err);
                            });
        });
    }
}
