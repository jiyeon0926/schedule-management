package schedule.service;

import schedule.dto.ScheduleRequestDto;
import schedule.dto.ScheduleResponseDto;
import schedule.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedules(String email);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String name, String contents, String password);

    void deleteSchedule(Long id, String password);
}
