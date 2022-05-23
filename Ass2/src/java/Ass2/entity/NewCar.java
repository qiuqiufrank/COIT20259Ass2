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
 *
 * @author Hirvi
 */
@Entity
@NamedQuery(name = NewCar.QueryAllCars, query = "select n from NewCar n")
@NamedQuery(name = NewCar.SearchNewCarByReference, query = "select n from NewCar n where n.referenceNumber=:referenceNumber")
/**
 * This stores the details of new car.
 */
public class NewCar extends Car {

    public static final String QueryAllCars = "NewCar.QueryAll";
    public static final String SearchNewCarByReference = "NewCar.SearchNewCarByReference";
    private static final long serialVersionUID = 1L;

    private int warranty;
    private int extendingWarranty;
    private String roadsideAssistancePackages;

    public NewCar() {
    }

    public int getExtendingWarranty() {
        return extendingWarranty;
    }

    public void setExtendingWarranty(int extendingWarranty) {
        this.extendingWarranty = extendingWarranty;
    }

    public String getRoadsideAssistancePackages() {
        return roadsideAssistancePackages;
    }

    public void setRoadsideAssistancePackages(String roadsideAssistancePackages) {
        this.roadsideAssistancePackages = roadsideAssistancePackages;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

}
