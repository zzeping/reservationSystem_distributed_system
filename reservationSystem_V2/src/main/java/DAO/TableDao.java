package DAO;
import entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import utils.JpaUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TableDao {

    @PersistenceContext(unitName = "a21da08")
    jakarta.persistence.EntityManager entityManager;

    //酒店录入table信息
    public void AddTable(String restUsername,int two,int four,int eight){

        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT r FROM RestaurantTableEntity r WHERE r.restUsername = :username");
        query.setParameter("username", restUsername);
        RestaurantTableEntity thisRest = (RestaurantTableEntity)query.getSingleResult();
        for(int i = 0; i<two; i++)
        {
            TableTableEntity table = new TableTableEntity();
            table.setTableNumber(i+1);
            table.setTableSize(2);
            table.setRestaurant(thisRest);
            entityManager.persist(table);
        }
        for(int i = 0; i<four; i++)
        {
            TableTableEntity table = new TableTableEntity();
            table.setTableNumber(i+1+two);
            table.setTableSize(4);
            table.setRestaurant(thisRest);
            entityManager.persist(table);
        }
        for(int i = 0; i<eight; i++)
        {
            TableTableEntity table = new TableTableEntity();
            table.setTableNumber(i+1+two+four);
            table.setTableSize(8);
            table.setRestaurant(thisRest);
            entityManager.persist(table);
        }
        //entityManager.getTransaction().commit();
        //entityManager.close();
    }


    //用 酒店id 和 table num 获取table
    public TableTableEntity findTable(int restId, int tableNum){
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT t FROM TableTableEntity t WHERE (t.tableNumber = :tableNumber AND t.restaurant.restId = :restId)");
        query.setParameter("tableNumber", tableNum);
        query.setParameter("restId", restId);
        TableTableEntity table = (TableTableEntity) query.getSingleResult();
        //entityManager.getTransaction().commit();
        //entityManager.close();
        return table;
    }

    //根据需要的桌子大小查找所有桌子，返回所有符合要求的table
    public List<TableTableEntity> findSuitTable(int restid, int tsize)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();
        Query query1 = entityManager.createQuery("SELECT t FROM TableTableEntity t WHERE (t.tableSize = :tsize AND t.restaurant.restId = :restid) ORDER BY t.tableNumber ASC");
        query1.setParameter("tsize", tsize);
        query1.setParameter("restid", restid);
        List<TableTableEntity> list2 = query1.getResultList();
        //entityManager.getTransaction().commit();
        //entityManager.close();
        return list2;
    }


    //根据tableid返回该桌所有可用timeslot
    public List<Timeslot> findFreeTime(int tableid)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT r.timeslot FROM ReservationTableEntity r WHERE r.table.tableId = :tableid");
        query.setParameter("tableid", tableid);
        List<Timeslot> time = new ArrayList<>();
        time.add(Timeslot.timeslot0);
        time.add(Timeslot.timeslot1);
        time.add(Timeslot.timeslot2);
        time.add(Timeslot.timeslot3);
        List objList = query.getResultList();
        for(Object obj: objList){
            time.remove(obj);
        }

        //entityManager.getTransaction().commit();
        //entityManager.close();
        return time;
    }

    //用 酒店id 和 table num, timeslot 获取true, false
    public boolean checkAvailable(int restId, int tableNum, Timeslot t){
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT t FROM TableTableEntity t WHERE (t.tableNumber = :tableNumber AND t.restaurant.restId = :restId)");
        query.setParameter("tableNumber", tableNum);
        query.setParameter("restId", restId);
        TableTableEntity table = (TableTableEntity) query.getSingleResult();
        Query query2 = entityManager.createQuery("SELECT r FROM ReservationTableEntity r WHERE (r.table = :table AND r.timeslot = :t)");
        query2.setParameter("table", table);
        query2.setParameter("t", t);
        List<ReservationTableEntity> list = query2.getResultList();
        boolean available = false;
        if(list.isEmpty()) {available = true;}

        //entityManager.getTransaction().commit();
        //entityManager.close();
        return available;
    }


}
