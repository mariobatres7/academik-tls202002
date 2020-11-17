package edu.academik.telus.customer.service;

import edu.academik.telus.account.receivable.remote.AccountReceivableRemote;
import javax.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Mario Batres
 */
public class AccountReceivableRemoteProducer {

    @Produces
    public AccountReceivableRemote invocationContext() {

        try {
            InitialContext context = new InitialContext();

            return (AccountReceivableRemote) context.lookup("java:global/account-receivable-service-1.0.0/AccountReceivable!edu.academik.telus.account.receivable.remote.AccountReceivableRemote");
        } catch (NamingException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
