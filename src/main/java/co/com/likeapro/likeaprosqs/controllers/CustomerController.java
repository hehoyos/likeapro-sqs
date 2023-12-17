package co.com.likeapro.likeaprosqs.controllers;

import co.com.likeapro.likeaprosqs.models.Customer;
import co.com.likeapro.likeaprosqs.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/aws")
    public Mono<String> postCustomerQueue(@RequestBody Customer customer) {
        return Mono.just(customerService.publishStandardQueueMessage(10, customer));
    }
}
