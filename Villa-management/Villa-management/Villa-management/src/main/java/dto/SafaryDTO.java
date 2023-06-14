package dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class SafaryDTO {
    private  String safaryId;
    private  String type;
    private  String date;
    private  String time;
    private  String driverId;

    private  String driverName;
    private  String driverContact;

    public SafaryDTO(String safaryId, String type, String date, String time, String driverId, String driverName, String driverContact) {
        this.safaryId = safaryId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverContact = driverContact;
    }

    public SafaryDTO(String safaryId, String type, String date, String time, String driverId) {
        this.safaryId = safaryId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.driverId = driverId;
    }
}
