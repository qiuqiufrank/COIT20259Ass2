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
@Named(value = "usedCarController")
@ManagedBean
public class UsedCarController {

    @EJB
    UsedCarEJB ucEJB;

    @ManagedProperty("#{projectController}")
    private ProjectController projectController; // +setter (no getter!)

    private UsedCar usedCar = new UsedCar();
    private List<UsedCar> usedCarList = new ArrayList<UsedCar>();

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }

    /**
     *
     * @return Stored used cars.
     */
    public UsedCar getUsedCar() {
        return usedCar;
    }

    /**
     *
     * @param usedCar is set here which declared in the parameters class
     */
    public void setUsedCar(UsedCar usedCar) {
        this.usedCar = usedCar;
    }

    /**
     *
     * @return Create the new user and redirect to the usedcar List page
     */
    public String doCreateUsedCar() {
        //usedCarList.add(usedCar);
        UsedCar nc = ucEJB.createUsedCar(usedCar);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(projectController.getInfoComponent().getClientId(),
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Successfully created the used car:", nc.getMake()
                        + " " + nc.getModel()));
        return "usedCarList.xhtml";
    }

    /**
     *
     * @return Redirects the page to found used cars
     */
    public String searchUsedCars() {
        usedCarList = ucEJB.findUsedCars(usedCar.getReferenceNumber());
        return "foundUsedCars.xhtml";
    }

    /**
     * This will display the resulting used cars.
     */
    public void viewUsedCarDetails() {
        usedCar = ucEJB.findUsedCars(usedCar.getReferenceNumber()).get(0);
    }

    /**
     *
     * @return The list of used cars
     */
    public List<UsedCar> getUsedCarList() {
        return usedCarList;
    }

    /**
     *
     * @return The usedCar list query called and stored in this list
     */
    public List<UsedCar> getAllUsedCarList() {
        usedCarList = ucEJB.findAllUsedCars();
        return usedCarList;
    }

    /**
     *
     * @param usedCarList This will set the class parameter
     */
    public void setUsedCarList(List<UsedCar> usedCarList) {
        this.usedCarList = usedCarList;
    }
}
