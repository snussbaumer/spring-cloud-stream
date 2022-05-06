package sample.producer1;

import java.util.Random;
import java.util.UUID;

import com.example.Sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(proxyBeanMethods = false)
@RestController
public class ConfluentAvroProducer1Application {

	private Random random = new Random();

	@Autowired
	private StreamBridge streamBridge;

	public static void main(String[] args) {
		SpringApplication.run(ConfluentAvroProducer1Application.class, args);
	}

	@PostMapping("/randomMessage")
	public String sendRandomMessage() {
		streamBridge.send("supplier-out-0", randomSensor());
		return "ok, have fun with v1 payload!";
	}

	private Sensor randomSensor() {
		Sensor sensor = new Sensor();
		sensor.setId(UUID.randomUUID() + "-v1");
		sensor.setAcceleration(random.nextFloat() * 10);
		sensor.setVelocity(random.nextFloat() * 100);
		sensor.setTemperature(random.nextFloat() * 50);
		return sensor;
	}
}



