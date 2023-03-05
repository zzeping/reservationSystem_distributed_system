package webBeanExtra;

import entities.Timeslot;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("Reservation")
@SessionScoped
public class Reservation implements Serializable {

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    String restName;
    Timeslot timeslot;

    public String getTime() {
        if (timeslot == Timeslot.timeslot0)
        {
            time = "11:00 - 13:30";
        } else if (timeslot == Timeslot.timeslot1) {
            time = "13:30 - 15:00";
        } else if (timeslot == Timeslot.timeslot2) {
            time = "18:00 - 19:30";
        } else {
            time = "19:30 - 21:00";
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;

}
