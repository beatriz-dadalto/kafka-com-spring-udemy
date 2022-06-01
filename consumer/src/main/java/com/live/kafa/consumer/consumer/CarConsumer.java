package com.live.kafa.consumer.consumer;

import com.live.kafa.consumer.DTO.CarDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CarConsumer {

    private static final Logger log = LoggerFactory.getLogger(CarConsumer.class);

    @Value(value = "${topic.name}")
    private String topic;

    @Value(value = "${spring.kafka.group-id}")
    private String groupId;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "carKafkaListenerContainerFactory")
    public void listenTopicCar(ConsumerRecord<String, CarDTO> record) {
        log.info("Received Message: " + record.partition());
        log.info("Received message: " + record.value());
    }
}
