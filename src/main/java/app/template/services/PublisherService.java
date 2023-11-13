package app.template.services;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PublisherService {
    public String queueName;
    public String exchange;
    public String message;
    public String routingKey;

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public void publish() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("username");
            factory.setPassword("password");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicPublish(exchange, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}