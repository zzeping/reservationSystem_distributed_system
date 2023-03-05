package entities;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("customerTable")
@SessionScoped
@XmlRootElement
@Entity
@Table(name = "customerTable", schema = "hellodemo", catalog = "")
public class CustomerTableEntity implements  Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cust_id", nullable = false)
    private int custId;
    @Basic
    @Column(name = "cust_username", nullable = false, length = 100)
    private String custUsername;
    @Basic
    @Column(name = "cust_password", nullable = false, length = 100)
    private String custPassword;
    @Basic
    @Column(name = "cust_name", nullable = false, length = 100)
    private String custName;
//    @Basic
//    @Column(name = "cust_phone", nullable = false, length = 100)
//    private String custPhone;
//    @Basic
//    @Column(name = "cust_email", nullable = false, length = 100)
//    private String custEmail;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "custPhone", column = @Column(name = "cust_phone")),
            @AttributeOverride( name = "custEmail", column = @Column(name = "cust_email"))
    })
    private CustInfo custInfo;

    @ManyToMany(targetEntity = TableTableEntity.class)
    @JoinTable(name = "reservationTable",
            joinColumns = {@JoinColumn(name = "fk_cust_id",referencedColumnName = "cust_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_table_id",referencedColumnName = "table_id")})
    private List<TableTableEntity> tables = new ArrayList<>();



    public List<TableTableEntity> getTables() { return tables; }

    public void setTables(List<TableTableEntity> tables) { this.tables = tables; }

    public CustInfo getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(CustInfo custInfo) {
        this.custInfo = custInfo;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustUsername() {
        return custUsername;
    }

    public void setCustUsername(String custUsername) {
        this.custUsername = custUsername;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

//    public String getCustPhone() {
//        return custPhone;
//    }
//
//    public void setCustPhone(String custPhone) {
//        this.custPhone = custPhone;
//    }
//
//    public String getCustEmail() {
//        return custEmail;
//    }
//
//    public void setCustEmail(String custEmail) {
//        this.custEmail = custEmail;
//    }


}
