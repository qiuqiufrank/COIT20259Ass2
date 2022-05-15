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
public class OrderEJB {

    //Attributes             
    @PersistenceContext(unitName = "ASS2PU")
    private EntityManager em;

   

    public void deleteOrder(Order o) {
        em.remove(em.merge(o));
        em.flush();
    }

    public List<Order> findOrders(Long id) {
        Query query = em.createNamedQuery(Order.SearchOrderById);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Order> findOrdersByCustomerId(Customer customer) {
        Query query = em.createNamedQuery(Order.SearchOrdersByCustomer);
        query.setParameter("customer", customer);
        return query.getResultList();
    }

    public List<Order> findAllOrders() {
        Query query = em.createNamedQuery(Order.QueryAllOrders);
        return query.getResultList();
    }

    public Order createOrder(Order order) {
        Date date = new Date(System.currentTimeMillis());
        order.setCreateAt(date);
        em.persist(order);
        return order;
    }

}
