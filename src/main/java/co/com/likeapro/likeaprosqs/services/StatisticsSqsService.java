package co.com.likeapro.likeaprosqs.services;

import co.com.likeapro.likeaprosqs.models.StatisticsSqs;
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
public class StatisticsSqsService {

    private final AmazonSQS sqsClient;
    private static final String DATA_TYPE = "String";

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("likeapro-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, StatisticsSqs statisticsSqs) {

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("timestamp",
                new MessageAttributeValue()
                        .withStringValue(statisticsSqs.timestamp().toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("recording",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(statisticsSqs.recording()).orElse(0L).toString())
                        .withDataType(DATA_TYPE)
        );
        messageAttributes.put("data",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(statisticsSqs.data()).orElse(0L).toString())
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
