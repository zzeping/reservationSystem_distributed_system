package entities;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;

@Entity
@Table(name = "reservationTable")
@XmlRootElement
@XmlType
public class ReservationTableEntity {

    @ManyToOne
//    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name="fk_cust_id")
    @Id
    private CustomerTableEntity customer;

    @ManyToOne
//    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name="fk_table_id")
    @Id
    private TableTableEntity table;

    @Column(name = "timeslot",nullable = false)
    private Timeslot timeslot;

    public CustomerTableEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTableEntity customer) {
        this.customer = customer;
    }

    public TableTableEntity getTable() {
        return table;
    }

    public void setTable(TableTableEntity table) {
        this.table = table;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }
}
