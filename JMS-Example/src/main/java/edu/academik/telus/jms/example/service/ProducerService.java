package edu.academik.telus.jms.example.service;

import edu.academik.telus.jms.example.rq.CustomMsg;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;  //jakarta  ->> javax.jms.Queue => jakartax.jms.Queue;

/**
 *
 * @author Mario Batres
 */
@RequestScoped
public class ProducerService {
    
    
    @Resource(mappedName = "java:/jms/queue/suertudo")
    private Queue messageQueue;
    
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connection;
    
    
    public void createMessage(CustomMsg customMsg){
        JMSContext context = connection.createContext();
        JMSProducer producer = context.createProducer();
        producer.send(messageQueue, customMsg);
    }
}
