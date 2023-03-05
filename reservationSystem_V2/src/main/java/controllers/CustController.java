package controllers;

import DAO.CustomerDao;
import DAO.RestaurantDao;
import DAO.TableDao;
import business.CustomerInterface;
import business.MDB_Producer;
import entities.*;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import webBeanExtra.Reservation;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Named(value = "CustController")
@SessionScoped
//@ManagedBean(name = "CustController")
//@SessionScoped
public class CustController implements Serializable {

    public CustController(){}

    @EJB
    CustomerDao customerDao;
    @EJB
    RestaurantDao restaurantDao;
    @EJB
    TableDao tableDao;
    @EJB
    CustomerInterface customerInterface;

    @Inject
    MDB_Producer producer;

    @Inject
    CustomerTableEntity customerTableEntity;
    @Inject
    CustInfo custInfo;
    @Inject
    RestaurantTableEntity restaurantTableEntity;
    @Inject
    TableTableEntity tableTableEntity;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    private boolean loggedIn = false;

//    public String validateUsernamePassword(){
//        if(email.equals("") || password.equals("")) {
//            log = "Please input email and password to login. ";
//            return "failure";
//        }
//        UserEntity current_user = bean.user_validate(email, password);
//
//        if(current_user == null){
//            log = "User not exit or password is wrong.";
//            return "failure";
//        }
//        else{
//            loggedIn = true;
//            email = current_user.getEmail();
//            password = current_user.getPassword();
//            phone_nr = current_user.getPhoneNumber();
//            user = current_user;
//            id = current_user.getId();
//            log = "";
//            return "success";
//        }
//    }


    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    private String selectedCity;

    public String getSelectedGenre() {
        return selectedGenre;
    }

    public void setSelectedGenre(String selectedGenre) {
        this.selectedGenre = selectedGenre;
    }

    private String selectedGenre;

    public String customer(){
        return "user_login.xhtml";
    }
    public String restaurant(){
        return "restaurant_login.xhtml";
    }


    public String login(){

        String custUsername = customerTableEntity.getCustUsername();
        String custPassword = customerTableEntity.getCustPassword();

        CustomerTableEntity customerTableEntity1 = customerDao.CustomerLogin(custUsername,custPassword);

        if(customerTableEntity1 == null)
        {
            System.out.println("This user is not a registered user");
            loggedIn =false;
            return "user_login.xhtml";
        }
        else {
            customerInterface.login(customerTableEntity1);
            loggedIn = true;
            return "user_home.xhtml";
        }
    }


    public String signup(){

        CustomerTableEntity customer = new CustomerTableEntity();
        customer.setCustName(customerTableEntity.getCustName());
        customer.setCustUsername(customerTableEntity.getCustUsername());
        customer.setCustPassword(customerTableEntity.getCustPassword());
        CustInfo ci = new CustInfo();
        ci.setCustEmail(custInfo.getCustEmail());
        ci.setCustPhone(custInfo.getCustPhone());
        customer.setCustInfo(ci);

        producer.sendMessage(customer);
        //customerDao.CustomerNew(customer);

        return "user_login.xhtml";
    }

    public List<RestaurantTableEntity> returned_restaurants(){

        //List<RestaurantTableEntity>  result = restaurantDao.filter(City.All,Genre.All);
        //System.out.println(selectedGenre);
        List<RestaurantTableEntity>  result =null;
        if(selectedGenre == null && selectedCity == null){
            result = restaurantDao.filter(City.All,Genre.All);
        }
        else if(selectedGenre != null && selectedCity == null){
            switch (selectedGenre) {
                case "All":
                    result = restaurantDao.filter(City.All,Genre.All);
                    break;
                case "French":
                    result = restaurantDao.filter(City.All,Genre.French);
                    break;
                case "Italian":
                    result = restaurantDao.filter(City.All,Genre.Italian);
                    break;
                case "Chinese":
                    result = restaurantDao.filter(City.All,Genre.Chinese);
                    break;
                case "Japanese":
                    result = restaurantDao.filter(City.All,Genre.Japanese);
                    break;
                case "Indian":
                    result = restaurantDao.filter(City.All,Genre.Indian);
                    break;
            }
        } else if(selectedGenre == null && selectedCity != null){
            switch (selectedCity) {
                case "All":
                    result = restaurantDao.filter(City.All,Genre.All);
                    break;
                case "Leuven":
                    result = restaurantDao.filter(City.Leuven,Genre.All);
                    break;
                case "Brussels":
                    result = restaurantDao.filter(City.Brussels,Genre.All);
                    break;
                case "Bruges":
                    result = restaurantDao.filter(City.Bruges,Genre.All);
                    break;
                case "Antwerp":
                    result = restaurantDao.filter(City.Antwerp,Genre.All);
                    break;
                case "Ghent":
                    result = restaurantDao.filter(City.Ghent,Genre.All);
                    break;
                case "Liege":
                    result = restaurantDao.filter(City.Liege,Genre.All);
                    break;
            }
        }else{
            switch (selectedGenre) {
                case "All":
                    result = returned_restaurants_continued(Genre.All, selectedCity);
                    break;
                case "French":
                    result = returned_restaurants_continued(Genre.French, selectedCity);
                    break;
                case "Italian":
                    result = returned_restaurants_continued(Genre.Italian, selectedCity);
                    break;
                case "Chinese":
                    result = returned_restaurants_continued(Genre.Chinese, selectedCity);
                    break;
                case "Japanese":
                    result = returned_restaurants_continued(Genre.Japanese, selectedCity);
                    break;
                case "Indian":
                    result = returned_restaurants_continued(Genre.Indian, selectedCity);
                    break;
            }
        }

        return result;
        //return restaurantDao.filter(City.All,Genre.All);
    }

    public List<RestaurantTableEntity> returned_restaurants_continued(Genre genre, String selectedCity) {
        List<RestaurantTableEntity>  result = null;
        switch (selectedCity) {
            case "All":
                result = restaurantDao.filter(City.All,genre);
                break;
            case "Leuven":
                result = restaurantDao.filter(City.Leuven,genre);
                break;
            case "Brussels":
                result = restaurantDao.filter(City.Brussels,genre);
                break;
            case "Bruges":
                result = restaurantDao.filter(City.Bruges,genre);
                break;
            case "Antwerp":
                result = restaurantDao.filter(City.Antwerp,genre);
                break;
            case "Ghent":
                result = restaurantDao.filter(City.Ghent,genre);
                break;
            case "Liege":
                result = restaurantDao.filter(City.Liege,genre);
                break;
        }

        return result;
    }

    public String toUserConfigPage(){
        //System.out.println("ToUserConfigPage");
        customerTableEntity.setCustName(customerInterface.getUser().getCustName());
        customerTableEntity.setCustUsername(customerInterface.getUser().getCustUsername());
        customerTableEntity.setCustPassword(customerInterface.getUser().getCustPassword());
        custInfo.setCustEmail(customerInterface.getUser().getCustInfo().getCustEmail());
        custInfo.setCustPhone(customerInterface.getUser().getCustInfo().getCustPhone());
        return "user_page.xhtml";
    }

    public String logout(){
        customerInterface.logout();
        loggedIn = false;
        return "user_login.xhtml";
    }

    public List<Reservation> returned_reservations(){
        List<Object[]> list = customerDao.custReserTable(customerInterface.getUser().getCustId());
        List<Object[]> list2 = customerDao.custReserdetail(list);
        List<Reservation> reservations = new ArrayList<>();
        for(int i=0; i<list.size(); i++)
        {
            Reservation reservation = new Reservation();
            Object[] line = list.get(i);
            Object ti = line[1];
            Timeslot time = (Timeslot) ti;
            reservation.setTimeslot(time);

            line = list2.get(i);
            ti = line[0];
            String rest = (String) ti;
            reservation.setRestName(rest);

            reservations.add(reservation);
        }
        return reservations;
    }

    public String bookRestaurant(int restId, String restName){
        if(restId ==0 || restName.equals("")){
            return "booking_page.xhtml";
        }
        restaurantTableEntity.setRestId(restId);
        restaurantTableEntity.setRestName(restName);
        //System.out.println("restaurntID is "+restaurantTableEntity.getRestId());
        //System.out.println("restaurntName is "+restName);
        return "booking_page.xhtml";
    }


    public List<Reservation> returned_timeslots(){
//        List<Timeslot> findFreeTime(int tableid)
        List<Timeslot> list = tableDao.findFreeTime(tableTableEntity.getTableId());
        //System.out.println("tableid = "+tableTableEntity.getTableId());
        //System.out.println("tablenum = "+tableTableEntity.getTableNumber());
        List<Reservation> reservations = new ArrayList<>();
        String time = null;
        for(int i=0; i<list.size(); i++)
        {
            Reservation reservation = new Reservation();
            Timeslot timeslot = list.get(i);
            reservation.setTimeslot(timeslot);

            reservations.add(reservation);
        }
        return reservations;

    }

    public String bookTable(Timeslot timeslot){
        customerDao.bookTable(customerInterface.getUser().getCustUsername(),restaurantTableEntity.getRestId(),tableTableEntity.getTableNumber(), timeslot);
        //System.out.println(restaurantTableEntity.getRestId()+"     "+ timeslot+ "      "+tableTableEntity.getTableNumber());
        //System.out.print(customerInterface.getUser().getCustUsername());
        //customerDao.bookTable("songyao", 1,3, Timeslot.timeslot2);
        return "user_page.xhtml";
    }

    public List<Object[]> returned_Table(int tableSize){
        List<Object[]> list = restaurantDao.tablesOverviewBySize(restaurantTableEntity.getRestId(), tableSize);
        return list;
    }

    public void saveTableInfo(int tableid, int tablenum){
        tableTableEntity.setTableId(tableid);
        tableTableEntity.setTableNumber(tablenum);
        //System.out.println("tableid = "+tableid);
        //System.out.println("tablenum = "+tablenum);

    }

}

