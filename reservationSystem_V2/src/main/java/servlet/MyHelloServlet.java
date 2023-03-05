package servlet;

import DAO.CustomerDao;
import DAO.RestaurantDao;
import business.Customer;
import business.MDB_Producer;
import business.Singleton_LogInfo;
import ejb.MySessionBean;
import entities.CustInfo;
import entities.CustomerTableEntity;
import entities.Timeslot;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MyHelloServlet", urlPatterns = "/hello")
public class MyHelloServlet extends HttpServlet {


    @EJB    //@Inject  is also ok
    MySessionBean bean;

    @Inject
    MDB_Producer producer;

    @EJB
    private Singleton_LogInfo singleton_logInfo;

    /*
    @Inject
    UserInterface user;
    //start a timer
    @EJB
    TimerService timer;
     */

    @Inject
    CustomerDao customerDao;

    @Inject
    RestaurantDao restaurantDao;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("TEXT/HTML");
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html><head><title>DA demo </title></head>\n<body>");
        writer.println("<h1>Log information!</h1>");
        //writer.println("<p> the result of 15+32 = "+bean.dosomethingReallyDifferent(15,32)+".<p>");
        //writer.println("</body>\n</html>");

        //timer.setTimer(5000); //5000ms
        //producer.sendMessage("songyao_jin");
//       singleton_logInfo.addLogInInfo("songyao");

        List<String> logs = singleton_logInfo.getLogs();
        for(String tem : logs)
        {
            writer.println("singleton information: "+tem+" .<p>");
        }

        //writer.println(restaurantDao.valiadateResName("thaiTime"));

        writer.println("</body>\n</html>");
        //System.out.println("the singleton size is "+ logInfo_singleton.getNumberOfLogs());


//        CustomerTableEntity customer = new CustomerTableEntity();
//        customer.setCustName("lili222");
//        customer.setCustUsername("lili333");
//        customer.setCustPassword("999999");
//        CustInfo ci = new CustInfo();
//        ci.setCustEmail("lili3333@com");
//        ci.setCustPhone("23442");
//        customer.setCustInfo(ci);
        //CustomerDao CustomerDao=new CustomerDao();
        //CustomerDao.CustomerNew(customer);

        //customerDao.CustomerNew(customer);
        //producer.sendMessage(customer);


        //customerDao.bookTable("lili9777",1,1, Timeslot.timeslot0);

        //restaurantDao.clearAll();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
