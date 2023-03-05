package entities;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named("restaurantTable")
@SessionScoped
//@XmlRootElement
//@XmlType
@Entity
@Table(name = "restaurantTable", schema = "hellodemo", catalog = "")
public class RestaurantTableEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rest_id", nullable = false)
    private int restId;
    @Basic
    @Column(name = "rest_name", nullable = false, length = 100)
    private String restName;
    @Basic
    @Column(name = "rest_city", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private City restCity;
    @Basic
    @Column(name = "rest_location", nullable = false, length = 300)
    private String restLocation;
    //    @Basic
//    @Column(name = "rest_state", nullable = false)
//    private int restState;
    @Basic
    @Column(name = "rest_genre", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Genre restGenre;
    @Basic
    @Column(name = "rest_description", nullable = false, length = 500)
    private String restDescription;
    @Basic
    @Column(name = "rest_owner", nullable = false, length = 100)
    private String restOwner;
    @Basic
    @Column(name = "rest_username", nullable = false, length = 100)
    private String restUsername;
    @Basic
    @Column(name = "rest_password", nullable = false, length = 100)
    private String restPassword;

    //    @OneToMany(targetEntity = TableTableEntity.class)
//    @JoinColumn(name = "fk_rest_id",referencedColumnName = "rest_id")
    @OneToMany(mappedBy = "restaurant")
    private transient List<TableTableEntity> tables = new ArrayList<>();

    public List<TableTableEntity> getTables() { return tables; }

    public void setTables(List<TableTableEntity> tables) { this.tables = tables; }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public City getRestCity() {
        return restCity;
    }

    public void setRestCity(City restCity) {
        this.restCity = restCity;
    }

    public String getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(String restLocation) {
        this.restLocation = restLocation;
    }

//    public int getRestState() {
//        return restState;
//    }
//
//    public void setRestState(int restState) {
//        this.restState = restState;
//    }

    public Genre getRestGenre() {
        return restGenre;
    }

    public void setRestGenre(Genre restGenre) {
        this.restGenre = restGenre;
    }

    public String getRestDescription() {
        return restDescription;
    }

    public void setRestDescription(String restDescription) {
        this.restDescription = restDescription;
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

    public String getRestPassword() {
        return restPassword;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTableEntity that = (RestaurantTableEntity) o;

        if (restId != that.restId) return false;
        if (restCity != that.restCity) return false;
//        if (restState != that.restState) return false;
        if (restGenre != that.restGenre) return false;
        if (restName != null ? !restName.equals(that.restName) : that.restName != null) return false;
        if (restLocation != null ? !restLocation.equals(that.restLocation) : that.restLocation != null) return false;
        if (restDescription != null ? !restDescription.equals(that.restDescription) : that.restDescription != null)
            return false;
        if (restOwner != null ? !restOwner.equals(that.restOwner) : that.restOwner != null) return false;
        if (restUsername != null ? !restUsername.equals(that.restUsername) : that.restUsername != null) return false;
        if (restPassword != null ? !restPassword.equals(that.restPassword) : that.restPassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = restId;
        result = 31 * result + (restName != null ? restName.hashCode() : 0);
        result = 31 * result + (restCity != null ? restCity.hashCode() : 0);
        result = 31 * result + (restLocation != null ? restLocation.hashCode() : 0);
//        result = 31 * result + restState;
        result = 31 * result + (restGenre != null ? restGenre.hashCode() : 0);
        result = 31 * result + (restDescription != null ? restDescription.hashCode() : 0);
        result = 31 * result + (restOwner != null ? restOwner.hashCode() : 0);
        result = 31 * result + (restUsername != null ? restUsername.hashCode() : 0);
        result = 31 * result + (restPassword != null ? restPassword.hashCode() : 0);
        return result;
    }
}
