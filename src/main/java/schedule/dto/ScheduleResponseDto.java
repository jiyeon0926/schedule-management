package schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import schedule.entity.Schedule;

import java.time.ZoneId;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String name;
    private String contents;
    private String password;
    private String email;
    private Date dtcreate;
    private Date dtmodify;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.contents = schedule.getContents();
        this.password = schedule.getPassword();
        this.email = schedule.getEmail();

        // 조회할 때, Date 타입으로 보여주기 위해 LocalDateTime 타입으로 저장된 데이터를 변환하여 초기화
        this.dtcreate = Date.from(schedule.getDtcreate().atZone(ZoneId.systemDefault()).toInstant());
        this.dtmodify = Date.from(schedule.getDtmodify().atZone(ZoneId.systemDefault()).toInstant());
    }
}
