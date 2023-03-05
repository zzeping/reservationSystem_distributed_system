package RESTfulService;

import DAO.CustomerDao;
import DAO.RestaurantDao;
import entities.RestaurantTableEntity;
import entities.TableTableEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/restaurant")
@Stateless
public class RESTful_RestaurantService {

    @EJB
    CustomerDao custDao;
    @EJB
    RestaurantDao restDao;

    //http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant/searchRestaurantByRestId?restid=1
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("searchRestaurantByRestId")
    public RestaurantTableEntity getRestaurantByRestId(@DefaultValue("1") @QueryParam("restid") int restid){

        return restDao.getRestaurantByRestId(restid);
        //return null;
    }

//    @GET
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Path("searchAllRestaurants")
//    public List<RestaurantTableEntity> getAllRestaurants( ){
//
//        return restDao.Rests();
//    }

    //http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant/getAllRestaurants
    //查找所有restaurant信息 ok
    @GET
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getAllRestaurants")
    public Response getAllRestaurants()
    {
        return Response.ok(restDao.Rests()).build();
    }

    //http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant?username=songyao
    //@POST
    //@PUT
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Boolean ValidateRestaurantOwner(@QueryParam("username") String username){
        //restDao.changeOwner(username,owner);
        //return Response.created(URI.create("jiaqi")).build();
        return true;
    }


    //查找该用户名 ok
    //http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant/searchCustomerByUserId?custid=10
    @GET
    @Produces("text/plain")
    @Path("/searchCustomerByUserId")
    public String getCustomerByUserId(@DefaultValue("1") @QueryParam("custid") int custid){

        return custDao.findUserbyId(custid);
    }

    //http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant/delete?restid=9
    //@DELETE
    @Path("/delete")
    public Response deleteRestaurantById(@DefaultValue("1") @QueryParam("restid") int restid){
        restDao.deleteRest(restid);
        return Response.noContent().build();
        //return Response.ok().build();
    }

}
