package Ass2.entity;

import Ass2.entity.Car;
import Ass2.entity.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-25T23:24:28")
@StaticMetamodel(Order.class)
public class Order_ { 

    public static volatile SingularAttribute<Order, Float> unitPrice;
    public static volatile SingularAttribute<Order, Integer> quantity;
    public static volatile SingularAttribute<Order, Car> car;
    public static volatile SingularAttribute<Order, Long> id;
    public static volatile SingularAttribute<Order, Date> createAt;
    public static volatile SingularAttribute<Order, Customer> customer;

}