package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static utils.UtilProducer.closeProducer;
import static utils.UtilProducer.getProducerProperties;

public class ProducerCallbackKeys {
    public static KafkaProducer<String, String> producer;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = getProducerProperties();
        producer = new KafkaProducer<>(props);

        Logger logger = LoggerFactory.getLogger(ProducerCallbackKeys.class);

        String topic = "my-topic";
        for (int i = 0; i < 10; i++) {
            String key = "id: " + i;
            String value = "value: " + i * 10;

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

            logger.info("Key: " + key);
            logger.info("Value: " + value);

            producer.send(record, (recordMetadata, e) -> {
                if (e == null) {
                    logger.info("Successfully published the details as: \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
                } else {
                    logger.error("Error while producing message", e);
                }
            }).get();  // This get method forces data to be transmitted synchronously
        }

        closeProducer(producer);
    }

}
