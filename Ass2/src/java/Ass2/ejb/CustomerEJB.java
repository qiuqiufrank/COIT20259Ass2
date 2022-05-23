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
 * This is the customer bean provides the CRUD for all the customers
 *
 * @author Faqiu Sun
 */
//@Stateless
@Startup
@Singleton
public class CustomerEJB {

    //Attributes             
    @PersistenceContext(unitName = "ASS2PU")
    private EntityManager em;

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
     *
     * @param id
     * @return Returns a customer after querying according to the primary key id
     */
    public Customer findCustomerById(Long id) {
        Query query = em.createNamedQuery(Customer.QueryCustomerByID);
        query.setParameter("id", id);
        List<Customer> cs = query.getResultList();
        if (cs.size() > 0) {
            return cs.get(0);
        }
        return null;
    }

    /**
     *
     * @return List of all the customers present in db
     */
    public List<Customer> findAllCustomers() {
        Query query = em.createNamedQuery(Customer.QueryAllCustomers);
        return query.getResultList();
    }

    /**
     *
     * @param customer
     * @return This returns the newly created customer
     */
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

}
