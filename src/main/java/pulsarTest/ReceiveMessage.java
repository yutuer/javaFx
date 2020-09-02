package pulsarTest;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

import java.util.concurrent.Executors;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/7/1 17:34
 * @Version 1.0
 */
public class ReceiveMessage
{
    public static void receive()
    {
        Executors.newFixedThreadPool(1).execute(() ->
        {
            try
            {
                PulsarClient client = PulsarClientBuilder.getInstance();
                Consumer consumer = client.newConsumer(Schema.STRING)
                        .topic(PulsarClientBuilder.Topic.DEMO.getName())
                        .subscriptionName(PulsarClientBuilder.Subscription.DEMO.getName()).subscribe();

                while (true)
                {
                    Message message = consumer.receive();
                    System.out.println("receive message: " + message.getValue());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}
