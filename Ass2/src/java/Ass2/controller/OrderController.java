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
 */
@Named(value = "orderController")
@ManagedBean
public class OrderController {

    @EJB
    CustomerEJB cEJB;
    @EJB
    OrderEJB oEJB;
    @EJB
    NewCarEJB ncEJB;
    @EJB
    UsedCarEJB ucEJB;

    @ManagedProperty("#{projectController}")
    private ProjectController projectController;
    @ManagedProperty("#{customerController}")
    private CustomerController customerController;

    private Order order = new Order();
    private List<Order> orderList = new ArrayList<Order>();
    private Long customerIdInOrder;
    private Long orderIdToDelete=null;
    private String carReferenceInOrder;



    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    public void setCustomerController(CustomerController customerController) {
        this.customerController = customerController;
    }

    public String searchOrders() {

        orderList = oEJB.findOrders(order.getId());
        //   System.out.println("102 "+order.getId()+"  "+orderList.size());
        //    customerList.clear();
        return "foundOrders.xhtml";
    }

    public String doCreateOrder() {

        int quantityInOrder=order.getQuantity();
        order.setId(null);
        //Get selected Car by reference number;
        Car selectedCar = projectController.getAllCarList().stream().filter(c -> carReferenceInOrder.equals(c.getReferenceNumber()))
                .collect(Collectors.toList()).get(0);
        order.setCar(selectedCar);

        //Get selected customer by ID
        Customer selectedCustomer = customerController.getAllCustomerList().stream().filter(c -> customerIdInOrder.equals(c.getId()))
                .collect(Collectors.toList()).get(0);
        order.setCustomer(selectedCustomer);
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (quantityInOrder <1) {
            ctx.addMessage(projectController.getErrorComponent().getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, cars quantity:", "Minimum quantity is 1"));
            return null;
        }
        if (quantityInOrder > selectedCar.getQuantity()) {
            ctx.addMessage(projectController.getErrorComponent().getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, not enough cars:", "Maximum quantity is " + selectedCar.getQuantity()));
            return null;
        }

        order.setQuantity(quantityInOrder);
        selectedCar.setQuantity(selectedCar.getQuantity()-quantityInOrder);
        projectController.updateCar(selectedCar);
        Order o = oEJB.createOrder(order);

        ctx.addMessage(projectController.getInfoComponent().getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created the order:", o.getCustomer().getName() + " " + o.getCar().getReferenceNumber()));

        return "orderList.xhtml";
    }

    //list of all used cars and all new cars
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getOrderIdToDelete() {
        return orderIdToDelete;
    }

    public void setOrderIdToDelete(Long orderIdToDelete) {
        this.orderIdToDelete = orderIdToDelete;
    }
        

    public List<Order> getOrderList() {
        // orderList = bEJB.findAllOrders();
        return orderList;
    }

    public List<Order> getAllOrderList() {
        orderList = oEJB.findAllOrders();
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Long getCustomerIdInOrder() {
        return customerIdInOrder;
    }

    public String carDetail(Car car){
        if(car instanceof NewCar){
            return "/faces/newCar/newCarDetails";
        }
        else{
            return "/faces/usedCar/usedCarDetails";
        }
    }

    public void deleteOrder() {
        List<Order> res=oEJB.findOrders(orderIdToDelete );
        if (res.size() > 0) {
            Order o=res.get(0);
            Car selectedCar=o.getCar();
            oEJB.deleteOrder(o);
            selectedCar.setQuantity(selectedCar.getQuantity()+o.getQuantity());
            projectController.updateCar(selectedCar);
        }
        orderIdToDelete = null;
    }

    public void setCustomerIdInOrder(Long customerIdInOrder) {
        this.customerIdInOrder = customerIdInOrder;
    }

    public String getCarReferenceInOrder() {
        return carReferenceInOrder;
    }

    public void setCarReferenceInOrder(String carReferenceInOrder) {
        this.carReferenceInOrder = carReferenceInOrder;
    }


}
