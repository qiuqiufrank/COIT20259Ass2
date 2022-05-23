package Ass2.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
@Entity
@NamedQuery(name = UsedCar.QueryAllCars, query = "select u from UsedCar u")
@NamedQuery(name = UsedCar.SearchUsedCarByReference, query = "select n from UsedCar n where n.referenceNumber=:referenceNumber")
/**
 * This stores the details of the used cars.
 */
public class UsedCar extends Car {

    public static final String SearchUsedCarByReference = "UsedCar.SearchUsedCarByReference";
    public static final String QueryAllCars = "UsedCar.QueryAll";
    private static final long serialVersionUID = 1L;

    private int odometer;
    private String regoNo;
    private String regoExpiry;
    private String serviceHistory;
    private String vin;
    private String carHistory;

    public UsedCar() {
        setQuantity(1);
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getRegoNo() {
        return regoNo;
    }

    public void setRegoNo(String regoNo) {
        this.regoNo = regoNo;
    }

    public String getRegoExpiry() {
        return regoExpiry;
    }

    public void setRegoExpiry(String regoExpiry) {
        this.regoExpiry = regoExpiry;
    }

    public String getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(String serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarHistory() {
        return carHistory;
    }

    public void setCarHistory(String carHistory) {
        this.carHistory = carHistory;
    }

}
