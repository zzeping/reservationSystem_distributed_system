package business;


import DAO.RestaurantDao;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;

import java.util.logging.Logger;

@Stateless
public class Timer_TimerService {

    @Resource
    SessionContext context;


    @Inject
    RestaurantDao restaurantDao;

    private static final Logger logger = Logger.getLogger("business.MyTimerService");

    public void setTimer(long interval){

        context.getTimerService().createTimer(interval, "Setting a programmatic timer");    // single-action
    }

    @Timeout
    public void programmaticTimeout(Timer timer){
        logger.info("@Timeout in programmatic timer at" +new java.util.Date());
        //System.out.println("timeoutHandler : " + timer.getInfo());
        timer.cancel();
    }

    //print logger info and clear the Reservation information in the database every day.
    //@Schedule(second = "*/10", minute ="*" , hour = "*", dayOfWeek = "*", dayOfMonth = "*", month = "*", year = "*", info = "MyTimer")
    @Schedule(second = "0", minute ="0" , hour = "0", dayOfWeek = "*", dayOfMonth = "*", month = "*", year = "*", info = "MyTimer")
    private void scheduleTimeout(final Timer t){
        logger.info("@Scheduled timer triggered at " +new java.util.Date());
        //System.out.println("@Scheduled timer triggered at : " + new java.util.Date());
        restaurantDao.clearAll();
    }

}
