package schedule.v1.repository;

import schedule.v1.dto.ScheduleResponseDto;
import schedule.v1.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();
}
