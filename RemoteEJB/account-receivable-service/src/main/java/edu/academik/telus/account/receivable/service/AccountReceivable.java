package edu.academik.telus.account.receivable.service;

import edu.academik.telus.account.receivable.remote.AccountReceivableRemote;
import java.math.BigDecimal;
import javax.ejb.Stateless;

/**
 *
 * @author Mario Batres
 */
@Stateless
public class AccountReceivable implements AccountReceivableRemote {

    @Override
    public BigDecimal getAmount(Long customerId) {

        System.out.println(this.getClass().getName() + ":  customerId = " + customerId);

        return BigDecimal.valueOf(customerId).multiply(BigDecimal.valueOf(100L));
    }

}
