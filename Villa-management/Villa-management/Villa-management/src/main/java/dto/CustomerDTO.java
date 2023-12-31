package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // setter getter method
@AllArgsConstructor // all property
@NoArgsConstructor // defaul constructor
@ToString
public class CustomerDTO {

    private String cusId;
    private String name;
    private String address;
    private String dob;
    private String nic;
    private String contact;
    private String sex;
    private String safaryId;
    private String safaryType;


}
