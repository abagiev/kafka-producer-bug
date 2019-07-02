package ru.abagiev.kafka.producer.bug;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class Rest {

    private final SendService sendService;

    @PostMapping("/send")
    public Mono<String> send() {
        return sendService.send();
    }
}
