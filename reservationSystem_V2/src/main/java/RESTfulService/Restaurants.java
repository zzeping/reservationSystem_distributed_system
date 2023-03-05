package RESTfulService;

import entities.RestaurantTableEntity;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlType
@XmlRootElement
@XmlSeeAlso({RestaurantTableEntity.class})
public class Restaurants extends ArrayList<RestaurantTableEntity>{

    public Restaurants() {
        super();
    }

    public Restaurants(Collection<? extends RestaurantTableEntity> c){
        super(c);
    }


    @XmlElement(name = "restaurant")
    public List<RestaurantTableEntity> getRestaurants(){
        return this;
    }

    public void setRestaurants(List<RestaurantTableEntity> restaurants){
        this.addAll(restaurants);
    }
}
