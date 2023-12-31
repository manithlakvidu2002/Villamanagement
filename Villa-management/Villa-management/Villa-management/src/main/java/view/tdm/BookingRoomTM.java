package view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRoomTM {
    private String cusId;
    private String cusName;
    private String contact;
    private String roomId;
    private String roomType;
    private String roomPrice;
    private String paymentType;
    private double payment;
    private String cash;


}

