package org.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.models.LikeEvent;
import org.example.services.CounterService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LikeEventConsumer {
    private final CounterService counterService;

    public LikeEventConsumer(CounterService counterService) {
        this.counterService = counterService;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<String, LikeEvent> record, @Payload LikeEvent event) {
        try {
            counterService.increment(event.getContentId());
            log.info("Consumed {} (partition={}, offset={})", event, record.partition(), record.offset());
        } catch (Exception e) {
            log.error("Error while processing record {}", record, e);
        }
    }
}
