package com.rtiwariops.natsdemo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;

@EnableScheduling
@SpringBootApplication
public class NatsdemoApplication {

	private static Logger LOG = LoggerFactory.getLogger(NatsdemoApplication.class);

	public static void main(String[] args) {
		LOG.info("Starting the application ... ");
		SpringApplication.run(NatsdemoApplication.class, args);
        LOG.info("Completed ..");
	}
	
	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Connection conn = Nats.connect("nats://nats:4222");
			Dispatcher subdispather = conn.createDispatcher(message -> {
				System.out.printf(String.format("Received message: [%s] from [%s] : successfully",
						new String(message.getData(), StandardCharsets.UTF_8), message.getSubject()));
			});
			subdispather.subscribe("rtiwariops");
		};

	}

}
