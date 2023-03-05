package business;

import entities.CustomerTableEntity;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.*;

import java.util.Date;


@Stateless
public class MDB_Producer {

    @Inject
    @JMSConnectionFactory("jms/javaee7/ConnectionFactory")
    @JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
    private JMSContext context;
    @Resource(lookup = "jms/javaee7/Queue")
    private Destination queue;

    public void sendMessage(CustomerTableEntity customer){
        //context.createProducer().send(queue,"Text message sent at "+new Date());
        //用户注册 通过MessageDrivenBean
        context.createProducer().send(queue,customer);
    }


}