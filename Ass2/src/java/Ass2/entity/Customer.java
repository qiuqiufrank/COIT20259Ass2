package Ass2.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Hirvi
 */
@Entity
@NamedQuery(name = Customer.QueryAllCustomers, query = "select c from Customer c")
@NamedQuery(name = Customer.QueryCustomerByID, query = "select c from Customer  c where c.id=:id")
@NamedQuery(name = Customer.SearchCustomerByName, query = "select c from Customer  c where c.name=:name")
/**
 * This stores the customer details
 */
public class Customer implements Serializable {

    public static final String SearchCustomerByName = "Customer.SearchCustomerByName";
    public static final String QueryAllCustomers = "Customer.QueryAll";
    public static final String QueryCustomerByID = "Customer. QueryCustomerByID";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    // @Transient
    // private int orderNum;

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // public int getOrderNum() {
    //     return orders.size();
    // }
    // public void setOrderNum(int orderNum) {
    //     this.orderNum = orderNum;
    // }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Ass2.Customer[ id=" + id + " ]";
//    }
}
