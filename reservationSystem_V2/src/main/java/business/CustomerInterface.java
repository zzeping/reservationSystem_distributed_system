package business;

import entities.CustomerTableEntity;
import jakarta.ejb.Local;
import jakarta.ejb.Remove;

//save the logged customer information locally
@Local
public interface CustomerInterface {

    public void login(CustomerTableEntity cust);
    @Remove
    public void logout();

    public CustomerTableEntity getUser ();



}
