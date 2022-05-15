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
public class NewCarEJB {

    //Attributes             
    @PersistenceContext(unitName = "ASS2PU")
    private EntityManager em;

    //Public methods           
    public List<NewCar> findAllNewCars() {
        Query query = em.createNamedQuery(NewCar.QueryAllCars);
        return query.getResultList();
    }

    public List<NewCar> findNewCars(String referenceNumber) {
        Query query = em.createNamedQuery(NewCar.SearchNewCarByReference);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }

    public NewCar createNewCar(NewCar newCar) {
        em.persist(newCar);
        return newCar;
    }

    public NewCar updateNewCar(NewCar newCar) {
        em.merge(newCar);
        return newCar;
    }

}
