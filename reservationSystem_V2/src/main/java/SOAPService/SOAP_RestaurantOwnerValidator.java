package SOAPService;

import DAO.CustomerDao;
import DAO.RestaurantDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceRef;

@WebService
@Stateless
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public class SOAP_RestaurantOwnerValidator {

    @Inject
    CustomerDao custDao;
    @Inject
    RestaurantDao restDao;

//    @WebMethod(operationName = "ValidateRestaurantOwner")
//    @WebResult(name ="IsValid")
//    public boolean validate(@WebParam(name = "RestaurantOwner") XmlType_RestaurantOwner owner){
//
//
//        return true;
//    }

    @WebMethod(operationName = "ValidateRestaurantUsername")
    @WebResult(name ="IsValid")
    public boolean validate(@WebParam(name = "RestaurantUsername") String username){
        //restDao.valiadateResUsername(username);
        //return restDao.valiadateResUsername(username);
        return true;
    }

    @WebMethod(operationName = "ValidateRestaurantName")
    public boolean validateRestaurantName(String restaurantName){
        return true;
        //return restDao.valiadateResName(restaurantName);
    }


    public XmlType_RestaurantOwner getRestaurantInfo()
    {

        XmlType_RestaurantOwner re = new XmlType_RestaurantOwner("aa","bb","cc");
        return re;
    }

}
