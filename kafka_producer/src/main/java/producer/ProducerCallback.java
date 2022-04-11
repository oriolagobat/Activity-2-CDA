package producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerCallback {
    public static KafkaProducer<String, String> producer;

    public static void main(String[] args) {
        Properties props = getProducerProperties();
        producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "Hello World");
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                Logger logger = LoggerFactory.getLogger(ProducerCallback.class);
                if (e == null) {
                    logger.info("Successfully received the details as: \n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Partition:" + recordMetadata.partition() + "\n" +
                            "Offset" + recordMetadata.offset() + "\n" +
                            "Timestamp" + recordMetadata.timestamp());
                } else {
                    logger.error("Error while producing message", e);
                }
            }
        });

        closeProducer(producer);
    }

    private static Properties getProducerProperties() {
        // Create a Properties object to contain producer configuration
        Properties props = new Properties();
        String bootstrapServers = "localhost:9092";
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    private static void closeProducer(KafkaProducer<String, String> producer) {
        producer.flush();
        producer.close();
    }
}
