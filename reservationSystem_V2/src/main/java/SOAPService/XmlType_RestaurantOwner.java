package SOAPService;

import jakarta.xml.bind.annotation.*;

@XmlType
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlType_RestaurantOwner {

    @XmlAttribute(name = "restaurant_name",required = true)
    private String restName;

    @XmlAttribute(name = "restaurant_username",required = true)
    private String restUsername;

    @XmlAttribute(name = "restaurant_owner",required = true)
    private String restOwner;


    public XmlType_RestaurantOwner(String restName, String restUsername, String restOwner) {
        this.restName = restName;
        this.restUsername = restUsername;
        this.restOwner = restOwner;
    }

    public String getRestOwner() {
        return restOwner;
    }

    public void setRestOwner(String restOwner) {
        this.restOwner = restOwner;
    }

    public String getRestUsername() {
        return restUsername;
    }

    public void setRestUsername(String restUsername) {
        this.restUsername = restUsername;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

}
