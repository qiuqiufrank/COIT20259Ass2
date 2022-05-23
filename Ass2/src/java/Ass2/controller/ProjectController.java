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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
@Named(value = "projectController")
@ManagedBean(eager = true)
public class ProjectController {

    @EJB
    NewCarEJB ncEJB;
    @EJB
    UsedCarEJB ucEJB;
    @EJB
    CustomerEJB cEJB;
    @EJB
    OrderEJB oEJB;

    private UIComponent infoComponent;
    private UIComponent errorComponent;

    //Initialize database for testing
    @PostConstruct
    public void init() {
//        UsedCar usedCar = new UsedCar();
//        usedCar.setReferenceNumber("uc2");
//        usedCar.setModel("m1");
//        ucEJB.createUsedCar(usedCar);
//
//        NewCar newCar = new NewCar();
//        newCar.setReferenceNumber("nc2");
//        newCar.setModel("m2");
//        newCar.setQuantity(20);
//        ncEJB.createNewCar(newCar);
//
//        Customer customer = new Customer();
//        customer.setName("c9");
//        cEJB.createCustomer(customer);
//
//        Customer customer2 = new Customer();
//        customer2.setName("c2");
//        cEJB.createCustomer(customer2);
//
//        Customer customer3 = new Customer();
//        customer3.setName("c3");
//        cEJB.createCustomer(customer3);
//
//        Order order = new Order();
//        order.setCustomer(customer);
//        order.setCar(newCar);
//        oEJB.createOrder(order);

    }

    // private String searchCarReference;
    // private String searchOrderId;
    // private List<Car> carList = new ArrayList<Car>();

    public UIComponent getInfoComponent() {
        return infoComponent;
    }
/**
 * 
 * @param infoComponent The infoComponent of the UI/View is set here
 */
    public void setInfoComponent(UIComponent infoComponent) {
        this.infoComponent = infoComponent;
    }
/**
 * 
 * @return The error component of the UI
 */
    public UIComponent getErrorComponent() {
        return errorComponent;
    }
/**
 * 
 * @param errorComponent 
 */
    public void setErrorComponent(UIComponent errorComponent) {
        this.errorComponent = errorComponent;
    }
/**
 * 
 * @return 
 */
    public List<Car> getAllCarList() {
        List<Car> carList = Stream.of(ucEJB.findAllUsedCars(), ncEJB.findAllNewCars())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return carList;
    }
/**
 * 
 * @param car 
 */
    public void updateCar(Car car) {
        if (car instanceof NewCar) {
            NewCar nc = (NewCar) car;
            ncEJB.updateNewCar(nc);
        } else {
            UsedCar nc = (UsedCar) car;
            ucEJB.updateUsedCar(nc);
        }

    }

}
