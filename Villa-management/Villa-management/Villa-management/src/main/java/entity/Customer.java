package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // setter getter method
@AllArgsConstructor // all property
@NoArgsConstructor // defaul constructor
@ToString
public class Customer {

    private String cusId;
    private String name;
    private String address;
    private String dob;
    private String nic;
    private String contact;
    private String sex;
    private String safaryId;
    private String safaryType;


    public Customer(String cusId, String name, String address, String dob, String nic, String contact, String sex, String safaryId) {
        this.cusId = cusId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.nic = nic;
        this.contact = contact;
        this.sex = sex;
        this.safaryId = safaryId;
    }
}
