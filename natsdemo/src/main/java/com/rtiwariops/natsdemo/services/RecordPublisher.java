package com.rtiwariops.natsdemo.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import io.nats.client.Nats;

@Service
public class RecordPublisher {
	private static Logger LOG = LoggerFactory.getLogger(RecordPublisher.class);

	// Executes each 500 ms
	@Scheduled(fixedRate = 1000)
	public void checkRecords() throws IOException, InterruptedException {
		// Publish message
		Connection conn = Nats.connect("nats://nats:4222");
		conn.publish("rtiwariops", "testA".getBytes());
		LOG.info("testA");
	}
}
