package business;


import DAO.CustomerDao;
import entities.CustInfo;
import entities.CustomerTableEntity;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@MessageDriven(mappedName = "jms/javaee7/Queue", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Auto-acknowledge")})
public class MDB_Consumer implements MessageListener {

    /*
    @EJB
    private Singleton_LogInfo logInfo_singleton;
     */

//    @EJB
//    CustomerDao customerDao;

    @PersistenceContext(unitName = "a21da08")
    EntityManager entityManager;

    @Override
    public void onMessage(Message message) {
        try {
            //String username = message.getBody(String.class);
            //logInfo_singleton.addLogInInfo(username);

            CustomerTableEntity customer = message.getBody(CustomerTableEntity.class);
            System.out.println("Received object: "+customer.toString());

            //收到queue的消息，执行注册
            //CustomerDao customerDao=new CustomerDao();
            //customerDao.CustomerNew(customer);
            this.CustomerNew(customer);


        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }


    //用户注册
    public void CustomerNew(CustomerTableEntity customer){

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
    }

}


