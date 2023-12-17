package co.com.likeapro.likeaprosqs.controllers;

import co.com.likeapro.likeaprosqs.models.EventSqs;
import co.com.likeapro.likeaprosqs.services.EventSqsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/event")
public class EventSqsController {

    private EventSqsService eventSqsService;

    @PostMapping("/aws")
    public Mono<String> postCustomerQueue(@RequestBody EventSqs eventSqs) {
        return Mono.just(eventSqsService.publishStandardQueueMessage(10, eventSqs));
    }
}
