package schedule.repository;

import schedule.dto.ScheduleResponseDto;
import schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    int updateSchedule(Long id, String name, String contents, String password);
}
