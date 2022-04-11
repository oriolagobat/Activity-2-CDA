package producertutorial;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
    public static KafkaProducer<String, String> producer;

    public static void main(String[] args) {
        Properties props = getProducerProperties();
        producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "Hello World");
        producer.send(record);

        sendMessageFromString("Second message");

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

    private static void sendMessageFromString(String input) {
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", input);
        producer.send(record);
    }

    private static void closeProducer(KafkaProducer<String, String> producer) {
        producer.flush();
        producer.close();
    }
}
