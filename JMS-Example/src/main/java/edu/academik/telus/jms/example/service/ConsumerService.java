package edu.academik.telus.jms.example.service;

import edu.academik.telus.jms.example.rq.CustomMsg;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author Mario Batres
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/suertudo")})
public class ConsumerService implements MessageListener {

    /*
        @PersistenceContent
    private EntityManager entityManager;
    */
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            
            
            CustomMsg customMsg = (CustomMsg) objectMessage.getObject();
            
            System.out.println(customMsg);
            
            System.out.println(customMsg.getPrecio());
            
            System.out.println(customMsg.getTitulo());
            
            System.out.println(customMsg.getContent());
            
        } catch (JMSException ex) {
            Logger.getLogger(ConsumerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
