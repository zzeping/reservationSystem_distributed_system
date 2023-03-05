package DAO;
import business.Interceptor_Binding_Booking;
import entities.*;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.FluentQuery;
import utils.JpaUtils;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Stateless
public class CustomerDao{

    @PersistenceContext(unitName = "a21da08")
    EntityManager entityManager;

    @EJB
    TableDao tableDao;

    //用户注册
    public void CustomerNew(CustomerTableEntity customer){


        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();
        String email = customer.getCustInfo().getCustEmail();
        String username = customer.getCustUsername();
        Query query1 = entityManager.createQuery("SELECT c FROM CustomerTableEntity c WHERE c.custUsername = :username ") ;
        query1.setParameter("username", username);
        List<CustomerTableEntity> list1 = query1.getResultList();
        if(list1.isEmpty())
        {
            Query query2 = entityManager.createQuery("SELECT c.custInfo FROM CustomerTableEntity c WHERE c.custInfo.custEmail = :email ") ;
            query2.setParameter("email", email);
            List<CustInfo> list2 = query2.getResultList();
            if(list2.isEmpty())
            {
                entityManager.persist(customer);
            }
            else
            {
                System.out.println("duplicte email");
            }
        }
        else
        {
            System.out.println("duplicte username");
        }

        //entityManager.getTransaction().commit();
        //entityManager.close();

    }


    //用户登录
    public CustomerTableEntity CustomerLogin(String username, String password){


        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        //输出所有用array

        Query query = entityManager.createQuery("SELECT c FROM CustomerTableEntity c WHERE (c.custUsername = :username AND c.custPassword = :password)") ;
        query.setParameter("username", username);
        query.setParameter("password",  password) ;
        List<CustomerTableEntity> list = query.getResultList();
        if(list.isEmpty()) {
            System.out.println("no result found");
            list.add(null);
        }


        //entityManager.getTransaction().commit();
        //entityManager.close();

        return list.get(0);
    }


    //查找用户所有信息
    public CustomerTableEntity findUser(String username)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT c FROM CustomerTableEntity c WHERE c.custUsername = :username");
        query.setParameter("username", username);
        CustomerTableEntity c = (CustomerTableEntity)query.getSingleResult();
        //entityManager.getTransaction().commit();
        //entityManager.close();
        return c;
    }


    //查找用户名字
    public String findUserbyId(int id)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT c.custName FROM CustomerTableEntity c WHERE c.custId = :userid");
        query.setParameter("userid", id);
        String c = (String)query.getSingleResult();
        //entityManager.getTransaction().commit();
        //entityManager.close();
        return c;
    }

    //顾客预定桌子，需要顾客用户名 和 restId，table number，timeslot
    @Interceptor_Binding_Booking
    public void bookTable(String username, int restid, int tablenum, Timeslot time)
    {

        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("a21da08");
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        //entityManager.getTransaction().begin();

        //CustomerDao CustomerDao=new CustomerDao();
        CustomerTableEntity c = this.findUser(username);

        //TableDao TableDao=new TableDao();
        TableTableEntity t = tableDao.findTable(restid,tablenum);

        ReservationTableEntity reser = new ReservationTableEntity();
        reser.setTable(t);
        reser.setCustomer(c);
        reser.setTimeslot(time);
        entityManager.persist(reser);

        //entityManager.getTransaction().commit();
        //entityManager.close();
        //entityManagerFactory.close();
    }



    //显示该顾客所有预定（根据cust_id，返回桌子, timeslot）
    public List<Object[]> custReserTable(int custid)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT r.table, r.timeslot FROM ReservationTableEntity r WHERE r.customer.custId = :custid");
        query.setParameter("custid", custid);
        List<Object[]> list2 = query.getResultList();

        //entityManager.getTransaction().commit();
        //entityManager.close();
        return list2;
    }



    //显示该顾客所有预定（接上，继续根据返回的桌子，得到餐厅名，地址，桌号）
    public List<Object[]> custReserdetail(List<Object[]> list)
    {
        //EntityManager entityManager = JpaUtils.getEntityManager();
        //entityManager.getTransaction().begin();

        List<Object[]> details = new ArrayList<>();
        for(int i=0; i<list.size(); i++)
        {
            Object[] line = list.get(i);
            Object ta =line[0];
            TableTableEntity table = (TableTableEntity) ta;
            Object ti = line[1];
            Timeslot time = (Timeslot) ti;
            RestaurantTableEntity restaurant = table.getRestaurant();
            int number = table.getTableNumber();
            String location = restaurant.getRestLocation();
            String restname = restaurant.getRestName();
            Object[] detail = {restname, location, number, time};
            details.add(detail);
        }
        //entityManager.getTransaction().commit();
        //entityManager.close();

        return details;
    }




//    public void CustomerLogin(String username, String password){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("a21da08");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        CustomerTableEntity cust =entityManager.find(CustomerTableEntity.class, 1);
//        System.out.println(cust);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
//    }


//**************
    /*
    public void CustomerUpdate(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("a21da08");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CustomerTableEntity cust =entityManager.find(CustomerTableEntity.class, 1);
        cust.setCustUsername("newUsername");
//        CustInfo ci = new CustInfo();
//        ci.setCustPhone("1029384756");
//        cust.setCustInfo(ci);
//        entityManager.merge(cust);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
     */




//        public void updateByName(String password,String name) {
//            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("a21da08");
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//            String sql = "UPDATE CustomerTableEntity set cust_password=:password where cust_name=:name";
//            Query query = (Query) entityManager.createNativeQuery(sql);
//            query.setParameter("password", password)
//                    .setParameter("name", name)
//                    .executeUpdate();
//            entityManager.getTransaction().commit();
//            entityManager.close();
//            entityManagerFactory.close();
//        }


}



