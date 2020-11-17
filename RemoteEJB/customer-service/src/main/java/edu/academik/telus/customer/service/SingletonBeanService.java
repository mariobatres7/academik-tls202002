package edu.academik.telus.customer.service;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

/**
 *
 * @author Mario Batres
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SingletonBeanService {

    public synchronized void process() {

    }

}
