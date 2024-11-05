package schedule.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import schedule.v1.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String name;
    private String contents;
    private String password;
    private LocalDateTime dtcreate;
    private LocalDateTime dtmodify;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.contents = schedule.getContents();
        this.password = schedule.getPassword();
        this.dtcreate = schedule.getDtcreate();
        this.dtmodify = schedule.getDtmodify();
    }
}
