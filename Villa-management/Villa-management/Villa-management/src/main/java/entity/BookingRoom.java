package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRoom {
    private String cusId;
    private String cusName;
    private String contact;
    private String roomId;
    private String roomType;
    private String roomPrice;
    private String paymentType;
    private double payment;
    private String cash;




    public BookingRoom(String cusId, String roomId, String paymentType, double payment) {
        this.cusId = cusId;
        this.roomId = roomId;
        this.paymentType = paymentType;
        this.payment = payment;
    }
}

