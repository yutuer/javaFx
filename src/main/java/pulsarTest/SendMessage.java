package pulsarTest;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/7/1 17:34
 * @Version 1.0
 */
public class SendMessage
{
    private static final String MESSAGE_TEMPLATE = "pulsar-message-%s";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void send()
    {
        Executors.newFixedThreadPool(1).execute(() ->
        {
            int sendTimes = 0;
            try
            {
                PulsarClient client = PulsarClientBuilder.getInstance();
                Producer<String> producer = client.newProducer(Schema.STRING)
                        .topic(PulsarClientBuilder.Topic.DEMO.getName())
                        .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                        .sendTimeout(10, TimeUnit.SECONDS)
                        .blockIfQueueFull(true).create();

                while (true)
                {
                    String message = String.format(MESSAGE_TEMPLATE, sdf.format(new Date()));
//                    System.out.println("send message: " + message);
                    producer.send(message);
//                    Thread.sleep(1000);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}
