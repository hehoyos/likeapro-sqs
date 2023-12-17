package co.com.likeapro.likeaprosqs.services;

import co.com.likeapro.likeaprosqs.models.Customer;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {

    private final AmazonSQS sqsClient;
    private static final String DATA_TYPE = "String";

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("customer-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, Customer customer) {

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("id",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(customer.id()).orElse(0L).toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("name",
                new MessageAttributeValue()
                        .withStringValue(customer.name())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("email",
                new MessageAttributeValue()
                        .withStringValue(customer.email())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("password",
                new MessageAttributeValue()
                        .withStringValue(customer.password())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("phone",
                new MessageAttributeValue()
                        .withStringValue(customer.phone())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("role",
                new MessageAttributeValue()
                        .withStringValue(customer.role())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("status",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(customer.status()).orElse(Boolean.FALSE).toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("createdAt",
                new MessageAttributeValue()
                        .withStringValue(customer.createdAt().toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("updatedAt",
                new MessageAttributeValue()
                        .withStringValue(customer.updatedAt().toString())
                        .withDataType(DATA_TYPE)
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(customer.name())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(messageAttributes);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
