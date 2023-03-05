package entities;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Embeddable;

import java.io.Serializable;

@Named("CustInfo")
@SessionScoped
@Embeddable
public class CustInfo implements Serializable {
    private String custPhone;
    private String custEmail;

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
}
