package edu.academik.telus.customer.service;

import edu.academik.telus.account.receivable.remote.AccountReceivableRemote;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mario Batres
 */
@Stateless
public class CustomerService {

    @Inject
    private AccountReceivableRemote accountReceivableRemote;

    public String getCustomer(Long customerId) {

        BigDecimal amount = this.accountReceivableRemote.getAmount(customerId);

        return "customerId = " + customerId + ", amount = " + amount;
    }

}
