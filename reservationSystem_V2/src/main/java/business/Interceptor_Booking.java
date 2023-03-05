package business;

import DAO.CustomerDao;
import DAO.TableDao;
import entities.Timeslot;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.interceptor.*;
import jakarta.persistence.PersistenceContext;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Time;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


@Interceptor
@Interceptor_Binding_Booking
public class Interceptor_Booking {

    @EJB
    TableDao tableDao;


    private static final Logger logger = Logger.getLogger("business.MyService");

    @AroundInvoke
    public Object BookingMethod(InvocationContext ic) throws Exception{
        int restid= 0;
        int tablenum = 0;
        Timeslot time = null;

        logger.info(ic.getTarget().toString()+" "+ic.getMethod().getName().toString());
        try{

            //Interceptor 要intercept DAO booking function
            //获得传入参数，检验一下（调用DAO function），不行的话就拦截（返回null），行的话才可继续，还可以更改参数
            Object[] parameters = ic.getParameters();
            if (parameters.length > 0 && parameters[0] instanceof String) {
                String username = (String) parameters[0];
                restid = (int) parameters[1];
                tablenum = (int) parameters[2];
                time = (Timeslot) parameters[3];

                //System.out.println("a= " + username+" , b= "+time);
                //parameters[0] = param + " Nice to meet you.";
                //ic.setParameters(parameters);
            }

            if(tableDao.checkAvailable(restid,tablenum,time) )
            {
                return ic.proceed();
            }
            //parameters[0] = param + " Nice to meet you.";
            //ic.setParameters(parameters);

            //返回null 表明被拦截，条件不成立
            System.out.println("Blocked Reservation: this Reservation time has been occupied");
            return null;
            //return 13;

            /*
            if(){
                return ic.proceed();
            }
            else{
                //返回null 表明被拦截，条件不成立
                return null;
            }
             */

        } finally {
            //logger.info(ic.getTarget().toString()+" "+ ic.getMethod().getName().toString());
        }
    }
}
