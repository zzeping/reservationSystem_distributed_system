import soap.RestaurantOwnerValidator;
import soap.RestaurantOwnerValidatorService;
import soapservice.SOAPRestaurantOwnerValidator;
import soapservice.SOAPRestaurantOwnerValidatorService;
import soapservice.XmlTypeRestaurantOwner;

import javax.xml.ws.WebServiceRef;

//public class SOAPClient {
//
//    public static void  main(String[] args){
//
//        RestaurantOwnerValidator restaurantOwnerValidator = new RestaurantOwnerValidatorService().getRestaurantOwnerValidatorPort();
//        if(restaurantOwnerValidator.validateRestaurantName("dd") == true)
//            System.out.println("the restaurant is in the database");
//
//    }
//}

public class SOAPClient {

    public static void  main(String[] args){

        SOAPRestaurantOwnerValidator soapRestaurantOwnerValidator = new SOAPRestaurantOwnerValidatorService().getSOAPRestaurantOwnerValidatorPort();

        if(soapRestaurantOwnerValidator.validateRestaurantName("dd") == true)
            System.out.println("the restaurant is in the database");

        if(soapRestaurantOwnerValidator.validateRestaurantUsername("dd") == true)
            System.out.println("the restaurant user is in the database");

        //soapRestaurantOwnerValidator.getRestaurantInfo();
    }
}
