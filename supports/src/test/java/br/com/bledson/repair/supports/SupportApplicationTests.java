package br.com.bledson.repair.supports;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class SupportApplicationTests {
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.username", rabbitContainer::getAdminUsername);
        registry.add("spring.rabbitmq.password", rabbitContainer::getAdminPassword);
        registry.add("spring.data.default.mongodb.host", mongoDb::getHost);
        registry.add("spring.data.default.mongodb.database", mongoDb::getHost);
        registry.add("spring.data.default.mongodb.port", mongoDb::getHost);
    }

    @Container
    static MongoDBContainer mongoDb = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @Container
    static RabbitMQContainer rabbitContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:latest"));

    @Test
    void contextLoads() {
    }

}
