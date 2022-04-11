package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static utils.UtilConsumer.getConsumerProperties;

public class Consumer {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Consumer.class);
        Properties props = getConsumerProperties();

        String topic = "my-topic";
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(List.of(topic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("Received message: (" + record.key() + ", " + record.value() + ")");
                logger.info("Message on offset " + record.offset() + ", on partition " + record.partition());
            }
        }
    }

}
