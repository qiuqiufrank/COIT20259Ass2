package Ass2.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 *
 * @author Faqiu Sun
 */
@Entity
@Table(name="orderline")
@NamedQuery(name = Order.QueryAllOrders, query = "select o from Order o")
@NamedQuery(name = Order.SearchOrderById, query = "select o from Order  o where o.id=:id")
@NamedQuery(name = Order.SearchOrdersByCustomer, query = "select o from Order  o where o.customer=:customer")
public class Order implements Serializable {


    public static final String SearchOrderById= "Customer.SearchOrderById";
    public static final String SearchOrdersByCustomer= "Customer.SearchOrdersByCustomer";
    public static final String QueryAllOrders = "Order.QueryAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Customer customer;
    private Car car;
    private Date createAt;
    //set default to 1
    private int quantity=1;
    @Transient
    private float total;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


}
