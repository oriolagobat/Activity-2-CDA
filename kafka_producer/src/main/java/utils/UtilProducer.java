package utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class UtilProducer {
    public static Properties getProducerProperties() {
        // Create a Properties object to contain producer configuration
        Properties props = new Properties();
        String bootstrapServers = "localhost:9092";
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    public static void closeProducer(KafkaProducer<String, String> producer) {
        producer.flush();
        producer.close();
    }
}
