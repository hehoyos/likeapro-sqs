package co.com.likeapro.likeaprosqs.services;

import co.com.likeapro.likeaprosqs.models.EventSqs;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EventSqsService {

    private final AmazonSQS sqsClient;
    private static final String DATA_TYPE = "String";

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("likeapro-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, EventSqs eventSqs) {

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("name",
                new MessageAttributeValue()
                        .withStringValue(eventSqs.name())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("description",
                new MessageAttributeValue()
                        .withStringValue(eventSqs.description())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("date",
                new MessageAttributeValue()
                        .withStringValue(eventSqs.date().toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("status",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(eventSqs.status()).orElse(Boolean.FALSE).toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("customers",
                new MessageAttributeValue()
                        .withStringValue(eventSqs.customers())
                        .withDataType(DATA_TYPE)
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(UUID.randomUUID().toString())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(messageAttributes);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
