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

//@Stateless
@Startup
@Singleton
public class CustomerEJB {

    //Attributes             
    @PersistenceContext(unitName = "ASS2PU")
    private EntityManager em;

   



    public List<Customer> findCustomers(String name) {
        Query query = em.createNamedQuery(Customer.SearchCustomerByName);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public Customer findCustomerById(Long id) {
        Query query = em.createNamedQuery(Customer.QueryCustomerByID);
        query.setParameter("id", id);
        List<Customer> cs = query.getResultList();
        if (cs.size() > 0) {
            return cs.get(0);
        }
        return null;
    }

  



    public List<Customer> findAllCustomers() {
        Query query = em.createNamedQuery(Customer.QueryAllCustomers);
        return query.getResultList();
    }

    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }



}
