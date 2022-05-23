/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass2.controller;

import Ass2.ejb.CustomerEJB;
import Ass2.ejb.NewCarEJB;
import Ass2.ejb.OrderEJB;
import Ass2.ejb.UsedCarEJB;
import Ass2.entity.UsedCar;
import Ass2.entity.NewCar;
import Ass2.entity.Order;
import Ass2.entity.Car;
import Ass2.entity.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Faqiu Sun
 *
 * This class uses the customer and order beans to to display customers and
 * their corresponding orders
 */
@Named(value = "customerController")
@ManagedBean
public class CustomerController {

// all atributes
    @EJB
    CustomerEJB cEJB;
    @EJB
    OrderEJB oEJB;

    @ManagedProperty("#{projectController}")
    private ProjectController projectController; // +setter (no getter!)

    private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<Customer>();

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    /**
     *
     * @return It returns the list of customers by search keyword and the opens
     * the resulting xhtml that will display the result
     */
    public String searchCustomers() {
        customerList = cEJB.findCustomers(customer.getName());

        for (Customer c : customerList) {
            List<Order> orders = oEJB.findOrdersByCustomerId(c);
            c.setOrders(orders);
        }

        //    customerList.clear();
        return "foundCustomers.xhtml";
    }

    /**
     *
     * @return
     */
    public String doCreateCustomer() {
        //usedCarList.add(usedCar);
        Customer c = cEJB.createCustomer(customer);
        c.setId(null);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(projectController.getInfoComponent().getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created the customer:", c.getName()));
        // customer=new Customer();
        return "customerList.xhtml";
    }

    /**
     * DIsplay the details of the customers.
     */
    public void viewCustomerDetails() {
        customer = cEJB.findCustomerById(customer.getId());
        List<Order> orders = oEJB.findOrdersByCustomerId(customer);
        customer.setOrders(orders);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the list of customers using the beans defined
     *
     * @return
     */
    public List<Customer> getCustomerList() {
        // customerList = bEJB.findAllCustomers();
        return customerList;
    }

    /**
     *
     * @return Return all the customers using the bean
     */
    public List<Customer> getAllCustomerList() {
        customerList = cEJB.findAllCustomers();
        for (Customer c : customerList) {
            List<Order> orders = oEJB.findOrdersByCustomerId(c);
            c.setOrders(orders);
        }
        return customerList;
    }

    /**
     * Setter method for parameter of the class that contains the customer list
     *
     * @param customerList
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
