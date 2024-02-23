package com.demo.ConfluentCloudKafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;

@SpringBootApplication
public class ConfluentCloudKafkaApplication  implements CommandLineRunner {

	private final Producer producer;
	private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	public ConfluentCloudKafkaApplication(Producer producer, KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry) {
		this.producer = producer;
		this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
	}


	public static void main(String[] args) {
		SpringApplication.run(ConfluentCloudKafkaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		for (String arg : args) {
			switch (arg) {
				case "--producer":
					this.producer.sendMessage("keySender", "test evento 1");
					break;
				case "--consumer":
					MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer");
					listenerContainer.start();
					break;
				default:
					break;
			}
		}
	}
}
