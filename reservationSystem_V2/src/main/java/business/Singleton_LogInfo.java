package business;

import jakarta.annotation.PreDestroy;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.List;
import java.util.Vector;


//Record ever users' login and logout log information
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class Singleton_LogInfo {


    private List<String> logs = new Vector<>();

    public synchronized void addLogInInfo(String username){
        String info = username +" logs in at : " + new java.util.Date().toString();
        logs.add(info);
        //System.out.println(info);
        //System.out.println("the singleton size is "+ this.getNumberOfLogs());
    }

    public synchronized void addLogOutInfo(String username){
        String info = username +" logs out at : " + new java.util.Date().toString();
        logs.add(info);
        System.out.println(info);
    }

    public int getNumberOfLogs(){
        if (logs == null || logs.isEmpty())
            return 0;
        else
            return logs.size();
    }

    public List<String> getLogs() {
        return logs;
    }

    @PermitAll
    @PreDestroy
    public void clear()
    {
        logs.clear();
    }
}
