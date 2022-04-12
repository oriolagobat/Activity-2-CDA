package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static utils.UtilProducer.closeProducer;
import static utils.UtilProducer.getProducerProperties;

public class ProducerCallback {
    public static KafkaProducer<String, String> producer;

    public static void main(String[] args) {
        Properties props = getProducerProperties();
        producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "Hello World");
        producer.send(record, (recordMetadata, e) -> {
            Logger logger = LoggerFactory.getLogger(ProducerCallback.class);
            if (e == null) {
                logger.info("Successfully published the details as: \n" +
                        "Topic: " + recordMetadata.topic() + "\n" +
                        "Partition: " + recordMetadata.partition() + "\n" +
                        "Offset: " + recordMetadata.offset() + "\n" +
                        "Timestamp: " + recordMetadata.timestamp());
            } else {
                logger.error("Error while producing message", e);
            }
        });

        closeProducer(producer);
    }
}
