package schedule.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import schedule.v1.entity.Schedule;

import java.time.ZoneId;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String name;
    private String contents;
    private String password;
    private Date dtcreate;
    private Date dtmodify;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.contents = schedule.getContents();
        this.password = schedule.getPassword();
        this.dtcreate = Date.from(schedule.getDtcreate().atZone(ZoneId.systemDefault()).toInstant());
        this.dtmodify = Date.from(schedule.getDtmodify().atZone(ZoneId.systemDefault()).toInstant());
    }
}
