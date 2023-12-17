package co.com.likeapro.likeaprosqs.controllers;

import co.com.likeapro.likeaprosqs.models.RecordingSqs;
import co.com.likeapro.likeaprosqs.services.RecordingSqsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/recording")
public class RecordingSqsController {

    private RecordingSqsService recordingSqsService;

    @PostMapping("/aws")
    public Mono<String> postCustomerQueue(@RequestBody RecordingSqs recordingSqs) {
        return Mono.just(recordingSqsService.publishStandardQueueMessage(10, recordingSqs));
    }
}
