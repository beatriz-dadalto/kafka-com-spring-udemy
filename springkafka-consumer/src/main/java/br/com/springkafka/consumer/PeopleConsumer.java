package br.com.springkafka.consumer;
import br.com.springkafka.Car;
import br.com.springkafka.domain.Book;
import br.com.springkafka.domain.People;
import br.com.springkafka.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@KafkaListener(topics = "${topic.name}")
public class PeopleConsumer {

    private final PeopleRepository peopleRepository;


    @KafkaHandler
    public void consumer(ConsumerRecord<String, People> record, Acknowledgment ack) {
        var people = record.value();

        log.info("Mensagem consumida: " + people.toString());

        var peopleEntity = People.builder().build();

        peopleEntity.setId(people.getId().toString());
        peopleEntity.setName(people.getName().toString());
        peopleEntity.setCpf(people.getCpf().toString());

        peopleEntity.setBooks(people.getBooks().stream().map(book -> Book.builder()
                .people(peopleEntity).name(book.toString()).build()).collect(Collectors.toList()));

        peopleRepository.save(peopleEntity);

        ack.acknowledge();
    }

    @KafkaHandler
    public void consumerCar(Car car, Acknowledgment ack) {
        log.info("Message Received: " + car);
        ack.acknowledge();
    }

    /*
    caso apareca um objeto diferente do esperado aparece no log
     */
    @KafkaHandler(isDefault = true)
    public void unknown(Object object, Acknowledgment ack) {
        log.info("Message Received: " + object);
        ack.acknowledge();
    }
}