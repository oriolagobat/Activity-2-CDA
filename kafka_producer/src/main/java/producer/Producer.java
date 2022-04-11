package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import utils.UtilProducer;

import java.util.Properties;

import static utils.UtilProducer.closeProducer;

public class Producer {
    public static KafkaProducer<String, String> producer;

    public static void main(String[] args) {
        Properties props = UtilProducer.getProducerProperties();
        producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "Hello World");
        producer.send(record);

        sendMessageFromString("Second message");

        closeProducer(producer);
    }

    private static void sendMessageFromString(String input) {
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", input);
        producer.send(record);
    }
}
