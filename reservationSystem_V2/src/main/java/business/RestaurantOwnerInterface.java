package business;


import entities.RestaurantTableEntity;
import jakarta.ejb.Local;
import jakarta.ejb.Remove;

//save the logged restaurant owner information locally
@Local
public interface RestaurantOwnerInterface {

    public void login(RestaurantTableEntity restOwner);
    @Remove
    public void logout();

    public RestaurantTableEntity getUser ();
}
