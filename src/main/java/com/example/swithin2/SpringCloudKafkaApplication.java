package com.example.swithin2;

import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.swithin2.Service.MessageModelService;
import com.example.swithin2.model.MessageModel;

@SpringBootApplication
@RestController
public class SpringCloudKafkaApplication {

	Logger logger = LoggerFactory.getLogger(SpringCloudKafkaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudKafkaApplication.class, args);
	}

	@Autowired
	private MessageModelService messageService;

	@Autowired
	private StreamBridge streamBridge;

	@RequestMapping(method = RequestMethod.POST, value = "/publish")
	public void delegateToSupplier(@RequestBody String body) {
		System.out.println("Sending " + body);
		streamBridge.send("messages", body);
	}

	@Bean
	public Function<String, String> processor() {
		return message -> message + " Processed";
	}

	@Bean
	public Consumer<String> consumer() {
		return (message) -> {

			logger.info("Processed text : {}", message);
			messageService.addMessage(new MessageModel(message));

		};
	}
}
