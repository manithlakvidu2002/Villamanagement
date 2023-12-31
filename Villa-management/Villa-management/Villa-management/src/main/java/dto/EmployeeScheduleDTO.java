package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeScheduleDTO {

    private String scheduleId;
    private String time;
    private String date;
    private String shift;
    private String empId;

}
