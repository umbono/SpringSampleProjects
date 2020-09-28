package com.rtiwariops.natsdemo;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;

@SpringBootApplication
public class NatsdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NatsdemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Connection conn = Nats.connect("nats://localhost:4222");			
			Dispatcher subdispather = conn.createDispatcher(message -> {
				System.out.printf(String.format("Received message: [%s] from [%s] : successfully",
						new String(message.getData(), StandardCharsets.UTF_8), message.getSubject()));
			});
			subdispather.subscribe("rtiwariops");
		};

	}

}
