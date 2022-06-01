package br.com.springkafka.producer;


import br.com.springkafka.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarProducer {

    private final String topicName;
    private final KafkaTemplate<String, Car> kafkaTemplate;

    public CarProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, Car> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Car car) {
        kafkaTemplate.send(topicName, car).addCallback(
                success -> log.info("Mensagem Enviada com sucesso!"),
                failure -> log.error("Falha ao enviar mensagem!")
        );
    }
}
