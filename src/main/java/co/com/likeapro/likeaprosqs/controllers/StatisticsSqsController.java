package co.com.likeapro.likeaprosqs.controllers;

import co.com.likeapro.likeaprosqs.models.StatisticsSqs;
import co.com.likeapro.likeaprosqs.services.StatisticsSqsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsSqsController {

    private StatisticsSqsService statisticsSqsService;

    @PostMapping("/aws")
    public Mono<String> postCustomerQueue(@RequestBody StatisticsSqs statisticsSqs) {
        return Mono.just(statisticsSqsService.publishStandardQueueMessage(10, statisticsSqs));
    }
}
