package co.com.likeapro.likeaprosqs.services;

import co.com.likeapro.likeaprosqs.models.RecordingSqs;
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
public class RecordingSqsService {

    private final AmazonSQS sqsClient;
    private static final String DATA_TYPE = "String";

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("likeapro-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, RecordingSqs recordingSqs) {

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("name",
                new MessageAttributeValue()
                        .withStringValue(recordingSqs.name())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("event",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(recordingSqs.event()).orElse(0L).toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("duration",
                new MessageAttributeValue()
                        .withStringValue(recordingSqs.duration().toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("status",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(recordingSqs.status()).orElse(Boolean.FALSE).toString())
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
