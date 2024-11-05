package schedule.v1.repository;

import schedule.v1.dto.ScheduleResponseDto;
import schedule.v1.entity.Schedule;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
}
