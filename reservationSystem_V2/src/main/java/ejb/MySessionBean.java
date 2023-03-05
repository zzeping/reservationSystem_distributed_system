package ejb;


import business.Interceptor_Binding_Booking;
import business.Interceptor_Booking;
import business.Singleton_LogInfo;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.transaction.Transactional;

import javax.interceptor.Interceptors;

//@Transactional
//@Interceptors(Interceptor_Booking.class)
@jakarta.ejb.Stateless(name = "MySessionBeanEJB")
public class MySessionBean {
    public MySessionBean(){

    }


    @Inject
    Singleton_LogInfo singleton_logInfo;

    //测试用//@Interceptor_Binding_Booking
    public int dosomethingReallyDifferent(int a, int b){
        singleton_logInfo.addLogInInfo("jiaqi");
        //System.out.println(" try it try");
        return  a+b;
    }

    /*
    @AroundInvoke
    public Object BookingMethod(InvocationContext ic) throws Exception{
        //logger.info(ic.getTarget().toString()+" "+ic.getMethod().getName().toString());
        //logger.info("interceptor successfully");
        System.out.println("interceptor successfully");
        try{
            //return ic.proceed();
            return 15;

        } finally {
            //logger.info(ic.getTarget().toString()+" "+ ic.getMethod().getName().toString());
            //System.out.println("interceptor successfully");
        }
    }
     */

}
