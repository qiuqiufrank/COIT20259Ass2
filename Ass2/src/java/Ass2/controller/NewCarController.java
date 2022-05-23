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

import java.io.Console;
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
 */
@Named(value = "newCarController")
@ManagedBean
public class NewCarController {

    @EJB
    NewCarEJB ncEJB;

    @ManagedProperty("#{projectController}")
    private ProjectController projectController; // +setter (no getter!)

    private NewCar newCar = new NewCar();
    private List<NewCar> newCarList = new ArrayList<NewCar>();

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    /**
     *
     * @return This will return the list of new cars using the search keyword
     */
    public String searchNewCars() {
        newCarList = ncEJB.findNewCars(newCar.getReferenceNumber());
        return "foundNewCars.xhtml";
    }

    public void startup(@Observes @Initialized(ApplicationScoped.class) Object context) {

        //System.out.println("-----88--");
    }

//    private String searchCarReference;
//    private String searchOrderId;
    //   private List<Car> carList = new ArrayList<Car>();
    public NewCar getNewCar() {
        return newCar;
    }

    public void setNewCar(NewCar newCar) {
        this.newCar = newCar;
    }

    /**
     *
     * @return will create the new car and return the View element i.e. the
     * xhtml
     */
    public String doCreateNewCar() {
        NewCar nc = ncEJB.createNewCar(newCar);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(projectController.getInfoComponent().getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created the brand new car:", nc.getMake() + " " + nc.getModel()));
        // System.out.println("-----77");
        return "newCarList.xhtml";
    }

    /**
     *
     * @return Formatted display of new Car list
     */
    public List<NewCar> getAllNewCarList() {
        newCarList = ncEJB.findAllNewCars();
        for (NewCar n : newCarList) {
            System.out.println("-----97--" + n.getReferenceNumber());
        }
        // System.out.println("---94--"+newCarList.size());
        return newCarList;
    }

    /**
     * Initialises the new car parameter
     */
    public void viewNewCarDetails() {
        newCar = ncEJB.findNewCars(newCar.getReferenceNumber()).get(0);
    }

    /**
     *
     * @return The list of new cars
     */
    public List<NewCar> getNewCarList() {
        //  newCarList = bEJB.findAllNewCars();
        return newCarList;
    }

    /**
     * Setter method for parameter of the class that contains the newCarList
     *
     * @param newCarList
     */
    public void setNewCarList(List<NewCar> newCarList) {
        this.newCarList = newCarList;
    }

    public void searchForNewCar() {

    }

}
