package business;

import entities.CustInfo;
import entities.CustomerTableEntity;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.StatefulTimeout;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


@Stateful
//@StatefulTimeout(value = 30, unit = TimeUnit.MINUTES)
//@Local(CustomerInterface.class)
//@Alternative
@LocalBean
public class Customer implements CustomerInterface , Serializable {

    @Inject
    Singleton_LogInfo singleton_logInfo;
    private CustomerTableEntity customer = new CustomerTableEntity();
    private CustInfo ci = new CustInfo();

    @Override
    public void login(CustomerTableEntity cust) {
        /*
        customer.setCustId(cust.getCustId());
        customer.setCustName(cust.getCustName());

        customer.setCustUsername(cust.getCustUsername());
        customer.setCustPassword(cust.getCustPassword());

        customer.setTables(customer.getTables());

        ci.setCustEmail(cust.getCustInfo().getCustEmail());
        ci.setCustPhone(cust.getCustInfo().getCustPhone());
        customer.setCustInfo(ci);
         */
        customer = cust;
        System.out.println(customer.getCustName()+" logs in at : " + new java.util.Date());
        singleton_logInfo.addLogInInfo(customer.getCustUsername());
    }

    //@Remove
    @Override
    public void logout() {
        System.out.println(customer.getCustName()+" logs out at : " + new java.util.Date());
        singleton_logInfo.addLogOutInfo(customer.getCustUsername());
    }

    @Override
    public CustomerTableEntity getUser() {
        return customer;
    }
}
