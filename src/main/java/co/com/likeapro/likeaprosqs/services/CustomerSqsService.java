package co.com.likeapro.likeaprosqs.services;

import co.com.likeapro.likeaprosqs.models.CustomerSqs;
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
public class CustomerSqsService {

    private final AmazonSQS sqsClient;
    private static final String DATA_TYPE = "String";

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("likeapro-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, CustomerSqs customerSqs) {

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("id",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(customerSqs.id()).orElse(0L).toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("name",
                new MessageAttributeValue()
                        .withStringValue(customerSqs.name())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("email",
                new MessageAttributeValue()
                        .withStringValue(customerSqs.email())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("password",
                new MessageAttributeValue()
                        .withStringValue(customerSqs.password())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("phone",
                new MessageAttributeValue()
                        .withStringValue(customerSqs.phone())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("role",
                new MessageAttributeValue()
                        .withStringValue(customerSqs.role())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("status",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(customerSqs.status()).orElse(Boolean.FALSE).toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("createdAt",
                new MessageAttributeValue()
                        .withStringValue(customerSqs.createdAt().toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("updatedAt",
                new MessageAttributeValue()
                        .withStringValue(customerSqs.updatedAt().toString())
                        .withDataType(DATA_TYPE)
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(customerSqs.name())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(messageAttributes);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
