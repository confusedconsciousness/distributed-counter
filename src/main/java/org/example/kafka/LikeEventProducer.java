package org.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.example.models.LikeEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Slf4j
@Component
@EnableScheduling
public class LikeEventProducer {
    private final KafkaTemplate<String, LikeEvent> kafkaTemplate;
    private final String topic = "like-events"; // can be taken from the configuration
    private final Random random = new Random();

    public LikeEventProducer(KafkaTemplate<String, LikeEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void sendRandomLikeEvent() {
        String contentId = "post-" + (1 + random.nextInt(5));
        String userId = "user-" + (1 + random.nextInt(50));
        LikeEvent event = new LikeEvent(userId, contentId, Instant.now().toEpochMilli());
        // contentId is the key
        kafkaTemplate.send(topic, contentId, event).whenComplete((result, error) -> {
            if (error != null) log.error("Error sending like event", error);
            else log.info("Produced {}", event);
        });
    }
}
