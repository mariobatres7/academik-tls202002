package edu.academik.telus.jmxexample;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

/**
 *
 * @author Mario Batres
 */
public class Main {

    public static void main(String[] args) {

        try {
            ObjectName gameObjectName = new ObjectName("edu.academik.telus.jmxexample:type=basic,name=game");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(new Game(), gameObjectName);

            while (true) {
            }

        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
