package business;

import entities.RestaurantTableEntity;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.ejb.StatefulTimeout;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Stateful
//@StatefulTimeout(value = 30, unit = TimeUnit.MINUTES)
@LocalBean
public class RestaurantOwner implements RestaurantOwnerInterface, Serializable {

    @Inject
    Singleton_LogInfo singleton_logInfo;
    private RestaurantTableEntity restaurantOwner = new RestaurantTableEntity();

    @Override
    public void login(RestaurantTableEntity restOwner) {
        /*
        restaurantOwner.setRestId(restOwner.getRestId());
        restaurantOwner.setRestName(restOwner.getRestName());

        restaurantOwner.setRestUsername(restOwner.getRestUsername());
        restaurantOwner.setRestPassword(restOwner.getRestPassword());

        restaurantOwner.setRestOwner(restOwner.getRestOwner());
        restaurantOwner.setRestCity(restOwner.getRestCity());
        restaurantOwner.setRestDescription(restOwner.getRestDescription());
        restaurantOwner.setRestGenre(restOwner.getRestGenre());
        restaurantOwner.setRestLocation(restOwner.getRestLocation());
        //restaurantOwner.setRestState(restOwner.getRestState());
        restaurantOwner.setTables(restOwner.getTables());
         */
        restaurantOwner = restOwner;
        System.out.println(restaurantOwner.getRestOwner()+" having restaurant "+restaurantOwner.getRestName()+" logs in at : " + new java.util.Date());
        singleton_logInfo.addLogInInfo(restaurantOwner.getRestUsername());
    }

    //@Remove
    @Override
    public void logout() {
        System.out.println(restaurantOwner.getRestOwner()+" having restaurant "+restaurantOwner.getRestName()+" logs out at : " + new java.util.Date());
        singleton_logInfo.addLogOutInfo(restaurantOwner.getRestUsername());
    }

    @Override
    public RestaurantTableEntity getUser() {
        return restaurantOwner;
    }
}
