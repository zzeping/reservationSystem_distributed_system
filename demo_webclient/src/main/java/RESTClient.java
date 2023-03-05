import entities.RestaurantTableEntity;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


//JAX-RS 2.0
    public class RESTClient {

        public static void main(String[] args){
            //Client client = ClientBuilder.newBuilder().build();
            Client client = ClientBuilder.newClient();

            WebTarget target = client.target("http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant/searchCustomerByUserId");
            Invocation invo = target.queryParam("custid",10).request(MediaType.TEXT_PLAIN).buildGet();
            Response response = invo.invoke();
            String name = response.readEntity(String.class);
            System.out.println(name);

            target = client.target("http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant/searchRestaurantByRestId");
            invo = target.queryParam("restid",1).request(MediaType.APPLICATION_JSON).buildGet();
            response = invo.invoke();
            //Response response2 = response.readEntity(Response.class);
            //System.out.println(response.toString());
            //System.out.println(response.readEntity(RestaurantTableEntity.class));
            System.out.println(response.readEntity(String.class));


            target = client.target("http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant");
            invo = target.queryParam("username","songyao").request(MediaType.APPLICATION_JSON).buildGet();
            response = invo.invoke();
            //Response response2 = response.readEntity(Response.class);
            //System.out.println(response.toString());
            //response.readEntity(Boolean.class);
            System.out.println(response.getMediaType());
            System.out.println(response.readEntity(String.class));


            target = client.target("http://localhost:8080/reservationSystem_V2-1.0-SNAPSHOT/rs/restaurant/getAllRestaurants");
            invo = target.request(MediaType.APPLICATION_JSON).buildGet();
            response = invo.invoke();
            //Response response2 = response.readEntity(Response.class);
            System.out.println(response.readEntity(String.class));
            //response.readEntity(List<RestaurantTableEntity.class>.class);
            //System.out.println(response.readEntity(RestaurantTableEntity.class));
        }

    }

