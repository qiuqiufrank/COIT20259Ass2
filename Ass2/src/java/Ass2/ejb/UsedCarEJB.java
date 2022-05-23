/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass2.ejb;

import Ass2.entity.NewCar;
import Ass2.entity.Customer;
import Ass2.entity.UsedCar;
import Ass2.entity.Order;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Faqiu Sun
 */
//@Stateless
@Startup
@Singleton
public class UsedCarEJB {

    //Attributes             
    @PersistenceContext(unitName = "ASS2PU")
    private EntityManager em;

    /**
     *
     * @param referenceNumber The name variable
     * @return Returns a list of used cars after querying according to the
     * referenceNumber
     */
    public List<UsedCar> findUsedCars(String referenceNumber) {
        Query query = em.createNamedQuery(UsedCar.SearchUsedCarByReference);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }

    /**
     *
     * @param name The name variable
     * @return Returns a list of customers after querying according to the name
     */
    public List<Customer> findCustomers(String name) {
        Query query = em.createNamedQuery(Customer.SearchCustomerByName);
        query.setParameter("name", name);
        return query.getResultList();
    }

    /**
     * @return Returns a list of used cars after querying according to the
     * referenceNumber
     */
    public List<UsedCar> findAllUsedCars() {
        Query query = em.createNamedQuery(UsedCar.QueryAllCars);
        return query.getResultList();
    }

    /**
     *
     * @param usedCar
     * @return Returns the created new used car
     */
    public UsedCar createUsedCar(UsedCar usedCar) {
        em.persist(usedCar);
        return usedCar;
    }

    /**
     *
     * @param usedCar
     * @return Returns the updated used car
     */
    public UsedCar updateUsedCar(UsedCar usedCar) {
        em.merge(usedCar);
        return usedCar;
    }
}
