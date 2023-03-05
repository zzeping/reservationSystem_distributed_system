package entities;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("tableTable")
@SessionScoped
@Entity
@Table(name = "tableTable", schema = "hellodemo", catalog = "")
public class TableTableEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "table_id", nullable = false)
    private int tableId;
    @Basic
    @Column(name = "table_number", nullable = false)
    private int tableNumber;
    @Basic
    @Column(name = "table_size", nullable = false)
    private int tableSize;

    @Basic
    @Column(name = "table_state", nullable = false)
    private int tableState;

//    @Basic
//    @Column(name = "table_timeslot", nullable = false, length = 100)
//    @Enumerated(EnumType.ORDINAL)
//    private Timeslot tableTimeslot;

    @ManyToOne(targetEntity = RestaurantTableEntity.class)
    @JoinColumn(name = "fk_rest_id",referencedColumnName = "rest_id")
    private RestaurantTableEntity restaurant;

    @ManyToMany(mappedBy = "tables")
    private List<CustomerTableEntity> customers = new ArrayList<>();


    public RestaurantTableEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantTableEntity restaurant) {
        this.restaurant = restaurant;
    }


    public List<CustomerTableEntity> getCustomers() { return customers; }

    public void setCustomers(List<CustomerTableEntity> customers) { this.customers = customers; }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

//    public Timeslot getTableTimeslot() { return tableTimeslot; }
//
//    public void setTableTimeslot(Timeslot tableTimeslot) { this.tableTimeslot = tableTimeslot; }

    public int getTableState() {
        return tableState;
    }

    public void setTableState(int tableState) {
        this.tableState = tableState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableTableEntity that = (TableTableEntity) o;

        if (tableId != that.tableId) return false;
        if (tableNumber != that.tableNumber) return false;
        if (tableSize != that.tableSize) return false;
//        if (tableTimeslot != that.tableTimeslot) return false;
        if (tableState != that.tableState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableId;
        result = 31 * result + tableNumber;
        result = 31 * result + tableSize;
//        result = 31 * result + (tableTimeslot != null ? tableTimeslot.hashCode() : 0);
        result = 31 * result + tableState;
        return result;
    }
}
