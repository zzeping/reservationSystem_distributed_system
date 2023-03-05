package DAO;
import entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.bind.annotation.XmlType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import utils.JpaUtils;
import webBeanExtra.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Stateless
@XmlType
public class RestaurantDao{

    @PersistenceContext(unitName = "a21da08")
    jakarta.persistence.EntityManager entityManager;

    //    酒店注册
//    public void RestaurantNew(RestaurantTableEntity restaurant){
//
//        //EntityManager entityManager = JpaUtils.getEntityManager();
//        //entityManager.getTransaction().begin();
//        String username = restaurant.getRestUsername();
//        Query query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE r.restUsername = :username") ;
//        query.setParameter("username", username);
//        List<RestaurantTableEntity> list = query.getResultList();
//        if(list.isEmpty())
//        {
//            entityManager.persist(restaurant);
//        }
//        else
//        {
//            System.out.println("duplicate username");
//        }
//        //entityManager.getTransaction().commit();
//        //entityManager.close();
//    }

    //添加酒店信息
    public void RestaurantSignup(RestaurantTableEntity restaurant){
        entityManager.persist(restaurant);
    }


    //    酒店注册 查重复username
    public int RestaurantNew(String restaurantusername){

        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE r.restUsername = :username") ;
        query.setParameter("username", restaurantusername);
        List<RestaurantTableEntity> list = query.getResultList();
        if(list.isEmpty())
        {
            // entityManager.persist(restaurant);
            return 1;
        }
        else
        {
            System.out.println("duplicate username");
            return 0;
        }
        //entityManager.getTransaction().commit();
        //entityManager.close();
    }


    //    酒店登录get信息
    public RestaurantTableEntity RestaurantLogin(String username, String password){

        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE (r.restUsername = :username AND r.restPassword = :password)") ;
        query.setParameter("username", username);
        query.setParameter("password",  password) ;
        List<RestaurantTableEntity> list = query.getResultList();

        if(list.isEmpty()) {
            System.out.println("no result found");
            list.add(null);
        }

        //entityManager.getTransaction().commit();
        //entityManager.close();
        return list.get(0);
    }


    //city filter
    public List<RestaurantTableEntity> cityFilt(City city)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE r.restCity = :city");
        query.setParameter("city", city);
        List<RestaurantTableEntity> list = query.getResultList();

        //entityManager.getTransaction().commit();
        //entityManager.close();
        return list;
    }

    public List<RestaurantTableEntity> filter(City city,Genre genre)
    {
        Query query;

        if(city ==City.All && genre ==Genre.All)
        {
            query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r ");
        }
        else if( city ==City.All )
        {
            query = entityManager.createQuery("SELECT g FROM RestaurantTableEntity g WHERE g.restGenre = :genre");
            query.setParameter("genre", genre);
        } else if (genre == Genre.All) {
            query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE r.restCity = :city");
            query.setParameter("city", city);
        }
        else {
            query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE r.restCity = :city AND r.restGenre = :genre");
            query.setParameter("city", city);
            query.setParameter("genre", genre);
        }

        List<RestaurantTableEntity> list = query.getResultList();
        return list;
    }


    //city filter
    public List<RestaurantTableEntity> genreFilt(Genre genre)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT g FROM RestaurantTableEntity g WHERE g.restGenre = :genre");
        query.setParameter("genre", genre);
        List<RestaurantTableEntity> list = query.getResultList();

        //entityManager.getTransaction().commit();
        //entityManager.close();
        return list;
    }

    public List<Object[]> tablesOverviewBySize(int restid, int tablesize){

        Query query1 = entityManager.createQuery("SELECT t FROM TableTableEntity t WHERE t.restaurant.restId = :restid AND t.tableSize = :tablesize ORDER BY t.tableNumber ASC");
        query1.setParameter("restid", restid);
        query1.setParameter("tablesize", tablesize);
        List<TableTableEntity> tables = query1.getResultList();

        List<Object[]> overview = new ArrayList<>();

        for(int j=0;j< tables.size();j++)
        {
            TableTableEntity table = tables.get(j);
            int tablenum = table.getTableNumber();
            int tableid = table.getTableId();

            Object[] line ={tableid,tablenum};
            overview.add(line);
            //System.out.println(Arrays.toString(line));
        }

        return overview;
    }


//    //查看自己餐厅所有table情况(返回 [table num, ts1, ts2, ts3, ts4] )
//    public List<Object[]> tablesOverview(int restid)
//    {
//        //EntityManager entityManager = JpaUtils.getEntityManager();
//        //entityManager.getTransaction().begin();
//
//        Query query1 = entityManager.createQuery("SELECT t FROM TableTableEntity t WHERE t.restaurant.restId = :restid ORDER BY t.tableNumber ASC");
//        query1.setParameter("restid", restid);
//        List<TableTableEntity> tables = query1.getResultList();
//
//        List<Object[]> overview = new ArrayList<>();
//
//        for(int j=0;j< tables.size();j++)
//        {
//            TableTableEntity table = tables.get(j);
//            int tablenum = table.getTableNumber();
//            Query query2 = entityManager.createQuery("SELECT r.timeslot FROM ReservationTableEntity r WHERE r.table = :table");
//            query2.setParameter("table", table);
//            List<Timeslot> list2 = query2.getResultList();
//            int time0 = 0;
//            int time1 = 0;
//            int time2 = 0;
//            int time3 = 0;
//            for(int i=0;i<list2.size();i++)
//            {
//                if(Timeslot.timeslot0 == list2.get(i)) {time0 = 1;}
//                if(Timeslot.timeslot1 == list2.get(i)) {time1 = 1;}
//                if(Timeslot.timeslot2 == list2.get(i)) {time2 = 1;}
//                if(Timeslot.timeslot3 == list2.get(i)) {time3 = 1;}
//            }
//            Object[] line ={tablenum,time0,time1,time2,time3};
//            overview.add(line);
//            //System.out.println(Arrays.toString(line));
//        }
//        //entityManager.getTransaction().commit();
//        //entityManager.close();
//
//        return overview;
//    }


    //查看自己餐厅所有table情况(返回 [table num, ts1, ts2, ts3, ts4] )
    public List<time> timeOverview(int restid)
    {
        Query query1 = entityManager.createQuery("SELECT t FROM TableTableEntity t WHERE t.restaurant.restId = :restid ORDER BY t.tableNumber ASC");
        query1.setParameter("restid", restid);
        List<TableTableEntity> tables = query1.getResultList();

        List<time> overview = new ArrayList<>();

        for(int j=0;j< tables.size();j++)
        {
            TableTableEntity table = tables.get(j);
            int tablenum = table.getTableNumber();
            Query query2 = entityManager.createQuery("SELECT r.timeslot FROM ReservationTableEntity r WHERE r.table = :table");
            query2.setParameter("table", table);
            List<Timeslot> list2 = query2.getResultList();
            String time0 = "free";
            String time1 = "free";
            String time2 = "free";
            String time3 = "free";
            for(int i=0;i<list2.size();i++)
            {
                if(Timeslot.timeslot0 == list2.get(i)) {time0 = "occupied";}
                if(Timeslot.timeslot1 == list2.get(i)) {time1 = "occupied";}
                if(Timeslot.timeslot2 == list2.get(i)) {time2 = "occupied";}
                if(Timeslot.timeslot3 == list2.get(i)) {time3 = "occupied";}
            }
            overview.add(new time(time0,time1,time2,time3,tablenum));
            //System.out.println(Arrays.toString(line));
        }
        //entityManager.getTransaction().commit();
        //entityManager.close();

        return overview;
    }

    //店家取消预订某一预定（需要tableid+timeslot）
    public void cancelBook(int tableid, Timeslot time)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT r FROM ReservationTableEntity r WHERE (r.table.tableId = :tableid AND r.timeslot = :time)");
        query.setParameter("tableid", tableid);
        query.setParameter("time", time);
        ReservationTableEntity reser = (ReservationTableEntity)query.getSingleResult();
        entityManager.remove(reser);

        //entityManager.getTransaction().commit();
        //entityManager.close();
    }


    //店家取消所有预约
    public void cancelAll(int restid)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query1 = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE r.restId = :restid");
        query1.setParameter("restid", restid);
        RestaurantTableEntity rest = (RestaurantTableEntity)query1.getSingleResult();

        List<TableTableEntity> tables =rest.getTables();
        for(int i=0; i<tables.size(); i++)
        {
            TableTableEntity ta =tables.get(i);
            Query query2 = entityManager.createQuery("SELECT r FROM ReservationTableEntity r WHERE r.table = :ta");
            query2.setParameter("ta", ta);
            List<ReservationTableEntity> list2 = query2.getResultList();
            for(int j=0;j< list2.size();j++) { entityManager.remove(list2.get(j)); }
        }

        //entityManager.getTransaction().commit();
        //entityManager.close();
    }


    //每天清除所有
    public void clearAll()
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM ReservationTableEntity");
        query.executeUpdate();
        //entityManager.getTransaction().commit();
        //entityManager.close();
    }




    public List<ReservationTableEntity> allReser()
    {
        Query query = entityManager.createQuery("SELECT r FROM ReservationTableEntity r");
        List<ReservationTableEntity> list = query.getResultList();
        return list;
    }

    public List<RestaurantTableEntity> Rests()
    {
        Query query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r");
        List<RestaurantTableEntity> list = query.getResultList();
        return list;
    }

    public boolean valiadateOwner(String username, String owner)
    {
        Query query = entityManager.createQuery("SELECT g.restOwner FROM RestaurantTableEntity g WHERE g.restUsername = :username");
        query.setParameter("username", username);
        String o = (String)query.getSingleResult();
        if (o.equals(owner)){return true;}
        return false;
    }

    public void changeOwner(String username, String owner)
    {
        Query query = entityManager.createQuery("SELECT g FROM RestaurantTableEntity g WHERE g.restUsername = :username");
        query.setParameter("username", username);
        RestaurantTableEntity o = (RestaurantTableEntity)query.getSingleResult();
        o.setRestOwner(owner);
        entityManager.persist(o);
    }

    public void deleteRest(int id)
    {
        Query query = entityManager.createQuery("SELECT g FROM RestaurantTableEntity g WHERE g.restId = :id");
        query.setParameter("id", id);
        RestaurantTableEntity r = (RestaurantTableEntity)query.getSingleResult();
        entityManager.remove(r);
    }

    public RestaurantTableEntity getRestaurantByRestId( int restid){

        Query query = entityManager.createQuery("SELECT g FROM RestaurantTableEntity g WHERE g.restId = :id");
        query.setParameter("id", restid);
        RestaurantTableEntity rest = (RestaurantTableEntity)query.getSingleResult();
        return rest;
    }

    public boolean valiadateResUsername(String username)
    {
        Query query = entityManager.createQuery("SELECT g.restUsername FROM RestaurantTableEntity g WHERE g.restUsername = :username");
        query.setParameter("username", username);
        String o = (String)query.getSingleResult();
        if (o.equals(username)){return true;}
        return false;
    }

    public boolean valiadateResName(String restname)
    {
        Query query = entityManager.createQuery("SELECT g.restName FROM RestaurantTableEntity g WHERE g.restName = :restname");
        query.setParameter("restname", restname);
        String o = (String)query.getSingleResult();
        if (o.equals(restname)){return true;}
        return false;
    }

}
