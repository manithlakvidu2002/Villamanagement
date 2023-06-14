package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Bill {
    private String cusId;
    private String Name;
    private String contact;
    private String safaryType;
    private  String roomId;
    private String roomType;
    private String roomPrice;
    private double payment;
    private String bill;
    private String cash;
    private String balance;

    public Bill(String cusId, String bill, String cash, String balance) {
        this.cusId = cusId;
        this.bill = bill;
        this.cash = cash;
        this.balance = balance;
    }
}
