package controllers;

import DAO.RestaurantDao;
import DAO.TableDao;
import webBeanExtra.time;
import business.RestaurantOwnerInterface;
import entities.CustomerTableEntity;
import entities.RestaurantTableEntity;
import entities.TableTableEntity;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.io.Serializable;
import java.util.List;

@Named("RestController")
@SessionScoped
public class RestController implements Serializable {
    @EJB
    RestaurantOwnerInterface restaurantOwnerInterface;
    @EJB
    RestaurantDao restDao;
    @EJB
    TableDao tableDao;
    @Inject
    RestaurantTableEntity restEn;
    @Inject
    TableTableEntity tableEn;
    @Inject
    CustomerTableEntity custEn;

    private int two;
    private int four;
    private int eight;

    public void setTwo(int two) {
        this.two = two;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public void setEight(int eight) {
        this.eight = eight;
    }

    public int getTwo() {
        return two;
    }
    public int getFour() {
        return four;
    }
    public int getEight() {
        return eight;
    }

    public String login() {
        RestaurantTableEntity thisrest = restDao.RestaurantLogin(restEn.getRestUsername(),restEn.getRestPassword());
        if(thisrest==null){return "failure";}

        restaurantOwnerInterface.login(thisrest);

        restEn.setRestOwner(thisrest.getRestOwner());
        restEn.setRestName(thisrest.getRestName());
        restEn.setRestId(thisrest.getRestId());

        return "login_old_rest.xhtml";
    }

    public List<time> getSlots()
    {
        List<time>slots = restDao.timeOverview(restEn.getRestId());
        return slots;
    }



    public String gosignup()
    {
        System.out.println("goto signup");
        return "restaurant_signup.xhtml";
    }

    public String signup() {
        System.out.println("signup");
        int n = restDao.RestaurantNew(restEn.getRestUsername());
        if(n==0){return "failure";}

        return "login_new_rest.xhtml";
    }

    public String addRestData()
    {
        System.out.println("add restaurant data");
        RestaurantTableEntity r = new RestaurantTableEntity();
        r.setRestUsername(restEn.getRestUsername());
        r.setRestPassword(restEn.getRestPassword());
        r.setRestCity(restEn.getRestCity());
        r.setRestDescription(restEn.getRestDescription());
        r.setRestGenre(restEn.getRestGenre());
        r.setRestLocation(restEn.getRestLocation());
        r.setRestName(restEn.getRestName());
        r.setRestOwner(restEn.getRestOwner());
        r.setRestOwner(restEn.getRestOwner());
        restDao.RestaurantSignup(r);
        tableDao.AddTable(restEn.getRestUsername(),two,four,eight);

        restaurantOwnerInterface.logout();

        return "restaurant_login.xhtml";

    }

    public String gologin()
    {
        System.out.println("goto login");
        return  "restaurant_login.xhtml";
    }

    public String logout()
    {
        restaurantOwnerInterface.logout();
        return  "restaurant_login.xhtml";
    }

//    public String chooseUser()
//    {
//        return "user_login.xhtml";
//    }
//
//    public String chooseRest()
//    {
//        return "restaurant_login.xhtml";
//    }
}
