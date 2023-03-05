package entities;

//public enum City {
//    Leuven,
//    Brussels,
//    Bruges,
//    Antwerp,
//    Ghent,
//    Liege
//}

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;


public enum City {

    /**
     * City list
     */
    All(0),
    Leuven(1),
    Brussels(2),
    Bruges(3),
    Antwerp(4),
    Ghent(5),
    Liege(6),
    ;


    private final Integer cityId;

    City(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId() {
        return cityId;
    }
}