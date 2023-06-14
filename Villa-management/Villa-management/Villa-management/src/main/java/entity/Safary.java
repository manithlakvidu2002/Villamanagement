package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Safary {
    private  String safaryId;
    private  String type;
    private  String date;
    private  String time;
    private  String driverId;

    private  String driverName;
    private  String driverContact;

    public Safary(String safaryId, String type, String date, String time, String driverId, String driverName, String driverContact) {
        this.safaryId = safaryId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverContact = driverContact;
    }

    public Safary(String safaryId, String type, String date, String time, String driverId) {
        this.safaryId = safaryId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.driverId = driverId;
    }
}
