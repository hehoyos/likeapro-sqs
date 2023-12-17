package co.com.likeapro.likeaprosqs.controllers;

import co.com.likeapro.likeaprosqs.models.CustomerSqs;
import co.com.likeapro.likeaprosqs.services.CustomerSqsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerSqsController {

    private CustomerSqsService customerSqsService;

    @PostMapping("/aws")
    public Mono<String> postCustomerQueue(@RequestBody CustomerSqs customerSqs) {
        return Mono.just(customerSqsService.publishStandardQueueMessage(10, customerSqs));
    }
}
