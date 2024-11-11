package schedule.repository;

import schedule.dto.ScheduleResponseDto;
import schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    Schedule saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    int updateSchedule(Long id, String name, String contents, String password);

    int deleteSchedule(Long id, String password);
}
