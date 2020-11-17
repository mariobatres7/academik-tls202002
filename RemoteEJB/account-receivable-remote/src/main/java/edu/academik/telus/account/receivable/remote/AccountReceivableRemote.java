package edu.academik.telus.account.receivable.remote;

import java.math.BigDecimal;
import javax.ejb.Remote;

/**
 *
 * @author Mario Batres
 */
@Remote
public interface AccountReceivableRemote {

    BigDecimal getAmount(Long customerId);
}
